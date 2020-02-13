package cf.vodes.osubot.command.commands;

import org.pircbotx.User;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;

public class UnModCmd extends Command {

	public UnModCmd() {
		super("unmod", new String[] { "unmod" }, PermissionLevel.streamer);
	}

	@Override
	public void run(String message, User user) {
		if (message.isEmpty()) {
			this.respond("Please specify a user to unmod.", user);
			return;
		}

		for (String s : Main.mods.mods) {
			if (message.equalsIgnoreCase(s)) {
				Main.mods.mods.remove(s);
				this.respond("Mod removed.", user);
				Main.mods.save();
				return;
			}
		}

		this.respond("No Mod found with that name.", user);

	}

}
