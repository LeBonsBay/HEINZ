package com.example.discordbot;

import com.example.discordbot.commands.DateMessage;
import com.example.discordbot.commands.JoinCommand;
import com.example.discordbot.listeners.MessageListener;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.user.UserStatus;

public class Bot {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .addIntents(Intent.MESSAGE_CONTENT)
                .login()
                .join();


        DateMessage dateMessage = new DateMessage(api); //sendThing() is called after creation of the Object
        JoinCommand joinCommand = new JoinCommand(api);


        api.updateActivity("letto.htl-steyr.ac.at");
        //need to give api because of JoinCommand...
        api.addMessageCreateListener(new MessageListener(api)); // Here is the message listener for commands btw
        System.out.println("Bot is running: " + api.createBotInvite());
    }
}