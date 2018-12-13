import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by LIKHTAROVICH on 25.10.2017.
 */



public class CreateXML {

    public static void createXML(File[] listFiles,String dir,String result) {


        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = builder.newDocument();
        Element data = document.createElement("data");
        document.appendChild(data);
        for (int i = 0; i <listFiles.length ; i++) {
        Element file = document.createElement("file");

        Element name = document.createElement("name");
        Element path = document.createElement("path");
        Element size = document.createElement("size");
        Element datetime = document.createElement("datetime");



        Text fileName = document.createTextNode(listFiles[i].getName());
        Text filePath = document.createTextNode(dir + File.separator);
        Text fileSize = document.createTextNode(String.valueOf(listFiles[i].length()));
        Text filedatetime = document.createTextNode(String.valueOf(new Date(listFiles[i].lastModified())));

        name.appendChild(fileName);
        path.appendChild(filePath);
        size.appendChild(fileSize);
        datetime.appendChild(filedatetime);


        file.appendChild(name);
        file.appendChild(path);
        file.appendChild(size);
        file.appendChild(datetime);

        data.appendChild(file);
        }
        OutputFormat format = new OutputFormat(document);
        format.setIndenting(true);


        XMLSerializer serializer = null;
        try {
            serializer = new XMLSerializer(new FileOutputStream(result), format);
            serializer.serialize(document);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}






