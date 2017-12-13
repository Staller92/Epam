package catalog_mp3.track;

import catalog_mp3.artist.Artist;
import com.mpatric.mp3agic.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class TrackManager {

    private static Mp3File mp3File;
    private static List<Track> trackList = new ArrayList<>();
    private static HashMap<String, Artist> allSongsMap = new LinkedHashMap<>();
    private static final String UNKNOWN_ARTIST = "UNKNOWN ARTIST";
    private static final String UNKNOWN_ALBUM = "UNKNOWN ALBUM";

    public static void addTrack(String path) {
        try {
            mp3File = new Mp3File(path);
            if (mp3File.hasId3v1Tag()) {
                try {
                    ID3v1 id3v1 = mp3File.getId3v1Tag();
                    trackList.add(new Track(id3v1.getArtist(), id3v1.getAlbum(), id3v1.getTitle(), mp3File.getLengthInSeconds(), path));
                } catch (NullPointerException ex) {
                    trackList.add(new Track(UNKNOWN_ARTIST, UNKNOWN_ALBUM, new File(path).getName(), mp3File.getLengthInSeconds(), path));
                }
            } else {
                try {
                    ID3v2 id3v2 = mp3File.getId3v2Tag();
                    trackList.add(new Track(id3v2.getArtist(), id3v2.getAlbum(), id3v2.getTitle(), mp3File.getLengthInSeconds(), path));
                } catch (NullPointerException ex) {
                    trackList.add(new Track(UNKNOWN_ARTIST, UNKNOWN_ALBUM, new File(path).getName(), mp3File.getLengthInSeconds(), path));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Artist> getAllSongsMap() {
        return sortByArtist();
    }

    private static HashMap<String, Artist> sortByArtist() {
        for (Track track : trackList) {
            if (!allSongsMap.containsKey(track.getArtistName())) {
                allSongsMap.put(track.getArtistName(), new Artist(track));
            } else {
                allSongsMap.get(track.getArtistName()).addAlbum(track);
            }
        }
        return allSongsMap;
    }
}
