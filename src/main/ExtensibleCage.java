package main;

import java.util.ArrayList;
import java.util.List;

import animals.Animal;
import animals.Herbivore;
import animals.Predator;

public class ExtensibleCage<T extends Animal> {
	private List<T> cage = new ArrayList<>();

	public int addAnimal(T ani) {
		cage.add(ani);
		checkHuntCondition(ani);
		return cage.size();
	}

	public boolean removeAnimal(int index) {
		if (index < 0 || index >= cage.size()) {
			return false;
		}

		cage.remove(index);
		return true;

	}

	public List<T> getCage() {

		return cage;
	}

	private void checkHuntCondition(Animal animal) {
		if (animal instanceof Herbivore) {
			for (T t : cage) {
				if (t instanceof Predator) {
					((Predator) t).consume((Herbivore) animal);
					return;
				}

			}
		} else if (animal instanceof Predator) {
			for (T t : cage) {
				if (t instanceof Herbivore) {
					((Predator) animal).consume((Herbivore) t);

				}
			}
		}

	}
}
