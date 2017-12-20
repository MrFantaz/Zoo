package animals;

import interfaceZoo.Soundable;

public class Raven extends Bird implements Soundable {

	public Raven(String nickName, double size) {
		super(nickName, size);
		setType("Raven");
	}

	@Override
	public void makeSound() {

		System.out.println("Karr");
	}

}
