package com.example.discordbot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import java.util.Random;

public class SYTCommand implements Command {
    private static final String[] sentences = {
            "Der Kirchhoff stimmt ned",
            "Hefte zu",
            "Freiwillige hervor",
            "Frankfurter",
            "Is des Unix based :face_with_raised_eyebrow:",
            "Saufst du heimlich oder unheimlich?",
    };

    @Override
    public void execute(MessageCreateEvent event) {
        Random rnd = new Random();
        int index = rnd.nextInt(sentences.length);
        event.getChannel().sendMessage(sentences[index]);
    }
}
