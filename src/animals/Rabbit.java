package animals;

import input.Input;
import main.Main;

public class Rabbit extends Herbivore {

    public Rabbit(String nickName, double size) {
        super(nickName, size);
        this.type = "rabbit";
    }

    public static Rabbit createRabbit() {
        System.out.println("Enter nickname of your rabbit..");
        String nickName = Main.in.nextLine();
        double size = Input.inputNumber("Enter size of your rabbit..");
        return new Rabbit(nickName, size);
    }

    @Override
    public double jump() {
        return 3 + getSize() * 3;
    }

    @Override
    public void makeSound() {
        System.out.println("fir-fir....");
    }
}
