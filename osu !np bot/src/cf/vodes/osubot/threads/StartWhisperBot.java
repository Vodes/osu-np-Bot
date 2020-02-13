package cf.vodes.osubot.threads;

import java.io.File;
import java.io.IOException;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.cap.EnableCapHandler;
import org.pircbotx.exception.IrcException;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;
import cf.vodes.osubot.irc.Listener;
import cf.vodes.osubot.irc.whisper.WhisperBot;
import cf.vodes.osubot.options.OptionManager;

public class StartWhisperBot extends Thread{
	
	
	
	@Override
	public void run() {
		WhisperBot.config = new Configuration.Builder().
				addListener(new WhisperBot())
				.addServer("199.9.253.119")
				.setServerPassword(Main.oauthKey)
				.setName(((String)OptionManager.getOptionValue("Username")).toLowerCase())
				.setLogin("osu-np-bot")
				.buildConfiguration();
		WhisperBot.bot = new PircBotX(WhisperBot.config);
		try {
			WhisperBot.bot.startBot();
		} catch (IOException | IrcException e) {
			e.printStackTrace();
		}
	}

}
