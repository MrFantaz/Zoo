/*Nastya has created this*/

package main;

import animals.*;
import error.AnimalCreationException;
import error.AnimalInvalidNameException;
import error.AnimalInvalidSizeException;
import food.Food;
import interfaces.ISoundable;
import io.FileImporter;

import java.io.File;
import java.util.Map;

import io.XmlReader;
import io.XmlSaver;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;
import threadLive.CheakFeel;

import java.util.*;

public class Main implements Animal.IAnimalDeadListener {

    public static final Scanner in = new Scanner(System.in);
    Map<String, ExtensibleCage<? extends Animal>> cages = null;

    private Main() {
        cages = XmlReader.convertFromXML(new File(".autosave"));
        if (cages == null) {
            cages = new HashMap<>();
            cages.put(Mammal.class.getSimpleName(), new ExtensibleCage<Mammal>());
            cages.put(Bird.class.getSimpleName(), new ExtensibleCage<Bird>());
            cages.put(Herbivore.class.getSimpleName(), new ExtensibleCage<Herbivore>());
        }
        CheakFeel cheakFeel = CheakFeel.getCheakFeeel();
        cheakFeel.running(cages);
        boolean userInput = true;
        while (userInput) {
            System.out.println("\nChoose the number to do anything...");
            System.out.println("0- import animals from file.csv");
            System.out.println("1- watch the fill of animal");
            System.out.println("2- to fill  the animal");
            System.out.println("3- to create  the animal");
            System.out.println("4- to play the competition");
            System.out.println("5- REMOVE animal\n");
            String answer = Main.in.nextLine();
            switch (answer) {
                case "0":
                    try {
                        List<Animal> arrAnimal = FileImporter.importFromFile
                                ("animals.csv");
                        for (Animal an : arrAnimal) {
                            sortToCage(an);
                            System.out.println(an.toString());
                        }
                    } catch (AnimalCreationException e) {
                        System.out.println("File converting error");
                    }
                    break;
                case "1":
                    showAnimalInfo();
                    break;
                case "2":
                    if (cages.get("Mammal").cage.isEmpty() && cages.get("Bird").cage.isEmpty()
                            && cages.get("Herbivore").cage.isEmpty()) {
                        System.out.println("The animal is absent in cages ");
                    } else {
                        feedAnimal();
                    }
                    break;
                case "3": //adding animal
                    Animal newAnimal = null;
                    try {
                        newAnimal = createAnimal();

                    } catch (InputMismatchException e) {
                        System.out.println("NumberFormatException. Enter int instead of string");
                    } catch (AnimalInvalidNameException e) {
                        System.out.println(e.getMessage());
                    } catch (AnimalInvalidSizeException e) {
                        System.out.println(e.getMessage());
                    } catch (AnimalCreationException e) {
                        System.out.println(e.getMessage());
                    }
                    if (newAnimal == null) {
                        continue;
                    }
                    sortToCage(newAnimal);
                    break;
                case "4":
                    jumpAll();
                    break;
                case "5":
                    if (cages.get(Mammal.class.

                            getSimpleName()).cage.size() == 0 &&
                            cages.get(Bird.class.

                                    getSimpleName()).cage.size() == 0 &&
                            cages.get(Herbivore.class.

                                    getSimpleName()).cage.isEmpty())

                    {
                        System.out.println("All animals are absent today");
                        break;
                    } else

                    {
                        removeAnimal();
                    }
                    break;
                default:
                    userInput = false;
                    cheakFeel.stopWork(false);
                    break;
            }
        }
        XmlSaver.save(cages);
    }

    public static void main(String[] args) {
        Configurator.defaultConfig()
                .writer(new ConsoleWriter())
                .addWriter(new FileWriter("foo.bar"))
                .activate();

        Logger.warn("Hello Tinylog");
        new Main();

        in.close();
    }

