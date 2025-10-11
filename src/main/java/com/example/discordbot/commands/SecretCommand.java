package com.example.discordbot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import java.io.File;

public class SecretCommand implements Command {
    @Override
    public void execute(MessageCreateEvent event) {
        File mp3 = new File("src/main/resources/audio/schweiger.mp3");
        event.getChannel().sendMessage(mp3);
    }
}