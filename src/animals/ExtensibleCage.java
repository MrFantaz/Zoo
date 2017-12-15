package animals;

public class ExtensibleCage {
	private Animal[] cage = new Animal[0];

	public int addAnimal(Animal ani) {
		int newLength = cage.length + 1;
		Animal[] newCage = new Animal[newLength];
		for (int i = 0; i < cage.length; i++) {
			newCage[i] = cage[i];
		}
		newCage[newCage.length - 1] = ani;
		cage = newCage;
		return newCage.length;
	}

	public boolean removeAnimal(int index) {
		if (index < 0 || index >= cage.length) {
			return false;
		}
		int newLength = cage.length - 1;
		Animal[] newCage = new Animal[newLength];
		int j = 0;
		for (int i = 0; i < cage.length; i++) {
			if (i != index) {
				newCage[j] = cage[i];
				j++;

			}
		}
		cage = newCage;
		return true;

	}

	public Animal[] getCage() {
		return cage;
	}

}