    public void sortToCage(Animal animal) {
        if (animal instanceof Bird) {
            ExtensibleCage<Bird> cage =
                    (ExtensibleCage<Bird>) cages.get(Bird.class.getSimpleName());
            cage.addAnimal((Bird) animal);

            System.out.println("The size of cageBird = " +
                    cages.get(Bird.class.getSimpleName()).cage.size());
        } else if (animal instanceof Predator) {
            ((ExtensibleCage<Mammal>) cages.get(Mammal.class.getSimpleName()))
                    .addAnimal((Predator) animal);

            System.out.println("The size of cagePredator = " +
                    cages.get(Mammal.class.getSimpleName()).cage.size());
        } else {
            double rndCage = Math.random();
            if (rndCage >= 0.6 && rndCage < 1) {
                ((ExtensibleCage<Herbivore>) cages.get(Herbivore.class.getSimpleName()))
                        .addAnimal((Herbivore) animal);

                System.out.println("The size of cageHerbivore = " +
                        cages.get(Herbivore.class.getSimpleName()).cage.size());
            } else {
                ((ExtensibleCage<Mammal>) cages.get(Mammal.class.getSimpleName()))
                        .addAnimal((Mammal) animal);

                System.out.println("The size of cagePredator = " +
                        cages.get(Mammal.class.getSimpleName()).cage.size());
            }
        }
    }


    @Override
    public void onAnimalDead(Animal animal) {
        //System.out.println("animal " + animal.getNickName() + " is dead");
    }

    public Animal createAnimal() throws AnimalCreationException {
        Animal animal = null;
        for (; ; ) {
            System.out.println("Choose the number to create...\n1- WOLF\n2- cat\n3-bird\n4-rabbit" +
                    "\nor enter r - to return");
            String answer = Main.in.nextLine();
            switch (answer) {
                case "1":
                    Wolf wolf = Wolf.createWolf();
                    // wolf.setAnimalDeadListener(this);
                    animal = wolf;
                    if (animal != null && animal.getNickName().isEmpty()) {
                        throw new AnimalInvalidNameException();
                    }
                    if (animal.getSize() <= 0) {
                        throw new AnimalInvalidSizeException();
                    }
                    break;
                case "2":
                    Cat cat = Cat.createCat();
                    animal = cat;
                    if (animal != null && animal.getNickName().isEmpty()) {
                        throw new AnimalInvalidNameException();
                    }
                    if (animal.getSize() <= 0) {
                        throw new AnimalInvalidSizeException();
                    }
                    break;
                case "3":
                    Bird bird = Bird.createBird();
                    animal = bird;
                    if (animal != null && animal.getNickName().isEmpty()) {
                        throw new AnimalInvalidNameException();
                    }
                    if (animal.getSize() <= 0) {
                        throw new AnimalInvalidSizeException();
                    }
                    break;
                case "4":
                    Rabbit rabbit = Rabbit.createRabbit();
                    animal = rabbit;
                    if (animal != null && animal.getNickName().isEmpty()) {
                        throw new AnimalInvalidNameException();
                    }
                    if (animal.getSize() <= 0) {
                        throw new AnimalInvalidSizeException();
                    }
                    break;
                case "r":
                    animal = null;
            }

            break;

        }

        return animal;
    }

