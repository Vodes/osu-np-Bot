package cf.vodes.osubot.command.commands;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;

public class ModCmd extends Command{

	public ModCmd() {
		super(new String[] {"mod"}, PermissionLevel.streamer);
	}
	
	@Override
	public void run(String message) {
		if(message.isEmpty()) {
			this.respond("Please specify a user to mod.");
			return;
		}
		
		if(message.equalsIgnoreCase("list")) {
			
			if(Main.mods.mods.isEmpty()) {
				this.respond("There are no registered mods.");
				return;
			}
			
			String response = "Registered Mods are: ";
			
			for(String s : Main.mods.mods) {
				response += s + ", ";
			}
			
			this.respond(response);
			return;
		}
		
		Main.mods.mods.add(message.toLowerCase());
		this.respond("Mod added: " + message.toLowerCase());
		Main.mods.save();
	}
	
	

}
