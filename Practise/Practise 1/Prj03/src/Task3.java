/**
 * Created by LIKHTAROVICH on 20.10.2017.
 */
public class Task3 {
    public static void main(String[] args) {
        if (!(args.length != 2)) {
            System.out.println("Square of rectangle = " + square(args[0], args[1]));
        } else {
            System.out.println("You didn't write length or width");
        }


    }

    public static long square(String a, String b) {

        long square = 0;
        long length = 0;
        long width = 0;
        try {
            length = Long.parseLong(a);
        } catch (NumberFormatException e) {
            System.out.println("Params of length is not correct");
            System.exit(-1);
        }
        try {
            width = Long.parseLong(b);
        } catch (NumberFormatException e) {
            System.out.println("Params of width is not correct");
            System.exit(-1);
        }

        if (length * width > Long.MAX_VALUE) {
            System.out.println("Sorry, params are to large");
            System.exit(-1);
        } else if (length < 0 || width < 0) {
            System.out.println("Params can't be negative");
            System.exit(-1);
        }

            square = length * width;

        return square;

    }

}
