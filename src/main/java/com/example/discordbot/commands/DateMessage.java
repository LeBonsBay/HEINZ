package com.example.discordbot.commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.event.message.MessageCreateEvent;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DateMessage {

    private String msg;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private final DiscordApi api;

    public DateMessage(DiscordApi api) {
        this.api = api;
        sendScaryMessage(); // Scheduler direkt starten
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void sendScaryMessage() {

        Runnable runnable = () -> { // = new Runnable() ..., anonymous call with lambda

            LocalDate localDate = LocalDate.now();
            LocalTime time = LocalTime.now();
            if (msg == null && localDate.getDayOfWeek() == DayOfWeek.FRIDAY && time.getHour() == 8 && time.getMinute() == 0) {

                setMsg("Hefte zua, freiwillige hervor");
                ServerTextChannel textChannel = api.getServerTextChannelById("1409623454344282166").orElse(null);

                if (getMsg() != null && textChannel != null) {
                    textChannel.sendMessage(getMsg());
                }
            }
        };
        executorService.scheduleAtFixedRate(runnable, 0, 60, TimeUnit.SECONDS);
    }
}