    public void removeAnimal() {
        int indRemoveAnimal;
        System.out.println("\nFrom which cage you want to feed animal? \n1-mammal \t2-bird \t3-rabbit");
        int numberCage = in.nextInt();
        switch (numberCage) {
            case 1:
                if (cages.get(Mammal.class.getSimpleName()).cage.size() == 0) {
                    System.out.println("Error");
                    return;
                } else {
                    System.out.println("\nWhich animal you want to remove? From 1 to " +
                            cages.get(Mammal.class.getSimpleName()).cage.size());
                    indRemoveAnimal = in.nextInt() - 1;
                    in.nextLine();
                    if (indRemoveAnimal + 1 > cages.get(Mammal.class.getSimpleName()).cage.size()) {
                        return;
                    }
                    cages.get(Mammal.class.getSimpleName()).removeAnimal(indRemoveAnimal);
                }
                break;
            case 2:
                if (cages.get(Bird.class.getSimpleName()).cage.size() == 0) {
                    System.out.println("Error");
                    return;
                } else {
                    System.out.println("\nWhich animal you want to remove?  From 1 to " +
                            cages.get(Bird.class.getSimpleName()).cage.size());
                    indRemoveAnimal = in.nextInt() - 1;
                    in.nextLine();
                    if (indRemoveAnimal + 1 > cages.get(Bird.class.getSimpleName()).cage.size()) {
                        return;
                    }
                    cages.get(Bird.class.getSimpleName()).removeAnimal(indRemoveAnimal);
                }
                break;
            case 3:
                if (cages.get(Herbivore.class.getSimpleName()).cage.size() == 0) {
                    System.out.println("Error");
                    return;
                } else {
                    System.out.println("\nWhich animal you want to remove?  From 1 to  " +
                            cages.get(Herbivore.class.getSimpleName()).cage.size());
                    indRemoveAnimal = in.nextInt() - 1;
                    in.nextLine();
                    if (indRemoveAnimal + 1 > cages.get(Herbivore.class.getSimpleName()).cage.size()) {
                        return;
                    }
                    cages.get(Herbivore.class.getSimpleName()).removeAnimal(indRemoveAnimal);
                }
                break;
        }
        showAnimalInfo();
        System.out.println("New size of cagePredator = " + cages.get(Mammal.class.getSimpleName()).cage.size() +
                "\nNew size of cageBird = " + cages.get(Bird.class.getSimpleName()).cage.size() +
                "\nNew size of cageHerbivore = " + cages.get(Herbivore.class.getSimpleName()).cage.size());
    }

    public void showAnimalInfo() {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = cages.keySet();
        for (String key : keys) {
            sb.append(key);
            sb.append(" \n");
            sb.append(cages.get(key).toString());
            sb.append("\n");
        }
        System.out.println(sb.toString());
        dead();
    }

    private void dead() {
        for (int i = 0; i < cages.get("Mammal").cage.size(); i++) {
            if (cages.get("Mammal").cage.get(i).getFill() < 0) {
                onAnimalDead(cages.get("Mammal").cage.get(i));
                cages.get("Mammal").removeAnimal(i);
            }
        }
        for (int i = 0; i < cages.get("Bird").cage.size(); i++) {
            if (cages.get("Bird").cage.get(i).getFill() < 0) {
                onAnimalDead(cages.get("Bird").cage.get(i));
                cages.get("Bird").removeAnimal(i);
            }
        }
        for (int i = 0; i < cages.get("Herbivore").cage.size(); i++) {
            if (cages.get("Herbivore").cage.get(i).getFill() < 0) {
                onAnimalDead(cages.get("Herbivore").cage.get(i));
                cages.get("Herbivore").removeAnimal(i);
            }
        }
    }

    public void feedAnimal() {
        double countOfFood;
        for (String key : cages.keySet()) {
            System.out.println("Size of " + key + " " + cages.get(key).cage.size());
        }
        System.out.println("\nFrom which cage you want to feed animal? \n1-mammal \t2-bird \t3-herbivore");
        int numberCage = in.nextInt();
        int numberAnimal;
        switch (numberCage) {
            case 1:
                if (cages.get(Mammal.class.getSimpleName()).cage.size() == 0) {
                    return;
                } else {
                    System.out.println("\nWhich animal you want to feed? From 1 to " +
                            cages.get(Mammal.class.getSimpleName()).cage.size());
                    numberAnimal = in.nextInt() - 1;
                    if (numberAnimal + 1 > cages.get(Mammal.class.getSimpleName()).cage.size()) {
                        return;
                    }
                    Food food;
                    if (Math.random() > 0.5) {
                        food = Food.MEAT;
                    } else {
                        food = Food.CARROT;
                    }
                    cages.get(Mammal.class.getSimpleName()).cage.get(numberAnimal).feed(food);
                }
                break;
            case 2:
                if (cages.get(Bird.class.getSimpleName()).cage.size() == 0) {
                    return;
                } else {
                    System.out.println("\nWhich animal you want to feed? From 1 to " +
                            cages.get(Bird.class.getSimpleName()).cage.size());
                    numberAnimal = in.nextInt() - 1;
                    if (numberAnimal + 1 > cages.get(Bird.class.getSimpleName()).cage.size()) {
                        return;
                    }
                    Food food;
                    if (Math.random() > 0.5) {
                        food = Food.MEAT;
                    } else {
                        food = Food.CARROT;
                    }
                    cages.get(Bird.class.getSimpleName()).cage.get(numberAnimal).feed(food);
                }
                break;
            case 3:
                if (cages.get(Herbivore.class.getSimpleName()).cage.isEmpty()) {
                    return;
                } else {
                    System.out.println("\nWhich animal you want to feed? From 1 to " +
                            cages.get(Herbivore.class.getSimpleName()).cage.size());
                    numberAnimal = in.nextInt() - 1;
                    if (numberAnimal + 1 > cages.get(Herbivore.class.getSimpleName()).cage.size()) {
                        return;
                    }
                    Food food;
                    if (Math.random() > 0.5) {
                        food = Food.MEAT;
                    } else {
                        food = Food.CARROT;
                    }
                    cages.get(Herbivore.class.getSimpleName()).cage.get(numberAnimal).feed(food);
                }
                break;
        }
        showAnimalInfo();
    }

