package cf.vodes.osubot.command;

import org.pircbotx.User;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.permission.PermissionLevel;
import cf.vodes.osubot.irc.whisper.WhisperBot;

public class Command {
	
	private String[] names;
	private String id;
	private PermissionLevel perms;
	private boolean enabled = true;
	public boolean respondInWhisper = false;
	
	
	public Command (String id, String[] names, PermissionLevel perms) {
		this.perms = perms;
		this.id = id;
		this.names = names;
	}
	
	public void run(String message, User user) {
		
	}

	public PermissionLevel getPerms() {
		return perms;
	}

	public String[] getNames() {
		return names;
	}
	
	protected void respond (String response, User user) {
		if(respondInWhisper) {
			WhisperBot.whisper(user.getNick(), response);
			return;
		}
		Main.listener.bot.send().message(Main.listener.channel, response);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getId() {
		return id;
	}

}
