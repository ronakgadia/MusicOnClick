package data.dto;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Track {
    public List<String> artists;
    public long duration_ms;
    public String href;
    public int id;
    //    boolean is_playable;
    public String name;
//    public int popularity;
    public String preview_url;
    public String type;
    public String uri;
    public String trackAlbumImage;
    public String trackAlbumName;

    public void fromJson(JSONObject jsonObject) {
        this.trackAlbumImage = (String) ((ArrayList<JSONObject>) ((JSONObject) jsonObject.get("album")).get("images")).get(0).get("name");
        this.trackAlbumName = (String) ((JSONObject) jsonObject.get("album")).get("name");
        this.duration_ms = (long) jsonObject.get("duration_ms");
        this.href = (String) jsonObject.get("href");
        this.name = (String) jsonObject.get("name");
//        this.popularity = (int) jsonObject.get("popularity");
        this.preview_url = (String) jsonObject.get("preview_url");
        this.type = (String) jsonObject.get("type");
        this.uri = (String) jsonObject.get("uri");
    }
}
