package catalog_mp3.track;

import java.io.File;

public class Track {

    private String artistName;
    private String albumName;
    private String songName;
    private long songDuration;
    private String path;

    private static final String UNKNOWN_ARTIST = "UNKNOWN ARTIST";
    private static final String UNKNOWN_ALBUM = "UNKNOWN ALBUM";

    public Track(String artistName, String albumName, String songName, long songDuration, String path) {
        if (artistName == null||artistName.isEmpty()) {
            this.artistName = UNKNOWN_ARTIST;
        } else {
            this.artistName = artistName;
        }
        if (albumName == null || albumName.isEmpty()) {
            this.albumName = UNKNOWN_ALBUM;
        } else {
            this.albumName = albumName;
        }
        if (songName.isEmpty()) {
            this.songName = new File(path).getName();
        } else {
            this.songName = songName;
        }
        this.songDuration = songDuration;
        this.path = path;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSongName() {
        return songName;
    }

    public long getSongDuration() {
        return songDuration;
    }

    public String getPath() {
        return path;
    }
}
