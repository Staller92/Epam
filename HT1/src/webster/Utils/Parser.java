package webster.Utils;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import java.io.File;

import java.io.IOException;


public class Parser {
    private static final String pathToLog4jConfigurations = System.getProperty("user.dir") + "/src/log4j2.xml";

       public String getPathToLog() {

        Node fileName = null;
        try {
            File file = new File(pathToLog4jConfigurations);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList list = doc.getElementsByTagName("File");
            NamedNodeMap attributes = list.item(0).getAttributes();
            fileName = attributes.getNamedItem("fileName");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName.getNodeValue();
    }


}