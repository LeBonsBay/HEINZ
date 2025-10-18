package com.example.discordbot.api;

import com.example.discordbot.model.YoutubeData;
import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class YouTubeAPI {
    private String num;
    private static final Dotenv dotenv = Dotenv.load(); //loads .env file
    private final String YOUTUBE_Token = dotenv.get("YOUTUBE_TOKEN");

    public String makeRequest(String url, String channel_ID, String YOUTUBE_TOKEN) throws URISyntaxException, IOException {
        URI requestUrl = new URI(url + "?part=snippet" + "&channelId=" + URLEncoder.encode(channel_ID, StandardCharsets.UTF_8)+ "&maxResults=50"  + "&key=" + URLEncoder.encode(YOUTUBE_TOKEN, StandardCharsets.UTF_8));
        URLConnection connection = requestUrl.toURL().openConnection();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line = "";
        String json = "";

        while ((line = br.readLine()) != null) {
            json += line;
        }

        return json;
    }
    public YoutubeData ytData() throws URISyntaxException, IOException {

        String url = "https://www.googleapis.com/youtube/v3/search";
        String channel_ID = "UCB0JSO6d5ysH2Mmqz5I9rIw";
        final Dotenv dotenv = Dotenv.load(); //loads .env file
        String key = dotenv.get("YOUTUBE_TOKEN");

        Gson g = new Gson();
        String json = makeRequest(url, channel_ID, key);
        return g.fromJson(json, YoutubeData.class);
    }
}
