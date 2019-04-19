package data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.dto.Playlist;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
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
            String jsonArray = jsonObject.get("items").toString();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Playlist> playlists = objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(
                    List.class, Playlist.class));
            return playlists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}


