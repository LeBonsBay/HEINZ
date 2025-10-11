package com.example.discordbot;

import com.example.discordbot.listeners.MessageListener;
import io.github.cdimascio.dotenv.Dotenv;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

public class Bot {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("DISCORD_TOKEN");

        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .addIntents(Intent.MESSAGE_CONTENT)
                .login()
                .join();

        api.addMessageCreateListener(new MessageListener()); // Here is the message listener for commands btw
        System.out.println("Bot is running: " + api.createBotInvite());
    }
}