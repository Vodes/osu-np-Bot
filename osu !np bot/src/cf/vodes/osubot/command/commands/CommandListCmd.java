package cf.vodes.osubot.command.commands;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;
import cf.vodes.osubot.options.OptionManager;

public class CommandListCmd extends Command{

	public CommandListCmd() {
		super(new String[] {"commands", "commandlist"}, PermissionLevel.everyone);
	}
	
	@Override
	public void run(String message) {
//		String prefix = (String)OptionManager.getOptionValue("Command-Prefix");
//		String response = "Heres a list of Commands: ";
//		
//		boolean first = true;
//		
//		for(Command cmd : Main.cmdManager.commands) {
//			response += (first ? prefix + cmd.getNames()[0] : " | " + prefix + cmd.getNames()[0]);
//			first = false;
//		}
//		
//		Main.listener.bot.send().message(Main.listener.channel, response);
//		Main.listener.bot.send().message(Main.listener.channel, response);
		this.respond("https://github.com/Vodes/osu-np-Bot/blob/master/commands.txt");
		
	}
	
	

}
