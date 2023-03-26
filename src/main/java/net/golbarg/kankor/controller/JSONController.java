package net.golbarg.kankor.controller;

import net.golbarg.kankor.model.News;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class JSONController {

    public static void main(String[] args) {

        try {
            System.out.println(getNews());;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static ArrayList<News> getNews() throws Exception {

        String data = getDataFromURL(SystemController.SERVER_ADDRESS + "api/kankor_news");

        JSONParser parse = new JSONParser();
        JSONObject data_obj = (JSONObject) parse.parse(data);

        JSONArray arr = (JSONArray) data_obj.get("news");

        ArrayList<News> result = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {

            JSONObject new_obj = (JSONObject) arr.get(i);
            int id = Integer.parseInt(new_obj.get("id").toString());
            String category = new_obj.get("category").toString();
            String title = new_obj.get("title").toString();
            String description = new_obj.get("description").toString();
            String urlLink = new_obj.get("url_link").toString();
            String content = new_obj.get("content").toString();
            LocalDateTime news_date = LocalDateTime.parse(new_obj.get("news_date").toString());

            result.add(new News(
                    id, category,
                    title, description,
                    urlLink, content, news_date
            ));
        }

        return result;
    }

    public static String getDataFromURL(String urlAddress) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(urlAddress);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        //Getting the response code
        int responseCode = conn.getResponseCode();

        String result = "";

        if(responseCode == 200) {

            Scanner scanner = new Scanner(url.openStream());

            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                result += scanner.nextLine();
            }

            scanner.close();
        }

        return result;
    }
}
