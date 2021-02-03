import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.apache.commons.lang3.StringUtils;
import org.javacord.api.entity.server.Server;

public class mainClass {
    public static void main(String[] args) {
        DiscordApi API = new DiscordApiBuilder().setToken(System.getenv("TOKEN")).login().join();

        API.addMessageCreateListener(message ->{
            if(message.getServer().isPresent()) {
                Server server = message.getServer().get();
                if(message.getMessageContent().equals(".NAME")) message.getChannel().sendMessage("```" + server.getName() + "```");

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
