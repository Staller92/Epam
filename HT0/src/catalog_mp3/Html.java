package catalog_mp3;
import catalog_mp3.artist.Album;
import catalog_mp3.artist.Artist;
import catalog_mp3.artist.Song;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;


public class Html {

    private static final String START_HTML = "<html><body>";
    private static final String END_HTMI = "</body></html>";
    private static final String ARTIST_TAG = "<h2>%s</h2>";
    private static final String ALBUM_TAG = "<ul><b>Album: %s</b></ul>";
    private static final String SONG_TAG = "<li>NAME: %s;   DURATION: %s sec;   PATH: %s</li>";
    private String bodyContents;
    FileWriter fileWriter = null;
    BufferedWriter bufferedWriter = null;

    public void createHtmlFile(String path, HashMap<String, Artist> trackList) {
        File file = new File(path);
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(START_HTML);
            generateBodyContent(trackList);
            bufferedWriter.append(END_HTMI);

            fileWriter.flush();

        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {

                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void generateBodyContent(HashMap<String, Artist> trackList) {
        for (HashMap.Entry<String, Artist> artist: trackList.entrySet()) {
            try {
                bufferedWriter.append(new Formatter().format(ARTIST_TAG, artist.getKey()).toString());
                for (HashMap.Entry<String, Album> album: artist.getValue().getAlbumMap().entrySet()) {
                    bufferedWriter.append(new Formatter().format(ALBUM_TAG, album.getKey()).toString());
                    for (Song song :album.getValue().getSongList()) {
                       bufferedWriter.append(new Formatter().format(SONG_TAG, song.getName(),song.getDuration(), song.getPath()).toString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void createSumDuplicateFile(String path, HashMap<String, List<String>> sumDuplicates) {
        File file = new File(path);
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(START_HTML);
            generateSumDuplicatesBody(sumDuplicates);
            bufferedWriter.append(END_HTMI);

            fileWriter.flush();

        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {

                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void generateSumDuplicatesBody(HashMap<String, List<String>> sumDuplicates) {
        try {
            for (HashMap.Entry<String, List<String>> sumDuplicate: sumDuplicates.entrySet()) {

                if (sumDuplicate.getValue().size()>1){
                    bufferedWriter.append("<ul>Дубликаты: </ul>");
                    for (String path:sumDuplicate.getValue()) {
                        bufferedWriter.append("<li>"+path+"</li>");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




