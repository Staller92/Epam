package webster;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;


public class HtmlHelper {
    private static final Logger log = LogManager.getLogger(HtmlHelper.class.getName());
    private static final String PATH = "/src/webster/resources/file.html";


    public boolean createHTMl(String url) {

        try {
            URL urlAddress  = new URL(url);
            URLConnection urlConnection = urlAddress.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + PATH));
            while (reader.ready()) {
                writer.write(reader.readLine());
            }
            reader.close();
            writer.close();

        } catch (FileNotFoundException e) {
            log.debug("MSG: Error 404 Not Found: " + "EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
            return false;

        } catch (MalformedURLException e) {
            log.debug("MSG: Protocol is not specified. Use URL protocol 'https://'. " + "EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
            return false;

        } catch (UnknownHostException e) {
            log.debug("MSG: URL is not founded: " + "EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
            return false;
        } catch (IOException e) {
            log.debug("EXP: " + e + " STACK: " + new StackTrace().getStackTraceFromExceptions(e));
            return false;
        }
        return true;
    }
}
