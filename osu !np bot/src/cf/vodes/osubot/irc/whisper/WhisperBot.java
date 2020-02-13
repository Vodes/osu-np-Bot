package cf.vodes.osubot.irc.whisper;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.DisconnectEvent;

import cf.vodes.osubot.threads.WhisperReconnectThread;

public class WhisperBot extends ListenerAdapter {
	
	public static PircBotX bot;
	public static Configuration config;
	
	@Override
	public void onDisconnect(DisconnectEvent event) throws Exception {
		new WhisperReconnectThread().start();
		super.onDisconnect(event);
	}
	
	public static void whisper(String user, String message) {
		bot.sendRaw().rawLine("CAP REQ :twitch.tv/commands");
		bot.sendRaw().rawLine("PRIVMSG :/w " + user.toLowerCase() + " " + message);
	}

}
