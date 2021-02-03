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
                // Commands:
                // .getName();
                // .getMembers()...
                    // .length;
                    // [i]
                    // .getDiscriminatedName();
                    // .getNickname();
                    // .getId();
                    // .mention();
                // .getOwner();
                // .getChannels()...
                    // .length;
                    // [i]
                    // .getName();
                    // .getTopic();
                    // .isNSFW();
                    // .isHiddenToYou();
                // .banUser(String userID, Integer messagesToDelete, String reason);
                // .kickUser(String userID, String reason);
                // .disconnect();
                // .massDM(String content);
            }
        });
    }
}
