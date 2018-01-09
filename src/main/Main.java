package main;

import java.util.Scanner;
import animals.*;
import animals.Animal.IAnimalDeadListener;
import interfaceZoo.Jumpable;
import interfaceZoo.Soundable;
import java.util.List;

public class Main implements IAnimalDeadListener {
	public static final Scanner sc = new Scanner(System.in);
	private ExtensibleCage<Mammal> mammalCage;
	private ExtensibleCage<Bird> birdCage;
	private ExtensibleCage<Herbivore> herbivoreCage;

	private Main() {
		mammalCage = new ExtensibleCage<>();
		birdCage = new ExtensibleCage<>();
		herbivoreCage = new ExtensibleCage<>();
		boolean userInput = true;
		while (userInput) {

			System.out.println(

					"1) Инфа  \n 2) Покормить животное \n 3) Создать животное \n 4) Провести турнир по прыжкам среди животных \n 5) Удалить животное \n 6) выход");
			String answer = sc.nextLine();
			List<Mammal> cage2 = mammalCage.getCage();
			List<Bird> cage3 = birdCage.getCage();
			List<Herbivore> cage1 = herbivoreCage.getCage();

			switch (inputValidation(answer)) {
			case 1: {
				showAnimalInfo();
				break;
			}
			case 2: {
				feedAnimal();
				break;
			}
			case 3: {
				System.out.println("Создание животного...");
				Animal ani = createAnimal();

				if (ani != null) {
					if (ani instanceof Predatoc) {
						System.out.println("Номер животного в клетке " + mammalCage.addAnimal((Mammal) ani));

					} else if (ani instanceof Bird) {
						System.out.println("Номер птицы в клетке " + birdCage.addAnimal((Bird) ani));
					} else if (ani instanceof Herbivore) {
						Herbivore herbivore = (Herbivore) ani;
						if (Math.random() >= .5) {
							herbivoreCage.addAnimal(herbivore);
							System.out.println("Размер клетки для травоядных " + herbivoreCage.getCage().size());
						} else {
							mammalCage.addAnimal(herbivore);
							System.out.println("Размер клетки для mammal " + mammalCage.getCage().size());
						}
					}
				}
				break;
			}
			case 4: {
				jumpAll();

				break;
			}
			case 5: {
				removeAnimal();
				break;
			}
			case 6: {
				userInput = false;
				System.out.println("Конец программы");
			}
			}

		}

	}

	public static void main(String[] args) {

		new Main();
		sc.close();
	}

	public static void displayAnimals(Animal[] ani) {
		for (int i = 0; i < ani.length; i++)
			System.out.println(ani[i].toString());
	}

	public void SoundAll(Soundable[] cat) {
		for (int i = 0; i < cat.length; i++) {

			cat[i].makeSound();
		}
	}

	public void jumpAll() {
		if (mammalCage.getCage().size() == 0) {
			System.out.println("Прыгать некому");
			return;
		}
		double maxJump = 0;
		int maxElement = 0;
		for (int i = 0; i < mammalCage.getCage().size(); i++) {
			if (maxJump < mammalCage.getCage().get(i).jump()) {
				maxJump = mammalCage.getCage().get(i).jump();
				maxElement = i;
			}
		}
		if (mammalCage.getCage().size() == 1) {
			System.out.println("Один в поле воин!?");
		} else {
			System.out
					.println("Максимальная длина= " + maxJump + " " + mammalCage.getCage().get(maxElement).toString());
		}
	}

	public static int inputValidation(String answer) {

		if (answer.equals("1")) {
			return 1;
		} else if (answer.equals("2")) {
			return 2;
		} else if (answer.equals("3")) {
			return 3;
		} else if (answer.equals("4")) {
			return 4;
		} else if (answer.equals("5")) {
			return 5;
		} else {
			return 6;
		}

	}

	public Animal createAnimal() {
		System.out.println("1) Волк, 2) Кот 3) Ворона 4) Кролик");
		String answer = sc.nextLine();
		boolean userInput = true;
		while (userInput) {
			if (inputValidation(answer) == 1) {
				System.out.println("Создание Волка...");
				System.out.println("Ввод клички");
				String nickName = sc.nextLine();
				System.out.println("Ввод размера");
				double size = sc.nextDouble();
				sc.nextLine();
				Animal wolf = new ForestWolf(nickName, size);
				userInput = false;
				wolf.setAnimalDeadListener(this);
				return wolf;
			} else if (inputValidation(answer) == 2) {
				System.out.println("Создание Кота...");
				System.out.println("Ввод клички");
				String nickName = sc.nextLine();
				System.out.println("Ввод размера");
				double size = sc.nextDouble();
				sc.nextLine();
				Animal cat = new DomesticCat(nickName, size);
				userInput = false;
				cat.setAnimalDeadListener(this);
				return cat;
			} else if (inputValidation(answer) == 3) {
				System.out.println("Создание Вороны...");
				System.out.println("Ввод клички");
				String nickName = sc.nextLine();
				System.out.println("Ввод размера");
				double size = sc.nextDouble();
				sc.nextLine();
				Animal cat = new Raven(nickName, size);
				userInput = false;
				cat.setAnimalDeadListener(this);
				return cat;
			} else if (inputValidation(answer) == 4) {
				System.out.println("Создание Кролика...");
				System.out.println("Ввод клички");
				String nickName = sc.nextLine();
				System.out.println("Ввод размера");
				double size = sc.nextDouble();
				sc.nextLine();
				Animal rabbit = new Rabbit(nickName, size);
				userInput = false;
				rabbit.setAnimalDeadListener(this);
				return rabbit;
			} else {
				userInput = false;
				System.out.println("Error");
			}

		}
		return null;
	}

