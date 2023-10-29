package exceptions;

public class InvalidEmailOrPasswordException extends RuntimeException{
    public InvalidEmailOrPasswordException(String message) {
        super(message);
    }
}
