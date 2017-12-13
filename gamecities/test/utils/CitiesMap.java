package utils;

import java.util.*;

public class CitiesMap {
    private static Map<Character, Set<String>> citiesMap = new HashMap<>();

    static {
        citiesMap.put('т', new HashSet<>(Arrays.asList("туров", "толочин")));
        citiesMap.put('ф', new HashSet<>(Arrays.asList("фаниполь")));
        citiesMap.put('х', new HashSet<>(Arrays.asList("хойники")));
        citiesMap.put('ч', new HashSet<>(Arrays.asList("чаусы")));
    }

    public static boolean isCreatedCityMapCorrect(Map<Character, Set<String>> actualCityMap) {
        return actualCityMap.equals(citiesMap);
    }

}
