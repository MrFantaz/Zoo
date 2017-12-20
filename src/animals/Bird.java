package animals;

public class Bird extends Animal {
	private boolean isFlying;
	private boolean isSwimming;

	public Bird(String nickName, double size) {
		super(nickName, size);

	}

	@Override
	public void makeSound() {
		System.out.println("ko-ko");
	}

}
