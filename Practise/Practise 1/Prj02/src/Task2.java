import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LIKHTAROVICH on 20.10.2017.
 */
public class Task2 {
    public static void main(String[] args) {


        System.out.println("Please, enter Name Surname and Age");
        System.out.println("Press 'STOP' if you have finished");
        HashMap<String, Integer> map = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String x = null;

                x = reader.readLine();
                if (x.isEmpty()) {
                    System.out.println("You have entered nothing!");
                    System.out.println("Please, enter Name Surname and Age");
                    System.out.println("Press 'STOP' if you have finished");
                    continue;
                }

                map.put(findName(x), findAge(x));
                if (x.equalsIgnoreCase("STOP")) {
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        showStatistic(map);

    }


    public static int findAge(String data) {
        short age = 0;
        String[] info = data.split("[^0-9a-zA-Zа-яА-Я]+");

        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher;
        for (String x : info) {
            matcher = pattern.matcher(x);
            if (matcher.matches()) {
                age = Short.parseShort(x);
            }


        }
        return age;

    }

    public static String findName(String data) {
        String name = "";
        String[] info = data.split("[^a-zA-Zа-яА-Я]+");
        for (String n : info) {
            name = name + " " + n;
        }
        return name.trim();
    }


    public static void showStatistic(HashMap<String, Integer> map) {
        ArrayList<Integer> list = new ArrayList<>();

        for (HashMap.Entry<String, Integer> values : map.entrySet()) {

            list.add(values.getValue());
        }
        int maxAge = list.get(0);
        int minAge = list.get(0);
        int sumAge = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > maxAge) {
                maxAge = list.get(i);
            }
            if (list.get(i) < minAge) {
                minAge = list.get(i);
            }
            sumAge += list.get(i);
        }
        System.out.println("Max Age: " + maxAge);
        System.out.println("Min Age: " + minAge);
        System.out.printf("Averege Age: %.2f", (double) (sumAge / list.size()));

    }

}