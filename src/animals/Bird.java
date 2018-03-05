package animals;

import input.Input;
import main.Main;

public class Bird extends Animal {
    private boolean isFlying;
    private boolean isSweeming;


    public Bird(String nickName, double size) {
        super(nickName, size);
        this.type = "bird";
    }

    public static Bird createBird() {
        System.out.println("Enter nickname of your bird..");
        String nickName = Main.in.nextLine();
        double size = Input.inputNumber("Enter size of your bird..");
        return new Bird(nickName, size);
    }

    @Override
    public void makeSound() {
        System.out.println("Karr....");
    }

    @Override
    public double jump() {
        return 3 * getSize();
    }

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean isFlying) {
        this.isFlying = isFlying;
    }

    public boolean isSweeming() {
        return isSweeming;
    }

    public void setSweeming(boolean isSweeming) {
        this.isSweeming = isSweeming;
    }
}
