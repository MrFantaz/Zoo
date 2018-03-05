package error;

public class AnimalInvalidNameException extends AnimalCreationException {
    public AnimalInvalidNameException() {
        super("Animal name is invalid");
    }
}
