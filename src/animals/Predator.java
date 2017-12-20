package animals;

public abstract class Predator extends Mammal {
	boolean isScavenger;

	public Predator(String nickName, double size) {
		super(nickName, size);
	}

	public void consume(Herbivore prey) {
		this.feed(prey.getSize() * 5);
		prey.onConsumed();
		System.out.println(this + " סתוכ " + prey);
	}

}
