package webster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {


    private static final Logger log = LogManager.getLogger(Reader.class.getName());
    private static final String REGEX = "(((\\w+)\\s+\"(.+)\"\\s+\"([0-9.,]+)\")|((\\w+)\\s+\"(.+)\"))";

    private ArrayList<ArrayList<String>> commands = new ArrayList<>();

    public ArrayList<ArrayList<String>> getCommands() {
        return commands;
    }

    public Reader readInstructionsFromFile(String path){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while (reader.ready()) {
                String command = reader.readLine().trim();
                addCommands(command);
            }
            reader.close();

        } catch (IOException e) {
            log.debug("EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
            System.exit(-1);
        }
        return this;
    }


    private void addCommands(String command) {

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(command);
        if (m.matches()) {

            if (m.group(7) == null && m.group(8) == null) {
                commands.add(new ArrayList<String>(Arrays.asList(m.group(3), m.group(4), m.group(5))));
            } else {
                commands.add(new ArrayList<String>(Arrays.asList(m.group(7), m.group(8))));
            }
        }
    }
}
