package com.example.discordbot.listeners;

import com.example.discordbot.Bot;
import com.example.discordbot.commands.*;
import org.javacord.api.DiscordApi;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.util.HashMap;
import java.util.Map;

public class MessageListener implements MessageCreateListener {
    private final Map<String, Command> commands = new HashMap<>();

    public MessageListener(DiscordApi api) {
        commands.put("$syt", new SYTCommand());
        commands.put("$secret", new SecretCommand());
        commands.put("$img", new ImgCommand());
        commands.put("$acdc", new AcdcCommand());
        commands.put("$join", new JoinCommand(api));
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        String content = event.getMessageContent().toLowerCase();

        Command cmd = commands.get(content);
        if (cmd != null) {
            cmd.execute(event);
        }
    }
}
