package animals;

public abstract class Predatoc extends Mammal {
	boolean isScavenger;

	public Predatoc(String nickName, double size) {
		super(nickName, size);
	}

	public void consume(Herbivore prey) {
		this.feed(prey.getSize() * 5);
		prey.onConsumed();
		System.out.println(this + " סתוכ " + prey);
	}

}
