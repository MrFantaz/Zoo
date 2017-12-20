package animals;

import java.util.ArrayList;
import java.util.List;


public class ExtensibleCage<T extends Animal> {
	private List<T> cage = new ArrayList<>();
   
	public int addAnimal(Animal ani) {
		cage.add((T) ani);
		return cage.size();
	}

	public boolean removeAnimal(int index) {
		if (index < 0 || index >= cage.size()) {
			return false;
		}
	   
		cage.remove(index);
		return true;

	}

	public Animal[] getCage() {
		
		return cage.toArray(new Animal[cage.size()]);
	}

}

