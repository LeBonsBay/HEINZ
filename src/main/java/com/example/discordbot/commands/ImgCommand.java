package com.example.discordbot.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import java.io.File;
import java.util.Random;

public class ImgCommand implements Command {
    private static final String[] images = {"1.png", "2.png"};

    @Override
    public void execute(MessageCreateEvent event){
        Random rnd = new Random();
        int index = rnd.nextInt(images.length);
        File file = new File("src/main/resources/img/" + images[index]);
        event.getChannel().sendMessage(file);
    }
}
