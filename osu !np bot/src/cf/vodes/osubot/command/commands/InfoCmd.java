package cf.vodes.osubot.command.commands;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;
import cf.vodes.osubot.options.OptionManager;

public class InfoCmd extends Command{

	public InfoCmd() {
		super(new String[] {"info", "dl", "download", "help"}, PermissionLevel.everyone);
	}
	
	@Override
	public void run(String message) {
		String prefix = (String)OptionManager.getOptionValue("Command-Prefix");
		this.respond("Download this Tool at: https://github.com/Vodes/osu-np-Bot/");
		this.respond("For a list of Commands do " + prefix +  "commands");
	}

}
