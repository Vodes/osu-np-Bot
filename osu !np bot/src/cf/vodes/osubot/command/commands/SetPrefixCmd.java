package cf.vodes.osubot.command.commands;

import org.pircbotx.User;

import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;
import cf.vodes.osubot.options.OptionManager;

public class SetPrefixCmd extends Command {

	public SetPrefixCmd() {
		super("setPrefix", new String[] {"setPrefix", "prefix"}, PermissionLevel.streamer);
	}
	
	@Override
	public void run(String message, User user) {
		
		String prefix = (String) OptionManager.getOptionValue("Command-Prefix");
		
		if(message.isEmpty()) {
			this.respond("Current Prefix: " + prefix + " | Please specify a Prefix.", user);
			return;
		}
		
		String s = message;
		
		if(message.contains(" ")) {
			s = message.split(" ")[0];
		}
		
		OptionManager.setOptionValue("Command-Prefix", s);
		
		this.respond("Command Prefix set to: " + s, user);
		OptionManager.saveOptions();
	}

}