    public void jumpAll() {
        if (cages.get(Mammal.class.getSimpleName()).cage.isEmpty() &&
                cages.get(Bird.class.getSimpleName()).cage.isEmpty() &&
                cages.get(Herbivore.class.getSimpleName()).cage.isEmpty()) {
            System.out.println("Error///In competation no one play!!");
            return;
        }
        int maxElement = 0;
        double maxLenght = 0;
        if (!cages.get(Mammal.class.getSimpleName()).cage.isEmpty()) {
            for (int i = 0; i < cages.get(Mammal.class.getSimpleName()).cage.size(); i++) {
                if (cages.get(Mammal.class.getSimpleName()).cage.get(i).jump() > maxLenght) {
                    maxLenght = cages.get(Mammal.class.getSimpleName()).cage.get(i).jump();
                    maxElement = i;
                }
            }
            System.out.println("Дальше всех in cageMammal прыгает " +
                    cages.get(Mammal.class.getSimpleName()).cage.get(maxElement).toString() +
                    " jump = " + maxLenght);
        } else {
            System.out.println("Among animals from cageMammal no one animal");
        }
        if (!cages.get(Bird.class.getSimpleName()).cage.isEmpty()) {
            for (int i = 0; i < cages.get(Bird.class.getSimpleName()).cage.size(); i++) {
                if (cages.get(Bird.class.getSimpleName()).cage.get(i).jump() > maxLenght) {
                    maxLenght = cages.get(Bird.class.getSimpleName()).cage.get(i).jump();
                    maxElement = i;
                }
            }
            System.out.println("Дальше всех in cageBird прыгает " +
                    cages.get(Bird.class.getSimpleName()).cage.get(maxElement).toString() +
                    " jump = " + maxLenght);
        } else {
            System.out.println("Among animals from cageBird no one animal");
        }
        if (!cages.get(Herbivore.class.getSimpleName()).cage.isEmpty()) {
            for (int i = 0; i < cages.get(Herbivore.class.getSimpleName()).cage.size(); i++) {
                if (cages.get(Herbivore.class.getSimpleName()).cage.get(i).jump() > maxLenght) {
                    maxLenght = cages.get(Herbivore.class.getSimpleName()).cage.get(i).jump();
                    maxElement = i;
                }
            }
            System.out.println("Дальше всех in cageHerbivore прыгает " +
                    cages.get(Herbivore.class.getSimpleName()).cage.get(maxElement).toString() +
                    " jump = " + maxLenght);
        } else {
            System.out.println("Among animals from cageHerbivore no one animal");
        }
    }

    public static void displayAnimals(Object[] animal) {
        for (int i = 0; i < animal.length; i++) {
            System.out.println((i + 1) + " " + animal[i].toString());
        }
    }

    public static void soundAll(ISoundable[] animals) {
        for (ISoundable s : animals) {
            s.makeSound();
        }
    }

}
