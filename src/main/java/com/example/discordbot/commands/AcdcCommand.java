package com.example.discordbot.commands;

import org.javacord.api.event.message.MessageCreateEvent;

import java.io.File;
import java.util.Random;


public class AcdcCommand implements Command {
    Random rando = new Random();
    String placeholder = "";
    /*
    @ToDo
    *
    * Go to the docs and search how you fetch the data and then look how a asynchronous function works and write it down
    */

    public void execute(MessageCreateEvent event){
        event.getChannel().sendMessage(placeholder);
    }
}
