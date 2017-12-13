package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CitiesBase {

    private static final int FIRST_LETTER_INDEX = 0;

    private static Map<Character, Set<String>> citiesMap = new HashMap<>();

    public static void createCitiesSet(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while (reader.ready()) {
                addCity(reader.readLine().toLowerCase().trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addCity(String city) {
        char firstLetter = city.charAt(FIRST_LETTER_INDEX);
        if (citiesMap.containsKey(firstLetter)) {
            citiesMap.get(firstLetter).add(city);
        } else {
            citiesMap.put(firstLetter, new HashSet<>());
            citiesMap.get(firstLetter).add(city);
        }
    }

    public static Map<Character, Set<String>> getCitiesMap () {
        return citiesMap;
    }
}