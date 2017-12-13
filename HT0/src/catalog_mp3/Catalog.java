package catalog_mp3;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by LIKHTAROVICH on 29.10.2017.
 */
public class Catalog {

    private String Extension;

    public Catalog(String Extension) {
        this.Extension = Extension;
    }

    private HashSet<String> set = new HashSet<>();

    public HashSet<String> searcher(File... files) throws IOException {


        for (int i = 0; i < files.length; i++) {

            if (!files[i].exists()) {
                System.out.println(files[i].getName() + " is not exist");
                break;
            }
            File[] listOfFiles = files[i].listFiles();

            for (File file : listOfFiles) {
                if (file.isFile()) {
                    if (isRequiredFile(file)) {
                        set.add(file.getCanonicalPath());
                    }

                } else {
                    if (file.isDirectory()) {
                        searcher(file);

                    }
                }
            }
        }
        return set;
    }


    public boolean isRequiredFile(File file) {
        if (file.getName().toLowerCase().endsWith(Extension)) {
            return true;
        } else {
            return false;
        }
    }

}
