package cf.vodes.osubot.command.commands;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;
import cf.vodes.osubot.options.OptionManager;

public class SetPrefixCmd extends Command {

	public SetPrefixCmd() {
		super(new String[] {"setPrefix", "prefix"}, PermissionLevel.streamer);
	}
	
	@Override
	public void run(String message) {
		
		String prefix = (String) OptionManager.getOptionValue("Command-Prefix");
		
		if(message.isEmpty()) {
			this.respond("Current Prefix: " + prefix + " | Please specify a Prefix.");
			return;
		}
		
		String s = message;
		
		if(message.contains(" ")) {
			s = message.split(" ")[0];
		}
		
		OptionManager.setOptionValue("Command-Prefix", s);
		
		this.respond("Command Prefix set to: " + s);
		OptionManager.saveOptions();
	}

}
