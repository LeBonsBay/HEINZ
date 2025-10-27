package com.example.discordbot;

import com.example.discordbot.commands.DateMessage;
import com.example.discordbot.commands.JoinCommand;
import com.example.discordbot.listeners.MessageListener;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

import java.sql.Time;

public class Bot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .addIntents(Intent.MESSAGE_CONTENT)
                .login()
                .join();

        //Activity: plays ....
        api.updateActivity("letto.htl-steyr.ac.at");

        //sendScaryMessage() is called after creation of the Object
        DateMessage dateMessage = new DateMessage(api);
        JoinCommand joinCommand = new JoinCommand(api);

        api.addMessageCreateListener(new MessageListener(api)); // Here is the message listener for commands btw
        System.out.println("Bot is running: " + api.createBotInvite());
    }
}