package data;

import data.dto.Playlist;
import data.dto.Track;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.SecretsManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class APIDataManager {
    static CloseableHttpClient httpclient = HttpClients.createDefault();

    private String getBufferedReaderResponseString(CloseableHttpResponse response) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseString = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                responseString.append(line);
            }
            bufferedReader.close();
            return responseString.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String requestToken() {
        String url = "https://accounts.spotify.com/api/token";
        String auth = Base64.getEncoder().encodeToString((SecretsManager.clientId + ":" + SecretsManager.clientSecret).getBytes());
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            httpPost.addHeader("Authorization", "Basic " + auth);
            CloseableHttpResponse response = httpclient.execute(httpPost);
            System.out.println(response.getStatusLine());
            String responseStr = getBufferedReaderResponseString(response);
            response.close();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseStr);
            String token = jsonObject.get("access_token").toString();
            return token;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public List<Playlist> getFeaturedPlaylist() {
        String url = "https://api.spotify.com/v1/browse/featured-playlists";
        HttpGet httpGet = new HttpGet(url);
        PreferenceManager preferenceManager = PreferenceManager.getInstance();
        httpGet.addHeader("Authorization", "Bearer " + preferenceManager.getToken());
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            String responseStr = getBufferedReaderResponseString(response);
            response.close();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseStr);
            jsonObject = (JSONObject) jsonObject.get("playlists");
            JSONArray jsonArray = (JSONArray) jsonObject.get("items");
            ArrayList<JSONObject> jsonObjectList = new ArrayList<>();
            for (Object o : jsonArray) {
                jsonObjectList.add((JSONObject) o);
            }
            return convertJSONPlaylistToObject(jsonObjectList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Playlist> convertJSONPlaylistToObject(ArrayList<JSONObject> jsonObjectList) {
        ArrayList<Playlist> playlists = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjectList) {
            Playlist playlist = new Playlist();
            playlist.href = (String) jsonObject.get("href");
            playlist.id = (String) jsonObject.get("id");
            playlist.name = (String) jsonObject.get("name");
            playlist.tracksUrl = (String) ((JSONObject) jsonObject.get("tracks")).get("href");
            playlist.type = (String) jsonObject.get("type");
            playlist.uri = (String) jsonObject.get("uri");
            playlists.add(playlist);
        }
        return playlists;
    }

    private ArrayList<Track> convertJSONTracksToObject(ArrayList<JSONObject> jsonObjectList) {
        ArrayList<Track> tracks = new ArrayList<>();
        for (JSONObject jsonObject : jsonObjectList) {
            Track track = new Track();
            track.duration_ms = (int) jsonObject.get("duration_ms");
            track.href = (String) jsonObject.get("href");
            track.name = (String) jsonObject.get("name");
            track.popularity = (int) jsonObject.get("popularity");
            track.preview_url = (String) jsonObject.get("preview_url");
            track.type = (String) jsonObject.get("type");
            track.uri = (String) jsonObject.get("uri");
            tracks.add(track);
        }
        return tracks;
    }
}

