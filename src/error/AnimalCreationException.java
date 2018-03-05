package error;

public class AnimalCreationException extends Exception {
    public AnimalCreationException(){
        super("Animal can't be created!");
    }

    public AnimalCreationException(String message) {
        super(message);
    }
}
