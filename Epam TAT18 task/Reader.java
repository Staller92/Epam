

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by LIKHTAROVICH on 03.10.2017.
 */
public class Reader {
    private static final String INPUT = "Input text please and press Enter: ";
    private static final String EMPTY_STRING = "";

    public static String input() {
        System.out.println(INPUT);
        String text = EMPTY_STRING;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            text = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }


}
