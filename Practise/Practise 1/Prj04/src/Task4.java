import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by LIKHTAROVICH on 20.10.2017.
 */
public class Task4 {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Gradient.html"));
        writer.write("<html><body><table border=\"0\" width=\"300\">");

        for (int i = 255; i > 0; i--) {

            writer.write("<tr><td  bgcolor=\"rgb("+i+","+i+","+i+")\">" + "" + "</td></tr>");
            writer.flush();
        }

        writer.write("</table></body></html>");

    }

}


