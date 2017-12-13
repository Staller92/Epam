package exceptions;

public class NegativeSidesException extends Exception{
    public NegativeSidesException() {
        super("The sides of the triangle must be positive");
    }
}
