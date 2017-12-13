package webster;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Commands {
    private static final Logger LOG = LogManager.getLogger(Commands.class.getName());
    private static final String PATH = "/src/webster/resources/file.html";
    private static final String REGEX_TITLE = "<title>(.*?)</title>";
    private static final String REGEX_HREF = "(?<=(?i)href\\s{0,1}=\\s{0,1}[\"\']).*?(?=[\"\'])";
    private static final String REGEX_LINK = "(?i)<a([^>]+)>(.+?)</a>";
    private static boolean isOpened;
    private static int passedTests;
    private static int totalTests;
    private static double totalTime;


    public static boolean open(String url, double timeOutInSec) {
        Timer timer = new Timer();
        timer.start();
        totalTests++;
        HtmlHelper htmlHelper = new HtmlHelper();
        Formatter formatter = new Formatter();
        boolean isCreated = htmlHelper.createHTMl(url);
        timer.stop();
        double time = timer.getTime();
        totalTime += time;
        if (isCreated && timeOutInSec >= time) {
            LOG.info(formatter.format("+ [open \"%s\" \"%.3f\"] %.3f sec", url, timeOutInSec, time));
            isOpened = true;

        } else {
            LOG.info(formatter.format("! [open \"%s\" \"%.3f\"] %.3f sec", url, timeOutInSec, time));
            isOpened = false;

        }
        if (isOpened) {
            passedTests++;
        }
        return isOpened;

    }

    public static boolean checkPageTitle(String title) {
        Timer timer = new Timer();
        timer.start();
        totalTests++;
        boolean flagCheck = false;
        String actualTitle;
        BufferedReader reader;
        Pattern p;
        Matcher m;
        Formatter formatter = new Formatter();
        if (isOpened) {
            try {
                reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + PATH));
                p = Pattern.compile(REGEX_TITLE);
                while (reader.ready()) {
                    m = p.matcher(reader.readLine());
                    while (m.find()) {
                        actualTitle = m.group(1).trim();
                        if (actualTitle.equals(title)) {

                            flagCheck = true;
                            break;
                        }
                    }
                    break;
                }
                reader.close();
            } catch (IOException e) {
                LOG.debug("EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
                flagCheck = false;
            }
        }
        timer.stop();
        double time = timer.getTime();
        totalTime += time;
        if (flagCheck) {
            LOG.info(formatter.format("+ [checkPageTitle \"%s\"] %.3f sec", title, time));
        } else {
            LOG.info(formatter.format("! [checkPageTitle \"%s\"] %.3f sec", title, time));

        }
        if (flagCheck) {
            passedTests++;
        }
        return flagCheck;
    }

    public static boolean checkLinkPresentByHref(String href) {
        Timer timer = new Timer();
        timer.start();
        totalTests++;
        boolean flagCheck = false;
        String actualHref;
        BufferedReader reader;
        Pattern p;
        Matcher m;
        Formatter formatter = new Formatter();
        if (isOpened) {
            try {
                reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + PATH));
                p = Pattern.compile(REGEX_HREF);
                while (reader.ready()) {
                    m = p.matcher(reader.readLine());
                    while (m.find()) {
                        actualHref = m.group(0).trim();
                        if (actualHref.equals(href)) {

                            flagCheck = true;
                            break;
                        }
                    }
                    break;
                }
                reader.close();
            } catch (IOException e) {
                LOG.debug("EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
                flagCheck = false;
            }
        }
        timer.stop();
        double time = timer.getTime();
        totalTime += time;
        if (flagCheck) {
            LOG.info(formatter.format("+ [checkLinkPresentByHref \"%s\"] %.3f sec", href, time));
        } else {
            LOG.info(formatter.format("! [checkLinkPresentByHref \"%s\"] %.3f sec", href, time));

        }
        if (flagCheck) {
            passedTests++;
        }
        return flagCheck;
    }

    public static boolean checkLinkPresentByName(String name) {
        Timer timer = new Timer();
        timer.start();
        totalTests++;
        boolean flagCheck = false;
        String actualName;
        BufferedReader reader;
        Pattern p;
        Matcher m;
        Formatter formatter = new Formatter();
        if (isOpened) {
            try {
                reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + PATH));
                p = Pattern.compile(REGEX_LINK);
                while (reader.ready()) {
                    m = p.matcher(reader.readLine());
                    while (m.find()) {
                        actualName = m.group(2).trim();
                        if (actualName.equals(name)) {
                            flagCheck = true;
                            break;
                        }
                    }
                    break;
                }
                reader.close();
            } catch (IOException e) {
                LOG.debug("EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
                flagCheck = false;
            }
        }
        timer.stop();
        double time = timer.getTime();
        totalTime += time;
        if (flagCheck) {
            LOG.info(formatter.format("+ [checkLinkPresentByName \"%s\"] %.3f sec", name, time));
        } else {
            LOG.info(formatter.format("! [checkLinkPresentByName \"%s\"] %.3f sec", name, time));

        }
        if (flagCheck) {
            passedTests++;
        }
        return flagCheck;
    }

    public static boolean checkPageContains(String text) {

        Timer timer = new Timer();
        timer.start();
        StringBuilder sb;
        totalTests++;
        boolean flagCheck = false;
        BufferedReader reader;
        Formatter formatter = new Formatter();
        if (isOpened) {
            try {
                reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + PATH));
                sb = new StringBuilder();
                while (reader.ready()) {
                    sb.append(reader.readLine());
                }
                if (sb.toString().contains(text)) {
                    flagCheck = true;
                }
                reader.close();
            } catch (IOException e) {
                LOG.debug("EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
                flagCheck = false;
            }
        }
        timer.stop();
        double time = timer.getTime();
        totalTime += time;
        if (flagCheck) {
            LOG.info(formatter.format("+ [checkPageContains \"%s\"] %.3f sec", text, time));
        } else {
            LOG.info(formatter.format("! [checkPageContains \"%s\"] %.3f sec", text, time));

        }
        if (flagCheck) {
            passedTests++;
        }
        return flagCheck;
    }

    public static void showStatistics() {

        double averageTime = totalTime / totalTests;

        if (totalTests == 0) {
            averageTime = 0;
        }
        Formatter formatter = new Formatter();
        LOG.info(formatter.format("STATISTICS:\nTotal tests: %d\n" +
                "Passed/Failed: %d/%d\n" +
                "Total time: %.3f sec\n" +
                "Average time: %.3f sec\n" +
                "Log was saved to %s", totalTests, passedTests, totalTests - passedTests, totalTime, averageTime, InitializerLog4j.getPathLogFile()));

    }
}
