package data;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.SecretsManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class APIDataManager {
    static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String requestToken() {
        String url = "https://accounts.spotify.com/api/token";
        String auth = Base64.getEncoder().encodeToString((SecretsManager.clientId + ":" + SecretsManager.clientSecret).getBytes());
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.addHeader("Authorization", "Basic " + auth);
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            System.out.println(response.getStatusLine());
            BufferedReader breader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder responseString = new StringBuilder();
            String line = "";
            while ((line = breader.readLine()) != null) {
                responseString.append(line);
            }
            breader.close();
            response.close();
            String responseStr = responseString.toString();
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(responseStr);
                String token = jsonObject.get("access_token").toString();
                return token;
            } catch (ParseException e) {
                e.printStackTrace();
                return "";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


}


