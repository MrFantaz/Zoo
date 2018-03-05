package main;

import animals.Animal;
import animals.Herbivore;
import animals.Predator;

import java.util.*;

public class ExtensibleCage<T extends Animal> implements Animal.IAnimalDeadListener {
    List<T> cage = new LinkedList<>();
    private static SizeComparator sizeComparator = new SizeComparator();

    @Override
    public void onAnimalDead(Animal animal) {
    }

    @Override
    public String toString() {
        Collections.sort(cage);
        StringBuilder sb = new StringBuilder();
        if (cage.size() == 0) {
            sb.append("Cage is empty\n");
        } else {
            sb.append("Cage ");
            Iterator<? extends Animal> itr = cage.iterator();
            while (itr.hasNext()) {
                Animal animal = itr.next(); //in fact it's our foreach
                if (animal.getNickName() == null || animal.getNickName().isEmpty()) {
                    itr.remove();
                } else {
                    sb.append(" ");
                    sb.append(animal.toString());
                    sb.append(" fill = " + animal.getFill());
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    public int addAnimal(T animal) {
        cage.add(animal);
        animal.setAnimalDeadListener(this);
        if (cage.get(0) instanceof Predator) {
            checkHuntCondition(animal);
        }
        return cage.size();
    }

    public boolean removeAnimal(int indexRemoveAnimal) {
        if (indexRemoveAnimal + 1 > cage.size() || indexRemoveAnimal < 0) {
            System.out.println("Error. Enter the validate number from 1 to " + cage.size());
            return false;
        } else {
            cage.remove(indexRemoveAnimal);
            return true;
        }
    }

    public void checkHuntCondition(Animal animal) {
        if (animal instanceof Herbivore && !cage.isEmpty()) {
            for (T t : cage) {
                if (t instanceof Predator) {
                    ((Predator) t).consume((Herbivore) animal);
                }
            }
        }
    }

    public List<T> getCage() {
        return cage;
    }

    private static class SizeComparator implements Comparator<Animal> {
        @Override
        public int compare(Animal o1, Animal o2) {
            return (int) Math.ceil(o1.getSize() - o2.getSize());
        }
    }
}
