package test;


public class Main {

    public static void main(String[] args) {
        CitiesBase.createCitiesSet("src/test/resources/cities.txt");

        new Game().play();
    }
}
