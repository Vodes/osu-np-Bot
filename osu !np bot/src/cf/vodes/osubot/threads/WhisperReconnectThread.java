package cf.vodes.osubot.threads;

import org.pircbotx.PircBotX;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;
import cf.vodes.osubot.irc.whisper.WhisperBot;

public class WhisperReconnectThread extends Thread {

	int tries = 0;

	public WhisperReconnectThread() {
	}

	@Override
	public void run() {
		Sys.out("WhisperBot disconnected.");
		try {
			Thread.sleep(7000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				if (WhisperBot.bot != null) {
					if (WhisperBot.bot.isConnected()) {
						tries = 0;
						break;
					} else {
						tries++;
						Sys.out("WhisperBot: Attempting to reconnect... (" + tries + ")");
						WhisperBot.bot.close();
						WhisperBot.bot = new PircBotX(WhisperBot.config);
						WhisperBot.bot.startBot();
					}
				}
				this.sleep(5000L);
			} catch (Exception io) {
				io.printStackTrace();
			}
		}
	}

}