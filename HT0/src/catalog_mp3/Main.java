package catalog_mp3;
import catalog_mp3.track.TrackManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by LIKHTAROVICH on 29.10.2017.
 */
public class Main {
    public static void main(String[] args) {
        File[] files = new File[args.length];
        HashSet<String> set = new HashSet<>();
        if (args.length == 0) {
            System.out.println("Should be input at least one path to directory");
            System.exit(-1);
        } else {

            for (int i = 0; i < args.length; i++) {
                files[i] = new File(args[i]);
            }
        }

        Catalog catalogMP3 = new Catalog(".mp3");
        try {
            set = catalogMP3.searcher(files);

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String x : set) {

            TrackManager.addTrack(x);

        }
        new Html().createHtmlFile("C:\\file.html", TrackManager.getAllSongsMap());
        HashMap<String, List<String>> sumDuplicatesList = new HashMap<>();
        for (String x : set) {
            String sum = CheckSum.getCheckSum(x);
            if (!sumDuplicatesList.containsKey(sum)) {
                sumDuplicatesList.put(sum, new ArrayList<>());
                sumDuplicatesList.get(sum).add(x);
            } else {
                sumDuplicatesList.get(sum).add(x);
            }
        }

        new Html().createSumDuplicateFile("C:\\file1.html", sumDuplicatesList);
    }
}
