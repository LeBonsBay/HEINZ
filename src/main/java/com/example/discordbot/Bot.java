package com.example.discordbot;

import com.example.discordbot.commands.DateMessage;
import com.example.discordbot.commands.JoinCommand;
import com.example.discordbot.listeners.JoinListener;
import com.example.discordbot.listeners.MessageListener;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;

import java.sql.Time;

public class Bot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                //Intent.GUILD_MEMBERS enables the JoinListener
                .addIntents(Intent.MESSAGE_CONTENT,Intent.GUILD_MEMBERS)
                .login()
                .join();

        //Activity: plays ....
        api.updateActivity("letto.htl-steyr.ac.at");

        //sendScaryMessage() is called after creation of the Object
        DateMessage dateMessage = new DateMessage(api);
        JoinCommand joinCommand = new JoinCommand(api);

        api.addMessageCreateListener(new MessageListener(api));// Here is the message listener for commands btw
        api.addServerMemberJoinListener(new JoinListener()); //adding the ServerMemberJoinListener
        System.out.println("Bot is running: " + api.createBotInvite());
    }
}