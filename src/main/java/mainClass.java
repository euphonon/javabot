import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.apache.commons.lang3.StringUtils;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class mainClass {
    public static void main(String[] args) {
        DiscordApi API = new DiscordApiBuilder().setToken(System.getenv("TOKEN")).login().join();

        API.addMessageCreateListener(message ->{
                if(message.getMessageAuthor().isUser()){
                    if(message.getServer().isPresent()) {
                        Server server = message.getServer().get();
                        if (message.getMessageContent().equals(".NAME;"))
                            message.getChannel().sendMessage("```" + server.getName() + "```");
                        else if (message.getMessageContent().equals(".MEMBERS.LENGTH;"))
                            message.getChannel().sendMessage("```" + server.getMemberCount() + "```");
                        else if (message.getMessageContent().equals(".CHANNELS.LENGTH;"))
                            message.getChannel().sendMessage("```" + server.getChannels().size() + "```");
                        else if (message.getMessageContent().startsWith(".MEMBERS[")) {
                            User[] members = server.getMembers().toArray(new User[0]);
                            User user = members[Integer.valueOf(StringUtils.substringBetween(message.getMessageContent(), "[", "]"))];
                            switch (message.getMessageContent().split("]")[1]) {
                                case ".DISCRIMINATED_NAME":
                                    message.getChannel().sendMessage("```" + user.getDiscriminatedName() + "```");
                                case ".NICKNAME":
                                    message.getChannel().sendMessage("```" + user.getNickname(server).get() + "```");
                                case ".GETID":
                                    message.getChannel().sendMessage("```" + user.getIdAsString() + "```");
                                case ".MENTION":
                                    message.getChannel().sendMessage(user.getMentionTag());
                            }
                        } else if (message.getMessageContent().startsWith(".OWNER")) {
                            switch (message.getMessageContent().split(".")[2]) {
                                case ".DISCRIMINATED_NAME":
                                    message.getChannel().sendMessage("```" + server.getOwner().get().getDiscriminatedName() + "```");
                                case ".NICKNAME":
                                    message.getChannel().sendMessage("```" + server.getOwner().get().getNickname(server) + "```");
                                case ".GETID":
                                    message.getChannel().sendMessage("```" + server.getOwner().get().getIdAsString() + "```");
                                case ".MENTION":
                                    message.getChannel().sendMessage(server.getOwner().get().getMentionTag());
                            }
                        } else if (message.getMessageContent().startsWith(".CHANNELS[")) {
                            ServerTextChannel[] channels = server.getTextChannels().toArray(new ServerTextChannel[0]);
                            ServerTextChannel channel = channels[Integer.valueOf(StringUtils.substringBetween(message.getMessageContent(), "[", "]"))];
                            switch (message.getMessageContent().split("]")[1]) {
                                case ".NAME;":
                                    message.getChannel().sendMessage("```" + channel.getName() + "```");
                                case ".TOPIC;":
                                    message.getChannel().sendMessage("```" + channel.getTopic() + "```");
                                case ".IS_NSFW;":
                                    message.getChannel().sendMessage("```" + channel.isNsfw() + "```");
                                case ".IS_HIDDEN;":
                                    message.getChannel().sendMessage("```" + channel.canSee(message.getMessageAuthor().asUser().get()) + "```");
                            }
                        }
                    }

                // Commands:
                // .NAME
                // .MEMBERS...
                    // .LENGTH
                    // [i]
                        // .DISCRIMINATED_NAME
                        // .NICKNAME
                        // .GETID
                        // .MENTION
                // .OWNER
                    // .DISCRIMINATED_NAME
                    // .NICKNAME
                    // .GETID
                    // .MENTION
                // .CHANNELS...
                    // .LENGTH
                    // [i]
                        // .NAME
                        // .TOPIC
                        // .IS_NSFW
                        // .IS_HIDDEN
            }
        });
    }
}
