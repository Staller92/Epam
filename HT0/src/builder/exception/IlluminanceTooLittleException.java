package exception;

/**
 * Created by LIKHTAROVICH on 28.10.2017.
 */
public class IlluminanceTooLittleException extends Exception {

    public IlluminanceTooLittleException() {
        super("There is too little light in the room");
    }

}
