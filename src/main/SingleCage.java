package main;

import animals.Animal;

public class SingleCage <T extends Animal> {
    private T habitant;

    public void myMethod(){
        T temp;
    }
    public void setHabitant(T habitant) {
        this.habitant = habitant;
    }

    public T getHabitant() {
        return habitant;
    }
}
