import exception.IlluminanceTooLittleException;
import exception.IlluminanceTooMuchException;
import exception.SpaceUsageTooMuchException;
import furniture.Furniture;
import illumination.Lightbulb;
import illumination.Window;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Locale;


/**
 * Created by LIKHTAROVICH on 27.10.2017.
 */
public class Room {

    private static final double PERCENT_OF_ALLOWABLE_SQUARE = 0.7;
    private static final int MAX_ILLUMINATION = 4000;
    private static final int MIN_ILLUMINATION = 300;
    private double allowableSquare;
    private double usedSquare;
    private String name;
    private double allSquare;
    private int illumination;
    private int amountOfWindows;
    private static int maxAmountOfWindows;

    static {
        maxAmountOfWindows = MAX_ILLUMINATION / Window.ILLUMINATION;
    }

    private ArrayList<Lightbulb> lightbulbsList = new ArrayList<>();
    private ArrayList<Furniture> furnitureList = new ArrayList<>();


    public String getName() {
        return name;
    }

    public Room(String name, double square, int amountOfWindows) {


        if (square <= 0) {
            System.out.println("Square of room must be positive");
        } else if (amountOfWindows > maxAmountOfWindows || amountOfWindows <0) {
            System.out.println("Amount of windows must be " + maxAmountOfWindows + " or less and must be positive");
        } else {
            this.name = name;
            this.allSquare = square;
            this.amountOfWindows = amountOfWindows;
            this.allowableSquare = PERCENT_OF_ALLOWABLE_SQUARE * square;
            this.illumination = Window.ILLUMINATION * amountOfWindows;
        }
    }

    public Room(String name, double square) {
        if (square <= 0) {
            System.out.println("You can't create room with such square");
        } else {
            this.name = name;
            this.allSquare = square;
            this.allowableSquare = PERCENT_OF_ALLOWABLE_SQUARE * square;
        }
    }

    public void add(Lightbulb lightbulb) throws IlluminanceTooMuchException {
        if (isIlluminationAvailable(lightbulb.getIllumination())) {
            lightbulbsList.add(lightbulb);
        }
    }

    public void add(Furniture furniture) throws SpaceUsageTooMuchException {
        if (isSpaceAvailable(furniture.getMaxSquare())) {
            furnitureList.add(furniture);
        }
    }


    private boolean isSpaceAvailable(double furnitureSquare) throws SpaceUsageTooMuchException {
        if (furnitureSquare <= allowableSquare) {
            usedSquare += furnitureSquare;
            return true;
        }
        throw new SpaceUsageTooMuchException();
    }

    private boolean isIlluminationAvailable(int lightBulbIllumination) throws IlluminanceTooMuchException {
        if (lightBulbIllumination <= MAX_ILLUMINATION - illumination) {
            illumination += lightBulbIllumination;
            return true;
        }
        throw new IlluminanceTooMuchException();
    }

    public boolean validateMinIllumination() throws IlluminanceTooLittleException {
        if (illumination > MIN_ILLUMINATION) {
            return true;
        }
        throw new IlluminanceTooLittleException();
    }

    public void describeRoom() {
        Formatter formatter = new Formatter(Locale.US);
        formatter.format("\t\tОсвещённость = %d лк (%d %s по %d лк; %s)\n" +
                        "\t\tПлощадь = %.1f м^2 (занято %.1f м^2, гарантированно свободно %.1f м^2 или %d %% площади)"
                , illumination, amountOfWindows, conjugation(amountOfWindows), Window.ILLUMINATION, getLightbulbs(), allSquare, usedSquare
                , getFreeSpace(), getPercentOfFreeSpace());

        System.out.println("\t" + name);
        System.out.println(formatter);

        System.out.println("\t\tМебель:");


        if (furnitureList.isEmpty()) {
            System.out.println("\t\tМебели нет");
        } else {
            for (Furniture furniture : furnitureList) {
                System.out.println(furniture);
            }
        }

    }

    public double getFreeSpace() {
        return allSquare - usedSquare;
    }

    public int getPercentOfFreeSpace() {
        return (int) ((1 - usedSquare / allSquare) * 100);
    }

    public String getLightbulbs() {
        String str = "";
        if (lightbulbsList.isEmpty()) {
            return "лампочек нет";
        } else {
            for (Lightbulb lightbulb : lightbulbsList) {
                str += "лампочка: " + lightbulb.getIllumination() + " лк ";
            }
        }
        return str.trim();
    }


    public static String conjugation(int amountOfWindows) {
        String text = "";
        if ((amountOfWindows / 10) % 10 == 1)
            return "окон";
        else {
            switch (amountOfWindows % 10) {
                case 1:
                    text = "окно";
                    break;
                case 0:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    text = "окон";
                    break;
                case 2:
                case 3:
                case 4:
                    text = "окна";
                    break;
            }
            return text;
        }
    }


}
