package cf.vodes.osubot.threads;

import org.pircbotx.PircBotX;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;

public class ChatReconnectThread extends Thread {

	int tries = 0;

	public ChatReconnectThread() {
	}

	@Override
	public void run() {
		Sys.out("IRC disconnected.");
		try {
			Thread.sleep(7000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				if (Main.listener.bot != null) {
					if (Main.listener.bot.isConnected()) {
						tries = 0;
						break;
					} else {
						tries++;
						Sys.out("Attempting to reconnect... (" + tries + ")");
						Main.win.lblStatus.setText("<html><font color='red'>Disconnected: Reconnecting (" + tries + ")");
						Main.listener.bot.close();
						Main.listener.bot = new PircBotX(Main.listener.config);
						Main.listener.bot.startBot();
					}
				}
				this.sleep(5000L);
			} catch (Exception io) {
				io.printStackTrace();
			}
		}
	}

}