	public void showAnimalInfo() {
		StringBuilder sd = new StringBuilder();

		if (mammalCage.getCage().size() == 0) {
			sd.append("Клетка пуста\n");
		} else {
			sd.append("Клетка для хищника");
			for (Mammal p : mammalCage.getCage()) {
				sd.append("\t");
				sd.append(p.toString());
				sd.append("\n");
			}

		}
		if (birdCage.getCage().size() == 0) {
			sd.append("Клетка пуста\n");
		} else {
			sd.append("Клетка для птицы");
			for (Bird p : birdCage.getCage()) {
				sd.append("\t");
				sd.append(p.toString());
				sd.append("\n");
			}
		}
		if (herbivoreCage.getCage().size() == 0) {
			sd.append("Клетка пуста\n");
		} else {
			sd.append("Клетка для кролика");
			for (Mammal p : herbivoreCage.getCage()) {
				sd.append("\t");
				sd.append(p.toString());
				sd.append("\n");
			}
		}
		System.out.println(sd.toString());
	}

	public void feedAnimal() {
		List<Mammal> ani = mammalCage.getCage();
		List<Bird> ani1 = birdCage.getCage();
		List<Herbivore> ani2 = herbivoreCage.getCage();
		if (ani.size() == 0) {
			System.out.println("Error.Create animal's...");

		}
		System.out.println("Кого покормить: 1-животное, 2-птицу, 3-кролика?");

		if (sc.nextInt() == 1) {
			System.out.println("Кого будем кормить? от 1 до " + (ani.size()));
			int numberAni = sc.nextInt() - 1;
			sc.nextLine();
			if (numberAni > (ani.size() - 1)) {
				System.out.println("Input Error..");
			} else {
				System.out.println(ani.get(numberAni).toString() + " Его сытость " + ani.get(numberAni).getFill());
				System.out.println("Сколько дать ему еды?");
				double amountFood = sc.nextDouble();
				sc.nextLine();
				System.out.println("Его Сытость " + ani.get(numberAni).feed(amountFood));
				System.out.println("Животное покормленно");
			}

		} else if (sc.nextInt() == 2) {
			System.out.println("Кого будем кормить? от 1 до " + (ani1.size()));
			int numberAni = sc.nextInt() - 1;
			sc.nextLine();
			if (numberAni > (ani1.size() - 1)) {
				System.out.println("Input Error..");
			} else {
				System.out.println(ani1.get(numberAni).toString() + " её сытость " + ani1.get(numberAni).getFill());
				System.out.println("Сколько дать ей еды?");
				double amountFood = sc.nextDouble();
				sc.nextLine();
				System.out.println("Её сытость " + ani1.get(numberAni).feed(amountFood));
				System.out.println("птица покормлена");
			}
		} else if (sc.nextInt() == 3) {
			System.out.println("Кого будем кормить? от 1 до " + (ani2.size()));
			int numberAni = sc.nextInt() - 1;
			sc.nextLine();
			if (numberAni > (ani2.size() - 1)) {
				System.out.println("Input Error..");
			} else {
				System.out.println(ani2.get(numberAni).toString() + " Его сытость " + ani2.get(numberAni).getFill());
				System.out.println("Сколько дать ему еды?");
				double amountFood = sc.nextDouble();
				sc.nextLine();
				System.out.println("Его Сытость " + ani2.get(numberAni).feed(amountFood));
				System.out.println("Животное покормленно");
			}

		}

	}

	public void removeAnimal() {

		if (mammalCage.getCage().size() == 0) {
			System.out.println("Error");
			return;
		}
		System.out.println("Кого удалить: 1-животное, 2-птицу, 3-кролика?");
		if (sc.nextInt() == 1) {
			System.out.println("Какое животное удалить? с 1 до " + mammalCage.getCage().size());
			int index = sc.nextInt();
			sc.nextLine();
			mammalCage.removeAnimal(index - 1);
			System.out.println("Новый размер клетки " + mammalCage.getCage().size());
		} else if (sc.nextInt() == 2) {
			System.out.println("Какую птицу удалить? с 1 до " + birdCage.getCage().size());
			int index = sc.nextInt();
			sc.nextLine();
			birdCage.removeAnimal(index - 1);
			System.out.println("Новый размер клетки " + birdCage.getCage().size());
		} else if (sc.nextInt() == 3) {
			System.out.println("Какое животное удалить? с 1 до " + herbivoreCage.getCage().size());
			int index = sc.nextInt();
			sc.nextLine();
			herbivoreCage.removeAnimal(index - 1);
			System.out.println("Новый размер клетки " + herbivoreCage.getCage().size());
		}
	}

	@Override
	public void onAnimalDead(Animal animal) {
		System.out.println("Оно умерло " + animal.toString());
		if (animal instanceof Predatoc) {
			for (int i = 0; i < mammalCage.getCage().size(); i++) {
				if (mammalCage.getCage().get(i).equals(animal)) {

					mammalCage.removeAnimal(i);
					System.out.println("Новый размер клетки " + mammalCage.getCage().size());
					return;
				}
			}
		} else {
			for (int i = 0; i < birdCage.getCage().size(); i++) {
				if (birdCage.getCage().get(i).equals(animal)) {

					birdCage.removeAnimal(i);
					System.out.println("Новый размер клетки " + birdCage.getCage().size());
					return;
				}

			}
		}
	}
}
