package controller;

import org.json.JSONArray;
import org.json.JSONObject;
import model.Kotel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Http {


    public static JSONObject connect(String email, String pass) throws Exception {

        URL url = new URL("http://smieszne-koty.herokuapp.com/oauth/token" +
                "?grant_type=password&email=" + email + "&password=" + pass);
        HttpURLConnection connection;
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setReadTimeout(30000);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = input.readLine()) != null)
            response.append(inputLine);
        input.close();

        JSONObject autoryzacja = new JSONObject(response.toString());


        return autoryzacja;
    }


    public static List<Kotel> getKotely(JSONObject jsonObject, int pageNumber) throws Exception {
        String token = jsonObject.getString("access_token");
        URL url = new URL("http://smieszne-koty.herokuapp.com/api/kittens" +
                "?access_token=" + token + "&page=" + pageNumber);
        HttpURLConnection connection;

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(30000);

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = input.readLine()) != null)
            response.append(inputLine);
        input.close();

        JSONArray kotki = new JSONArray(response.toString());
        List<Kotel> kotely = new ArrayList<>();


        for(int i = 0; i < kotki.length(); i++) {

            JSONObject kotek = kotki.getJSONObject(i);
            Kotel kotel = new Kotel();
            kotel.setName(kotek.getString("name"));
            kotel.setURL(kotek.getString("url"));
            kotel.setVotes(kotek.getInt("vote_count"));
            kotely.add(kotel);

        }

        return kotely;
    }
}
