package animals;

import error.AnimalCreationException;
import food.Food;
import interfaces.ISoundable;
import interfaces.Jumpable;
import io.Logger;

abstract public class Animal implements ISoundable, Jumpable, Comparable<Animal> {
    @Override
    public int compareTo(Animal o) {
        return (int) Math.ceil(this.getSize() - o.getSize());
    }

    private String nickName;
    private double size;// size in metres
    private String gender;// male or female
    private long age;// milliseconds
    public String type;
    private double fill;
    private long lastFeedTime;
    private double countOfFood;
    boolean isAlive = true;
    private IAnimalDeadListener animalDeadListener;

    public Animal(String nickName, double size) {
        super();
        this.nickName = nickName;
        this.size = size;
        setFill(60);
    }

    public interface IAnimalDeadListener {
        void onAnimalDead(Animal animal);
    }

    public void setAnimalDeadListener(IAnimalDeadListener animalDeadListener) {
        this.animalDeadListener = animalDeadListener;
    }

    public void feed(Food f) {
        switch (f){
            case MEAT:
                org.pmw.tinylog.Logger.warn(this+" Eating MEAT");
                break;
            case CARROT:
                org.pmw.tinylog.Logger.warn(this+" Eating CARROT");
                break;
        }
        setFill(getFill()+f.getEnergyValue());
        Logger.log(this+" fed "+f);
    }

    public static Animal convertFromString(String str)
            throws AnimalCreationException {
        String[] arrStr = str.split(",");
        switch (arrStr[0]) {
            case "wolf":
                return new Wolf(arrStr[1], Double.parseDouble(arrStr[2]));
            case "cat":
                return new Cat(arrStr[1], new Double(arrStr[2]));
            case "rabbit":
                return new Rabbit(arrStr[1], new Double(arrStr[2]));
            case "bird":
                return new Bird(arrStr[1], new Double(arrStr[2]));
        }
        throw new AnimalCreationException();
    }

    @Override
    public double jump() {
        return size * 10 / (size * size);
    }

    @Override
    public String toString() {
        return "Animal " + type + " " + getNickName() + " size " + getSize();
    }

    public String getNickName() {
        return nickName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getFill() {
        return fill;
    }

    public void dea(){
        isAlive = false;
        if (animalDeadListener != null) {
            animalDeadListener.onAnimalDead(this);
            System.out.println("animal " + this.getNickName() + " is dead");

        }

    }

    public void setFill(double fill) {
        this.fill = fill;
        lastFeedTime = System.currentTimeMillis();
    }

    public long getLastFeedTime() {
        return lastFeedTime;
    }

    public void setLastFeedTime(long lastFeedTime) {
        this.lastFeedTime = lastFeedTime;
    }

    public double getCountOfFood() {
        return countOfFood;
    }

    public void setCountOfFood(double countOfFood) {
        this.countOfFood = countOfFood;
    }

    public void setAlive(boolean alive) {
        if (getFill() >= 0) {
            isAlive = false;
        } else {
            isAlive = true;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

}
