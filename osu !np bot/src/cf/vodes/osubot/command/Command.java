package cf.vodes.osubot.command;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.permission.PermissionLevel;

public class Command {
	
	private String[] names;
	private PermissionLevel perms;
	
	public Command (String[] names, PermissionLevel perms) {
		this.perms = perms;
		this.names = names;
	}
	
	public void run(String message) {
		
	}

	public PermissionLevel getPerms() {
		return perms;
	}

	public String[] getNames() {
		return names;
	}
	
	
	protected void respond (String response) {
		Main.listener.bot.send().message(Main.listener.channel, response);
	}
	

}
