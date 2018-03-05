package animals;

import input.Input;
import interfaces.ISoundable;
import interfaces.Jumpable;
import main.Main;

public class Cat extends Feline {
    private int swarmSize;

    public Cat(String nickName, double size) {
        super(nickName, size);
        this.type = "cat";
    }

    public static Cat createCat() {
        System.out.println("Enter nickname of your cat..");
        String nickName = Main.in.nextLine();
        double size = Input.inputNumber("Enter size of your cat..");
        return new Cat(nickName, size);
    }

    @Override
    public void makeSound() {
        System.out.println("Meow....");
    }

    @Override
    public double jump() {
        return (2 + 3 * getSize());
    }
}
