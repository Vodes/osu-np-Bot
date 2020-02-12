package cf.vodes.osubot.command.commands;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;

public class UnModCmd extends Command {

	public UnModCmd() {
		super(new String[] { "unmod" }, PermissionLevel.streamer);
	}

	@Override
	public void run(String message) {
		if (message.isEmpty()) {
			this.respond("Please specify a user to unmod.");
			return;
		}

		for (String s : Main.mods.mods) {
			if (message.equalsIgnoreCase(s)) {
				Main.mods.mods.remove(s);
				this.respond("Mod removed.");
				Main.mods.save();
				return;
			}
		}

		this.respond("No Mod found with that name.");

	}

}
