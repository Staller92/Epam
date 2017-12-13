import exception.IlluminanceTooMuchException;
import exception.SpaceUsageTooMuchException;
import furniture.Armchair;
import furniture.Desk;
import illumination.Lightbulb;

/**
 * Created by LIKHTAROVICH on 27.10.2017.
 */
public class Main {
    public static void main(String[] args) {

        Building building2 = new Building("Здание 2");
        Lightbulb lightbulb1 = new Lightbulb(15);
        Lightbulb lightbulb2 = new Lightbulb(5);
        Desk desk1 = new Desk("Стол 1", 3);
        Armchair armchair1 = new Armchair("Кресло 1", 3);
        Armchair armchair2 = new Armchair("Кресло 2", 4);
        Room room = new Room("Кухня", 25,1);
        Room room2 = new Room("Кухня2", 25,4);
        try {
            room.add(desk1);
        } catch (SpaceUsageTooMuchException e) {
            e.printStackTrace();
        }
        try {
            room.add(armchair1);
        } catch (SpaceUsageTooMuchException e) {
            e.printStackTrace();
        }
        try {
            room.add(armchair2);
        } catch (SpaceUsageTooMuchException e) {
            e.printStackTrace();
        }

        try {
            room.add(lightbulb1);
        } catch (IlluminanceTooMuchException e) {
            e.printStackTrace();
        }
        try {
            room.add(lightbulb2);
        } catch (IlluminanceTooMuchException e) {
            e.printStackTrace();
        }
        building2.addRoom(room);
        building2.addRoom(room2);
        building2.describe();


    }
}
