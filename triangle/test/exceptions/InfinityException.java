package exceptions;

public class InfinityException extends Exception {
    public InfinityException() {
        super("The sides of the triangle must not be infinity");
    }
}
