package main;

import java.util.ArrayList;
import java.util.List;

import animals.Animal;


public class ExtensibleCage<T extends Animal> {
	private List<T> cage = new ArrayList<>();
   
	public int addAnimal(T ani) {
		cage.add( ani);
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

}
