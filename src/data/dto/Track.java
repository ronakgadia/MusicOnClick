package data.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Track {
    public ArrayList<String> artists;
    public long duration_ms;
    public String href;
    public String id;
    public String name;
    public String preview_url;
    public String type;
    public String uri;
    public String trackAlbumImage;
    public String trackAlbumName;

    public void fromJson(JSONObject jsonObject) {
        Album album = new Album();
        album.fromJson((JSONObject) jsonObject.get("album"));
        this.trackAlbumImage = album.image;
        this.trackAlbumName = album.name;
        this.artists = getTrackArtist((JSONArray) jsonObject.get("artists"));
        this.duration_ms = (long) jsonObject.get("duration_ms");
        this.href = (String) jsonObject.get("href");
        this.id = (String) jsonObject.get("id");
        this.name = (String) jsonObject.get("name");
        this.preview_url = (String) jsonObject.get("preview_url");
        this.type = (String) jsonObject.get("type");
        this.uri = (String) jsonObject.get("uri");
    }

    private ArrayList<String> getTrackArtist(JSONArray jsonArray) {
        ArrayList<String> artists = new ArrayList<String>();
        for (Object o : jsonArray) {
            String name = (String) ((JSONObject) o).get("name");
            if (name != null)
                artists.add(name);
        }
        return artists;
    }
}
