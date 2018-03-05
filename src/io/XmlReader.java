package io;

import animals.*;
import main.ExtensibleCage;
import org.pmw.tinylog.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmlReader {
    public static Map<String, ExtensibleCage<? extends Animal>>
    convertFromXML(File file) {
        Map<String, ExtensibleCage<? extends Animal>> cages=null;
        try {
            cages = new HashMap<>();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            NodeList cageList = doc.getElementsByTagName(XmlSaver.TAG_NAME_CAGE);
            for (int i = 0; i < cageList.getLength(); i++) {
                Node cageNode = cageList.item(i);
                Element cageElement = (Element) cageNode;
                String attrCage = cageElement.getAttribute(XmlSaver.ATTRIBUTE_NAME_TYPE);
                ExtensibleCage cage;
                switch (attrCage) {
                    case "Mammal":
                        cage = new ExtensibleCage<Mammal>();
                        cages.put(Mammal.class.getSimpleName(), cage);
                        break;
                    case "Bird":
                        cage = new ExtensibleCage<Bird>();
                        cages.put(Bird.class.getSimpleName(), cage);
                        break;
                    case "Herbivore":
                        cage = new ExtensibleCage<Herbivore>();
                        cages.put(Herbivore.class.getSimpleName(), cage);
                        break;
                    default:
                        continue;
                }
                NodeList animalNodes = cageNode.getChildNodes();
                for (int j = 0; j < animalNodes.getLength(); j++) {
                    Node animalNode = animalNodes.item(j);
                    Element animalElement = (Element) animalNode;
                    String type = animalElement.getAttribute(XmlSaver.ATTRIBUTE_NAME_TYPE);
                    String size = animalElement.getAttribute(XmlSaver.ATTRIBUTE_NAME_SIZE);
                    String fill = animalElement.getAttribute(XmlSaver.ATTRIBUTE_NAME_FILL);
                    String nickName = animalElement.getTextContent();
                    Animal animal;
                    switch (type) {
                        case "Cat":
                            animal = new Cat(nickName, Double.parseDouble(size));
                            animal.setFill(Double.parseDouble(fill));
                            break;
                        case "Wolf":
                            animal = new Wolf(nickName, Double.parseDouble(size));
                            animal.setFill(Double.parseDouble(fill));
                            break;
                        case "Bird":
                            animal = new Bird(nickName, Double.parseDouble(size));
                            animal.setFill(Double.parseDouble(fill));
                            break;
                        case "Rabbit":
                            animal = new Rabbit(nickName, Double.parseDouble(size));
                            animal.setFill(Double.parseDouble(fill));
                            break;
                        default:
                            continue;
                    }
                    cage.addAnimal(animal);
                }
            }
        } catch (Exception e) {
            Logger.error("Error loading autosave");
        }
        return cages;
    }
}
