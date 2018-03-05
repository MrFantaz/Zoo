package io;

import animals.Animal;
import error.AnimalCreationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileImporter {
    public static List<Animal> importFromFile(String fileName)
            throws AnimalCreationException {
        List<Animal> animals = new LinkedList<>();
        File file = new File(fileName);
        int nextByte;
        int tmp = 0;
        try {
            FileReader fr = new FileReader(file);
            StringBuilder sb = new StringBuilder();
            while ((nextByte = fr.read()) != -1) {
                if (nextByte == 10 && tmp == 13) {
                    String str = sb.toString();
                    animals.add(Animal.convertFromString(str));
                    sb.setLength(0);
                } else {
                    sb.append((char) nextByte);
                }
                tmp = nextByte;
            }
            String str = sb.toString();
            animals.add(Animal.convertFromString(str));
        } catch (FileNotFoundException e) {
            System.out.println("File: " + fileName +
                    " not found!");
            return animals;
        } catch (IOException e) {
            System.out.println("File reading error!");
            return animals;
        }
        return animals;
    }
}
