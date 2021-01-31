import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class mainClass {
    public static void main(String[] args) {
        DiscordApi API = new DiscordApiBuilder().setToken(System.getenv("TOKEN")).login().join();
    }
}
