package animals;

public abstract class Mammal extends Animal {
	private int pragnancyPeriod;

	public Mammal(String nickName, double size) {
		super(nickName, size);
	}

	public int getPragnancyPeriod() {
		return pragnancyPeriod;
	}

	public void setPragnancyPeriod(int pragnancyPeriod) {
		this.pragnancyPeriod = pragnancyPeriod;
	}

}
