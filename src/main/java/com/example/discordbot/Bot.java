package com.example.discordbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import java.util.Random;

public class Bot {
    private static final String BOT_Token = "MTQwOTYxODY0Nzk0NDUyNzk2Mw.GI9TRV.NqEjUb-sMai5CYO6_sYS1dk17LyY-aQRDg5Ev0";
   private static final String[] sentences = {"Der Kirchhoff stimmt ned", "Hefte zu", "Freiwillige hervor", "Frankfurter"};

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
        });
    }
}
