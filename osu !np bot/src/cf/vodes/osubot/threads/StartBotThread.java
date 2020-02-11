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
import cf.vodes.osubot.options.OptionManager;

public class StartBotThread extends Thread{
	
	
	@Override
	public void run() {
		Main.listener = new Listener();
		File sc_directory = new File((String)OptionManager.getOptionValue("StreamCompanion-Files-Path"));
		Main.files.npAll = new File(sc_directory, "np_all.txt");
		Main.files.mods = new File(sc_directory, "current_mods.txt");
		Main.files.npDL = new File(sc_directory, "np_playing_DL.txt");
		if(anyFileInvalid()) {
			Main.win.lblStatus.setText("You messed up something with your SC Files.");
			try {
				Thread.sleep(1500L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Main.win.lblStatus.setText("Please configure your Stream Companion Files correctly.");
			Sys.out("Please configure your Stream Companion Files correctly.");
			return;
		}
		try {
			Main.listener.config = new Configuration.Builder()
					.setAutoNickChange(false)
					.setOnJoinWhoEnabled(false)
					.addServer("irc.twitch.tv")
					.setName(((String)OptionManager.getOptionValue("Username")).toLowerCase())
					.setServerPassword(Main.oauthKey)
					.addAutoJoinChannel("#" + ((String)OptionManager.getOptionValue("Username")).toLowerCase())
					.addListener(Main.listener)
					.setLogin("osu-np-bot")
					.buildConfiguration();
		}catch(Exception e2) {
			e2.printStackTrace();
			return;
		}

		Main.listener.bot = new PircBotX(Main.listener.config);
		try {
			Main.listener.bot.startBot();
		} catch (IOException | IrcException e) {
			e.printStackTrace();
		}
	}
	
	private boolean anyFileInvalid() {
		if(!Main.files.npAll.exists()) {
			return true;
		}
		if(!Main.files.mods.exists()) {
			return true;
		}
		if(!Main.files.npDL.exists()) {
			return true;
		}
		return false;
	}

}
