package furniture;

/**
 * Created by LIKHTAROVICH on 27.10.2017.
 */
public class Desk extends Furniture {
    public Desk(String name, double maxSquare) {
        super(name, maxSquare);
    }

    public Desk(String name, double maxSquare, double minSquare) {
        super(name, maxSquare, minSquare);
    }
}
