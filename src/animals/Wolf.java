package animals;

import input.Input;
import main.Main;

public class Wolf extends Canine {

    public Wolf(String nickName, double size) {
        super(nickName, size);
        this.type = "wolf";
    }

    public static Wolf createWolf() {
        System.out.println("Enter nickname of your wolf..");
        String nickName = Main.in.nextLine();
        double size = Input.inputNumber("Enter size of your wolf..");
        return new Wolf(nickName, size);

    }

    @Override
    public void makeSound() {
        System.out.println("Rrrrrr!!!!!");
    }

    @Override
    public double jump() {
        return super.jump();
    }
}
