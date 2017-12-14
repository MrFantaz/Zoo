package main;

import java.util.Scanner;
import animals.*;
import animals.Animal.IAnimalDeadListener;
import interfaceZoo.Jumpable;
import interfaceZoo.Soundable;
import java.util.List;

public class Main implements IAnimalDeadListener {
	public static final Scanner sc = new Scanner(System.in);
	private ExtensibleCage<Predatoc> predatocCage;
	private ExtensibleCage<Bird> birdCage;

	private Main() {
		predatocCage = new ExtensibleCage<>();
		boolean userInput = true;
		while (userInput) {

			System.out.println(

					"1) Инфа  \n 2) Покормить животное \n 3) Создать животное \n 4) Провести турнир по прыжкам среди животных \n 5) Удалить животного \n 6) выход");
			String answer = sc.nextLine();
			List<Predatoc> cage2 = predatocCage.getCage();
			switch (inputValidation(answer)) {
			case 1: {
				showAnimalInfo();
				break;
			}
			case 2: {
				feedAnimal(cage2);
				break;
			}
			case 3: {
				System.out.println("Создание животного...");
				Animal ani = createAnimal();

				if (ani != null) {
					if (ani instanceof Predatoc) {
						System.out.println("Номер животного в клетке " + predatocCage.addAnimal((Predatoc) ani));

					} else if (ani instanceof Bird) {
						System.out.println("Номер животного в клетке " + birdCage.addAnimal((Bird) ani));
					}
				}
				break;
			}
			case 4: {
				jumpAll(cage2);

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

	public void jumpAll(List<Animal> animals) {
		if (animals.size() == 0) {
			System.out.println("Прыгать некому");
			return;
		}
		double maxJump = 0;
		int maxElement = 0;
		for (int i = 0; i < animals.size(); i++) {
			if (maxJump < animals.get(i).jump()) {
				maxJump = animals.get(i).jump();
				maxElement = i;
			}
		}
		if (animals.size() == 1) {
			System.out.println("Один в поле воин!?");
		} else {
			System.out.println("Максимальная длина= " + maxJump + " " + animals.get(maxElement).toString());
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
		System.out.println("1) Волк, 2) Кот 3) Птица");
		String answer = sc.nextLine();
		boolean userInput = true;
		while (userInput) {
			if (inputValidation(answer) == 1) {
				System.out.println("Создание Волка...");
				System.out.println("Ввод клички");
				String nickName = sc.nextLine();
				System.out.println("Ввод размера");
				int size = sc.nextInt();
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
				int size = sc.nextInt();
				sc.nextLine();
				Animal cat = new DomesticCat(nickName, size);
				userInput = false;
				cat.setAnimalDeadListener(this);
				return cat;
			} else if (inputValidation(answer) == 3) {
				System.out.println("Создание Птица...");
				System.out.println("Ввод клички");
				String nickName = sc.nextLine();
				System.out.println("Ввод размера");
				int size = sc.nextInt();
				sc.nextLine();
				Animal cat = new Bird(nickName, size);
				userInput = false;
				cat.setAnimalDeadListener(this);
				return cat;
			} else {
				userInput = false;
				System.out.println("Error");
			}

		}
		return null;
	}

	public void showAnimalInfo() {
		StringBuilder sd = new StringBuilder();
		if(predatocCage.getCage().size()==0) {
			sd.append("Клетка пуста");
		}else  {
				sd.append("Клетка для хижника");
				for(Predatoc p: predatocCage.getCage()) {
					sd.append("\t");
					System.out.println(sd.append(p.toString()));
				}
				
			}
	}
	public void feedAnimal(List<Animal> ani) {
		if (ani.size() == 0) {
			System.out.println("Error.Create animal's...");
		} else {
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

		}
	}

	public void removeAnimal() {
		if (predatocCage.getCage().size() == 0) {
			System.out.println("Error");
			return;
		}
		System.out.println("Какое животное удалить? с 1 до " + predatocCage.getCage().size());
		int index = sc.nextInt();
		sc.nextLine();
		predatocCage.removeAnimal(index - 1);
		System.out.println("Новый размер клетки " + predatocCage.getCage().size());
	}

	@Override
	public void onAnimalDead(Animal animal) {
		System.out.println("Оно умерло " + animal.toString());
		for (int i = 0; i < predatocCage.getCage().size(); i++) {
			if (predatocCage.getCage().get(i).equals(animal)) {

				predatocCage.removeAnimal(i);
				System.out.println("Новый размер клетки " + predatocCage.getCage().size());
				return;
			}
		}

	}

}
