package webster;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    private static String pathToCommands = null;
    private static String pathToLog = null;

    public static void main(String[] args) {
        initial(args);
        System.setProperty("log4j.configurationFactory", InitializerLog4j.class.getName());


        Reader reader = new Reader();
        reader.readInstructionsFromFile(pathToCommands);
        ArrayList<ArrayList<String>> commands = reader.getCommands();
        MajorPayne majorPayne = new MajorPayne();
        majorPayne.execute(commands);
    }


    public static void initial(String[] args) {
        if (args.length >= 2) {
            pathToCommands = args[0];
            pathToLog = args[1];
            InitializerLog4j.setPathLogFile(pathToLog);
            // checking for special symbols in path
            if (checkPathWithRegExp(args[0]) || checkPathWithRegExp(args[1])) {
                System.out.println("Don't use special symbols in path. Try again.");
                System.exit(-1);
            }
            if (args.length > 2) {
                System.out.println("You entered more than two params. Program will continue with first two params.");
            }

            if (pathToCommands.equals(pathToLog)) {
                System.out.println("You entered equals path for instructions and log file. Try again.");
                System.exit(-1);
            }

        } else {
            if (args.length == 1) {
                System.out.println("Not entered path to log file. Log will be saved to default place.");
                pathToCommands = args[0];
            }
            if (args.length == 0) {
                System.out.println("Not entered path to file with instructions and log file. Try again.");
                System.exit(-1);
            }
        }
    }

    public static boolean checkPathWithRegExp(String filePath) {
        Pattern pattern = Pattern.compile("[$&,;=?#|'<>^*()%!]");
        if (pattern.matcher(filePath).find()) {
            return true;
        }
        return false;
    }
}

