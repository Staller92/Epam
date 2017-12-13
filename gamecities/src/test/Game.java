package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Game {

    private Character letterForPerson;
    private Set<String> citiesInGame;
    private Map<Character, Set<String>> allCities;

    public Game() {
        citiesInGame = new HashSet<>();
        allCities = CitiesBase.getCitiesMap();
    }

    public void play() {
        startGame();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String cityFromPerson = reader.readLine().toLowerCase().trim();
                if (!cityFromPerson.isEmpty()) {
                    if (checkCityInMap(cityFromPerson) && isFirstLetterCorrect(cityFromPerson.charAt(0))) {
                        char letterForComputer = getLastLetter(cityFromPerson);
                        if (allCities.containsKey(letterForComputer)) {
                            String cityForComputer = allCities.get(letterForComputer).toArray()[0].toString();
                            if (checkCityInMap(cityForComputer)) {
                                System.out.println(cityForComputer);
                                letterForPerson = getLastLetter(cityForComputer);
                            } else {
                                System.out.println("City is not found. You win!");
                                return;
                            }
                        } else {
                            System.out.println("City is not found. You win!");
                            return;
                        }
                    } else {
                        System.out.println("Incorrect city, you lose!");
                        return;
                    }
                } else {
                    System.out.println("You didn't input anything");
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startGame() {
        System.out.println("Hello, bro!\nInput a city to start the test.");

    }

    private boolean checkCityInMap(String city) {
        char firstLetter = city.charAt(0);
        if (allCities.containsKey(firstLetter)) {
            if (allCities.get(firstLetter).remove(city)) {
                if (allCities.get(firstLetter).isEmpty()) {
                    allCities.remove(firstLetter);
                }
                citiesInGame.add(city);
                return true;
            }
        }
        return false;
    }



    public char getLastLetter(String city) {
        char lastLetter = city.charAt(city.length() - 1);
        if (lastLetter == 'ь') {
            return city.charAt(city.length() - 2);
        } else {
            return lastLetter;
        }
    }

    private boolean isFirstLetterCorrect(char firstLetter) {
        if (letterForPerson == null || letterForPerson.equals(firstLetter)) {
            return true;
        } else {
            return false;
        }
    }
}
