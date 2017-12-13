
import exception.IlluminanceTooLittleException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by LIKHTAROVICH on 27.10.2017.
 */
public class Building {
    private String name;
    private HashMap<String, Room> roomMap = new HashMap<>();


    public Building(String name) {
        this.name = name;
    }


    public void addRoom(Room room) {
        if (roomMap.containsKey(room.getName())) {
            System.out.println("Room already exists");
        } else {
            roomMap.put(room.getName(), room);
        }
    }

    public Room getRoom(String name) {
        if (!roomMap.containsKey(name)) {
            System.out.println("Room is not exist");
            return null;
        } else {
            return roomMap.get(name);
        }
    }

    public void describe() {
        Iterator<HashMap.Entry<String, Room>> iterator = roomMap.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry<String, Room> pair = iterator.next();
            try {
                pair.getValue().validateMinIllumination();
            } catch (IlluminanceTooLittleException e) {
                iterator.remove();
                System.out.println(pair.getKey() + " was deleted. There is too little light in the room.");
            }
        }
        System.out.println(name);
        for (HashMap.Entry<String, Room> pair : roomMap.entrySet()) {

            pair.getValue().describeRoom();

        }

    }

}
