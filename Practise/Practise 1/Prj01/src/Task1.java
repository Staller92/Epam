/**
 * Created by LIKHTAROVICH on 20.10.2017.
 */


public class Task1 {

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        System.out.println("And hi again!");
        for (int i = 0; i < randomizer(5,50); i++) {
            System.out.print("!");
        }
    }

    public static int randomizer(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

}