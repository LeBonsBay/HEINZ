package com.example.discordbot.commands;

import com.example.discordbot.api.YouTubeAPI;
import com.example.discordbot.model.Item;
import com.example.discordbot.model.YoutubeData;
import org.javacord.api.event.message.MessageCreateEvent;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class AcdcCommand implements Command {
    String link = "";
    private  final List<String> videos = new LinkedList<>();

    public String sendMessage(){
        Random randomID = new Random();

        try {

            YouTubeAPI ytAPI = new YouTubeAPI();

            YoutubeData youtubeData = ytAPI.ytData();

            for (Item item : youtubeData.getItems()) {
                String videoId = item.getId().getVideoId();


                if(videoId != null && !videoId.isEmpty()){
                    link = "https://www.youtube.com/watch?v=" + videoId; //builds the video url
                    videos.add(link); //adds the url to the list
                }
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return videos.get(randomID.nextInt(videos.size())); //returns a random url from the list,  the videos.size is basically  understood like array.length...

    }
    public void execute(MessageCreateEvent event){
        event.getChannel().sendMessage(link = sendMessage());
    }
}