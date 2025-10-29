package com.example.discordbot.listeners;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.event.server.ServerJoinEvent;
import org.javacord.api.event.server.member.ServerMemberJoinEvent;
import org.javacord.api.listener.server.ServerJoinListener;
import org.javacord.api.listener.server.member.ServerMemberJoinListener;

public class JoinListener implements ServerMemberJoinListener {

    @Override
    public void onServerMemberJoin(ServerMemberJoinEvent serverMemberJoinEvent) {

        // Get the user ID of the member who just joined
        // We use the ID because Discord highlights mentions with <@ID>
        String user = String.valueOf(serverMemberJoinEvent.getUser().getId());

        // Try to find the channel we want to send the welcome message in
        serverMemberJoinEvent.getServer().getChannelById("1433066598993891473").ifPresent(channel -> {

            // 'instanceof' checks if the channel is a TextChannel
            // (I didn’t know this before! It’s just a safe way to cast objects)
            // Thank you, ChatGPT, I guess? haha
            if (channel instanceof TextChannel textChannel) {

                // <@user> will mention the user in Discord
                textChannel.sendMessage("Moizeit" + "<@" + user + ">" + ":drooling_face: " + "\n" + "Willkommen im Letto Keller" + ":zap:" + ":zap:");
                System.out.println("message sent");
            }
        });
    }
}
