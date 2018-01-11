package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import animals.Animal;
import animals.Herbivore;
import animals.Predatoc;

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
				if (t instanceof Predatoc) {
					((Predatoc) t).consume((Herbivore) animal);
					return;
				}

			}
		} else if (animal instanceof Predatoc) {
			for (T t : cage) {
				if (t instanceof Herbivore) {
					((Predatoc) animal).consume((Herbivore) t);

				}
			}
		}

	}

	@Override
	public String toString() {
		Collections.sort(cage,sizeComparator size= new sizeComparator());
		StringBuilder sb =new StringBuilder();
		if(cage.size()==0) {
			sb.append("Cage of");
			sb.append(" is empty \n");
		} else {
			sb.append("Cage for :");
			Iterator<? extends Animal>it=cage.iterator();
			while(it.hasNext()) {
				Animal ani = it.next();
				if(ani.getNickName()==null || ani.getNickName().length()==0) {
					it.remove();
				}else {
					sb.append("\t");
					sb.append(ani.toString());
				}
				
			}
		}
		return sb.toString();
	}
	
	
	private static class sizeComparator implements Comparator<Animal>{

		@Override
		public int compare(Animal o1, Animal o2) {
			
			return (int) (o2.getSize()-o1.getSize());
		}
		
	}
}
