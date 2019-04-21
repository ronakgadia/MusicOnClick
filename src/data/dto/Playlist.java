package data.dto;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Playlist {
    public String href;
    public String id;
    public String images;
    public String name;
    public String tracksUrl;
    public String type;
    public String uri;

    public void fromJson(JSONObject jsonObject) {
        this.href = (String) jsonObject.get("href");
        this.id = (String) jsonObject.get("id");
        this.name = (String) jsonObject.get("name");
        this.tracksUrl = (String) ((JSONObject) jsonObject.get("tracks")).get("href");
        this.type = (String) jsonObject.get("type");
        this.uri = (String) jsonObject.get("uri");
    }
}
