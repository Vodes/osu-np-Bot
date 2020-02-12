package cf.vodes.osubot.command.commands;

import org.apache.commons.io.FileUtils;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.permission.PermissionLevel;

public class NpCmd extends Command{

	public NpCmd() {
		super(new String[] {"np", "song", "playing"}, PermissionLevel.everyone);
	}
	
	@Override
	public void run(String message) {
		try {
			this.respond(buildMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String buildMessage() throws Exception {
		boolean somethingIsPlaying = false;
		String response = "";
		String npAll = FileUtils.readFileToString(Main.files.npAll);
		String mods = FileUtils.readFileToString(Main.files.mods);
		String dl = FileUtils.readFileToString(Main.files.npDL);
				
		if(!npAll.isEmpty()) {
			somethingIsPlaying = true;
		}
		
		if(somethingIsPlaying) {
			response = npAll.split("(?i)CS:")[0].trim();
			if(!mods.isEmpty()) {
				response = response + " +" + mods; 
			}
			response = response + " | DL: " + dl; 
			return response;
		}
		
		return (Main.scRunning ? "Nothing playing at the moment." : "StreamCompanion isnt running.");
	}

}
