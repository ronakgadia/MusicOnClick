package data.dto;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Album {
    public String album_type;
    public ArrayList<String> artists;
    public String href;
    public String image;
    public String id;
    public String name;
    public String release_date;
    public String type;

    public void fromJson(JSONObject jsonObject) {
        this.artists = getAlbumArtist((JSONArray) jsonObject.get("artists"));
        this.image = getAlbumImage((JSONArray) jsonObject.get("images"));
        this.album_type = (String) jsonObject.get("album_type");
        this.href = (String) jsonObject.get("href");
        this.id = (String) jsonObject.get("id");
        this.name = (String) jsonObject.get("name");
        this.release_date = (String) jsonObject.get("release_date");
        this.type = (String) jsonObject.get("type");
    }

    private String getAlbumImage(JSONArray jsonArray) {
        if (!jsonArray.isEmpty()) {
            return (String) ((JSONObject) jsonArray.get(0)).get("url");
        }
        return null;
    }

    private ArrayList<String> getAlbumArtist(JSONArray jsonArray) {
        ArrayList<String> artists = new ArrayList<String>();
        for (Object o : jsonArray) {
            String name = (String) ((JSONObject) o).get("name");
            if (name != null)
                artists.add(name);
        }
        return artists;
    }

}
