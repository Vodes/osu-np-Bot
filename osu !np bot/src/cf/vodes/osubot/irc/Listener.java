package cf.vodes.osubot.irc;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.MessageEvent;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;
import cf.vodes.osubot.options.OptionManager;
import cf.vodes.osubot.threads.ChatReconnectThread;

public class Listener extends ListenerAdapter{
	
	public PircBotX bot;
	public Configuration config;
	public Timer timer = new Timer();
	
	public String channel;
	
	@Override
	public void onConnect(ConnectEvent event) throws Exception {
		channel = "#" + ((String)OptionManager.getOptionValue("Username")).toLowerCase();
		bot.send().joinChannel(channel);
		Sys.out("IRC connected!");
		Main.win.lblStatus.setText("<html><font color='lime'>Connected!");
		super.onConnect(event);
	}
	
	@Override
	public void onDisconnect(DisconnectEvent event) throws Exception {
		new ChatReconnectThread().start();
		super.onDisconnect(event);
	}
	
	@Override
	public void onMessage(MessageEvent event) throws Exception {
		if(event.getMessage().startsWith((String)OptionManager.getOptionValue("Command-Prefix"))){
			float cooldownAsFloat = (float)((double)OptionManager.getOptionValue("Command-Cooldown"));
			if(timer.hasReached(cooldownAsFloat)) {
				String name = StringUtils.removeIgnoreCase(event.getMessage().split(" ")[0], (String)OptionManager.getOptionValue("Command-Prefix"));
				String messageWithoutPrefix = StringUtils.removeIgnoreCase(event.getMessage(), (String)OptionManager.getOptionValue("Command-Prefix"));
				Main.cmdManager.runCommand(name, messageWithoutPrefix, event.getUser().getNick());
			}
		}
	}
	
	private String buildMessage() throws Exception {
		boolean somethingIsPlaying = false;
		String response = "";
		String npAll = FileUtils.readFileToString(Main.files.npAll);
		String mods = FileUtils.readFileToString(Main.files.mods);
		String dl = FileUtils.readFileToString(Main.files.npDL);
				
		if(!npAll.isEmpty()) {
			somethingIsPlaying = true;
		}
		
		if(somethingIsPlaying) {
			response = npAll.split("(?i)CS:")[0].trim();
			if(!mods.isEmpty()) {
				response = response + " +" + mods; 
			}
			response = response + " | DL: " + dl; 
			return response;
		}
		return "Nothing playing at the moment.";
	}


}
