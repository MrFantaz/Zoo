package animals;

import interfaceZoo.Soundable;

public  class DomesticCat extends Feline implements Soundable {
	String breed;

	public DomesticCat(String nickName, double size) {
		super(nickName, size);
		setType("Cat");
	}

	@Override
	public void makeSound() {
		System.out.println("le meowitto");
		
	}

}
