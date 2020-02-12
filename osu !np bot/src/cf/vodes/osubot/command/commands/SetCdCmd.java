package cf.vodes.osubot.command.commands;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;
import cf.vodes.osubot.options.OptionManager;

public class SetCdCmd extends Command {

	public SetCdCmd() {
		super(new String[] { "setCooldown", "setCD", "cooldown", "cd" }, PermissionLevel.mod);
	}

	@Override
	public void run(String message) {
		
		double cooldown = (double)OptionManager.getOptionValue("Command-Cooldown");
		
		if(message.isEmpty()) {
			this.respond("Current Cooldown (in MS): " + cooldown + " | Please specify a Cooldown.");
			return;
		}
		
		String s = message;
		
		if (message.contains(" ")) {
			s = message.split(" ")[0];
		}
		
		double set = Double.parseDouble(s);

		OptionManager.setOptionValue("Command-Cooldown", set);

		this.respond("Command Cooldown set to: " + set + " ms");
		OptionManager.saveOptions();
	}

}
