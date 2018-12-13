import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by LIKHTAROVICH on 25.10.2017.
 */
public class Main {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("You didn't input file address");
            System.exit(-1);
        } else {
            DOMParser parser = new DOMParser();
            try {
                parser.parse(args[0]);
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

           HashSet<String> fio = new HashSet<>();

            Document document = parser.getDocument();
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i) instanceof Element) {
                    fio.add((((Element) nodeList.item(i)).getAttribute("fio")));
                }
            }
            ArrayList<String> list = new ArrayList<>(fio);

           /* for (String x:list ) {
                System.out.println(x);
            }*/


            String maximum="";
            String minimum="";
            int max=list.get(0).length();
            int min=list.get(0).length();
            for (int i = 0; i <list.size() ; i++) {
                if(list.get(i).length()>max){maximum=list.get(i);}
                if(list.get(i).length()<min){minimum=list.get(i);}

            }

            System.out.println(maximum);
            System.out.println(minimum);
//не успел(
        }

    }
}
