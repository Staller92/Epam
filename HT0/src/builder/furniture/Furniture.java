package furniture;

/**
 * Created by LIKHTAROVICH on 28.10.2017.
 */
public class Furniture {

    private String name;
    private double maxSquare;
    private double minSquare;

    public String getName() {
        return name;
    }

    public double getMaxSquare() {
        return maxSquare;
    }

    public double getMinSquare() {
        return minSquare;
    }


    public Furniture(String name, double maxSquare) {
        this.name = name;
        this.maxSquare = maxSquare;

    }

    public Furniture(String name, double maxSquare, double minSquare) {
        this.name = name;
        this.maxSquare = maxSquare;
        this.minSquare = minSquare;
    }

    @Override
    public String toString() {
        return "\t\t\t" + getName() + " " + "(площадь "+ getMaxSquare()+" м^2)";
    }
}
