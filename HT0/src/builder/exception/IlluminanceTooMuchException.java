package exception;

/**
 * Created by LIKHTAROVICH on 28.10.2017.
 */
public class IlluminanceTooMuchException extends Exception{

    public IlluminanceTooMuchException() {
        super("There is too much light in the room");
    }
}
