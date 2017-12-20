package animals;

import interfaceZoo.Jumpable;
import interfaceZoo.Soundable;

public class Animal extends Object implements Soundable, Jumpable {
	private String nickName;
	private double size;
	public String type;
	private double fill;
	private long lastFeedTime;
	private boolean isAlive;
	private IAnimalDeadListener animalDeadListener;

	public Animal(String nickName, double size) {
		this.nickName = nickName;
		this.size = size;
		fill = 1000;
		lastFeedTime = System.currentTimeMillis();
		isAlive = true;

	}

	public void setAnimalDeadListener(IAnimalDeadListener animalDeadListener) {
		this.animalDeadListener = animalDeadListener;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
    protected void die() {
    	if (animalDeadListener != null && isAlive) {
			
			animalDeadListener.onAnimalDead(this);
		}
		isAlive = false;
    }

	public double getFill() {
		long timeToDeath = (System.currentTimeMillis() - lastFeedTime) / 1000;
		if (timeToDeath >= fill) {
			
			die();
		}
		return fill;
	}

	public void setFill(double fill) {
		this.fill = this.fill + fill;
		lastFeedTime = System.currentTimeMillis();
	}

	public long getLastFeedTime() {
		return lastFeedTime;
	}

	public void setLastFeedTime(long lastFeedTime) {
		this.lastFeedTime = lastFeedTime;
	}

	public double feed(double feed) {
		setFill(feed);

		return getFill();
	}

	@Override
	public String toString() {

		return "Animal " + getNickName() + " Size " + getSize() + " Type " + getType();
	}

	@Override
	public void makeSound() {

	}

	@Override
	public double jump() {

		return size * 2.3;
	}

	public void sound() {
		// TODO Auto-generated method stub

	}

	public interface IAnimalDeadListener {
		void onAnimalDead(Animal animal);
	}
}
