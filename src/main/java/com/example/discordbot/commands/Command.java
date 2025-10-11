package com.example.discordbot.commands;

import org.javacord.api.event.message.MessageCreateEvent;

public interface Command {
    void execute(MessageCreateEvent event);
}