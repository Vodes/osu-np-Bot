package cf.vodes.osubot.command;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;
import cf.vodes.osubot.command.commands.CommandListCmd;
import cf.vodes.osubot.command.commands.InfoCmd;
import cf.vodes.osubot.command.commands.ModCmd;
import cf.vodes.osubot.command.commands.NpCmd;
import cf.vodes.osubot.command.commands.SetCdCmd;
import cf.vodes.osubot.command.commands.SetPrefixCmd;
import cf.vodes.osubot.command.commands.UnModCmd;
import cf.vodes.osubot.command.permission.PermissionLevel;
import cf.vodes.osubot.options.OptionManager;

public class CommandManager {
	
	public ArrayList<Command> commands = new ArrayList<Command>();
	
	public void init() {
		Sys.out("Loading Commands...");
		commands.add(new NpCmd());
		commands.add(new InfoCmd());
		commands.add(new ModCmd());
		commands.add(new UnModCmd());
		commands.add(new CommandListCmd());
		commands.add(new SetPrefixCmd());
		commands.add(new SetCdCmd());
		Sys.out("Commands loaded! (" + commands.size() + ")");
	}
	
	public void runCommand(String possibleName, String message, String user) {
		for(Command cmd : commands) {
			for(String name : cmd.getNames()) {
				if(possibleName.equalsIgnoreCase(name)) {
					if(cmd.getPerms() == PermissionLevel.streamer && !isStreamer(user)) {
						Main.listener.bot.send().message(Main.listener.channel, "You can not use this Command.");
						return;
					}
					if(cmd.getPerms() == PermissionLevel.mod && !isStreamer(user) && !Main.mods.isMod(user)) {
						Main.listener.bot.send().message(Main.listener.channel, "You can not use this Command.");
						return;
					}
					String passOn = StringUtils.removeIgnoreCase(message, name).trim();
					cmd.run(passOn);
				}
			}
		}
	}
	
	private boolean isStreamer(String user) {
		boolean b = false;
		if(user.equalsIgnoreCase((String)OptionManager.getOptionValue("Username"))) {
			b = true;
		}
		return b;
	}

}
