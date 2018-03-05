package animals;

import interfaces.ISoundable;

public abstract class Predator extends Mammal /*implements Herbivore.ICounsumeCallBack */ {

    public Predator(String nickName, double size) {
        super(nickName, size);
        this.type = "predator";
    }

    public void consume(Herbivore prey) {
        this.setFill(this.getFill() + prey.getSize());
        prey.onConsumed();
        System.out.println(this.toString() + " consume the " + prey.toString());
    }

}
