package input;

import animals.Animal;
import main.Main;

import java.io.*;
import java.util.List;

public class Input {
    public static double inputNumber(String str) {
        System.out.println(str);
        while (true) {
            String input = Main.in.nextLine();
            try {
                return Double.parseDouble(input.replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Please, try again to enter the number");
            }
        }
    }

    public static String inputFromConsole() {
        int tmp;
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while ((tmp = br.read()) != -1 && tmp != 10) {
                sb.append((char) tmp);
            }
        } catch (IOException e) {
            System.out.println("Input/output error");
        }
        return sb.toString();
    }

    public static List<Animal> readFromFile() {
        File file = new File("animals.csv");
        if (file.exists()) {
            System.out.println("This file is exists");
        } else {
            System.out.println("Not found");
        }

        try {
            int tmp;
            BufferedInputStream br =
                    new BufferedInputStream(new FileInputStream(file));
            while ((tmp = br.read()) != -1) {
                System.out.println(tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
