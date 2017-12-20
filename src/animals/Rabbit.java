package animals;

import interfaceZoo.Soundable;

public class Rabbit extends Herbivore {

	public Rabbit(String nickName, double size) {
		super(nickName, size);
		setType("Rabbit");
	}

	@Override
	public void makeSound() {
		super.makeSound();
	}

}
