package com.example.discordbot.commands;

import com.example.discordbot.Exceptions.VoiceChannelNotFoundException;
import org.javacord.api.DiscordApi;
import org.javacord.api.audio.AudioConnection;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;

import java.util.Optional;


public class JoinCommand implements Command {

    private final DiscordApi api;
    private final String message = "Hakrafax i find den VC ned oder du bist in goar keinen VC";

    public JoinCommand(DiscordApi api) {
        this.api = api;
    }

    public void joinCall(MessageCreateEvent event) throws VoiceChannelNotFoundException {

        //find the user that sent $join
        User user = event.getMessageAuthor().asUser().orElse(null);

        //get the Server from where the user wrote
        Server server = event.getServer().orElse(null);

        if (user != null) {

            //orElseThrow excepts a method that creates the exception so that's why the anonymous call is mandatory...
            //ServerVoiceChannel voiceChannel = user.getConnectedVoiceChannel(server).orElseThrow(() -> new VoiceChannelNotFoundException(message));

            ServerVoiceChannel voiceChannel = user.getConnectedVoiceChannel(server).orElse(null);

            if (event.getMessageContent().equals("$disconnect")) {

                //looks if there's a current audio connection on the discord, if yes it gets closed with the connection variable
                if (server.getAudioConnection().isPresent()) {
                    Optional<AudioConnection> connection = server.getAudioConnection();

                    connection.get().close();
                }
            }

            if (voiceChannel != null && event.getMessageContent().equals("$join")) {
                voiceChannel.connect();

            } else if (voiceChannel == null && event.getMessageContent().equals("$join")) {
                throw new VoiceChannelNotFoundException(message);
            }
        }
    }

    @Override
    public void execute(MessageCreateEvent event) {
        try {
            switch (event.getMessageContent()) {
                case "$join":
                    event.getChannel().sendMessage("Joining...");
                    joinCall(event);

                    break;
                case "$disconnect":
                    event.getChannel().sendMessage("Disconnect...");
                    joinCall(event);
                    break;
            }
        } catch (VoiceChannelNotFoundException e) {
            //not beautiful but let's let it be like this....
            throw new RuntimeException(e);
        }
    }
}
