package com.example.discordbot;

import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Bot {
    private static final Dotenv dotenv = Dotenv.load(); //loads .env file
    private static final String BOT_Token = dotenv.get("DISCORD_TOKEN");
    private static final String[] sentences = {"Der Kirchhoff stimmt ned", "Hefte zu", "Freiwillige hervor", "Frankfurter", "Is des Unix based :face_with_raised_eyebrow:"};
    private static final String[] images = {"1.png", "2.png"};

    //store sentences in a txt file later
    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
                .setToken(BOT_Token)
                .addIntents(Intent.MESSAGE_CONTENT)
                .login().join();

        api.addMessageCreateListener(event -> {

            if (event.getMessageContent().equalsIgnoreCase("!SYT")) { //upper/lower case is ignored
                Random rndm = new Random();
                int index = rndm.nextInt(sentences.length); //nextInt generates random integer
                event.getChannel().sendMessage(sentences[index]); //getChannel gives the channel where msg was sent
            }
            if (event.getMessageContent().equalsIgnoreCase("!SECRET")) {
                File mp3 = new File("src/main/resources/audio/schweiger.mp3");
                event.getChannel().sendMessage(mp3);
                //implement random img send by creating a Random object, storing the names of the files in an array
                //Note: don't forget that you can add with '+' something to strings my G
            }
            if (event.getMessageContent().equalsIgnoreCase("!IMG")) {
                Random random = new Random();
                int index = random.nextInt(images.length);
                File img = new File("src/main/resources/img/" + images[index]);
                event.getChannel().sendMessage(img);
            }
        });
    }
}
