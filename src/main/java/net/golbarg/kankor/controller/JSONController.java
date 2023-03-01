package net.golbarg.kankor.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class JSONController {

    public static void main(String[] args) {

        try {
            String data = getDataFromURL("http://localhost:8100/api/kankor_news");

            System.out.println(data);

            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(data);

            JSONArray arr = (JSONArray) data_obj.get("news");

            for (int i = 0; i < arr.size(); i++) {

                JSONObject new_obj = (JSONObject) arr.get(i);
                LocalDate date = LocalDate.parse(new_obj.get("news_date").toString());
                System.out.println(date.getMonth());

//                if(new_obj.get("Slug").equals("albania")) {
//                    System.out.println("Total Recovered: "+new.obj.get("TotalRecovered"));
//                    break;
//                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
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
