package cf.vodes.osubot.command.commands;

import org.pircbotx.User;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;
import cf.vodes.osubot.options.OptionManager;

public class CommandListCmd extends Command{

	public CommandListCmd() {
		super("commandlist", new String[] {"commands", "commandlist"}, PermissionLevel.everyone);
	}
	
	@Override
	public void run(String message, User user) {
		this.respond("https://github.com/Vodes/osu-np-Bot/blob/master/commands.txt", user);
		
	}
	
	

}
