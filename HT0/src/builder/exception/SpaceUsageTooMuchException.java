package exception;

/**
 * Created by LIKHTAROVICH on 28.10.2017.
 */
public class SpaceUsageTooMuchException extends Exception {

    public SpaceUsageTooMuchException() {
        super("There is not enough free space in the room");
    }
}
