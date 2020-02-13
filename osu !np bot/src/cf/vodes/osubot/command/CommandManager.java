package cf.vodes.osubot.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.pircbotx.User;

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
	
	public File file;
	
	public void init() {
		file = new File(Main.files.directory, "cmdEnabled.txt");
		Sys.out("Loading Commands...");
		commands.add(new NpCmd());
		commands.add(new InfoCmd());
		commands.add(new ModCmd());
		commands.add(new UnModCmd());
		commands.add(new CommandListCmd());
		commands.add(new SetPrefixCmd());
		commands.add(new SetCdCmd());
		try {
			this.loadEnabled();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Sys.out("Commands loaded! (" + commands.size() + ")");
	}
	
	public void runCommand(String possibleName, String message, String user, User us) {
		for(Command cmd : commands) {
			for(String name : cmd.getNames()) {
				if(possibleName.equalsIgnoreCase(name) && cmd.isEnabled()) {
					if(cmd.getPerms() == PermissionLevel.streamer && !isStreamer(user)) {
						Main.listener.bot.send().message(Main.listener.channel, "You can not use this Command.");
						return;
					}
					if(cmd.getPerms() == PermissionLevel.mod && !isStreamer(user) && !Main.mods.isMod(user)) {
						Main.listener.bot.send().message(Main.listener.channel, "You can not use this Command.");
						return;
					}
					String passOn = StringUtils.removeIgnoreCase(message, name).trim();
					cmd.run(passOn, us);
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
	
	public void saveEnabled() {
		if(file.exists()) {
			file.delete();
		}
		
		try {
			file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
			for(Command cmd : Main.cmdManager.commands) {
				fw.write(cmd.getId() + ";" + cmd.isEnabled() + ";" + cmd.respondInWhisper + "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadEnabled() throws Exception {
		if(!file.exists() || FileUtils.readFileToString(file).isEmpty()) {
			return;
		}
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        
        for (String s : lines) {
        	if(!s.startsWith("#") && s.contains(";")) {
            	for(Command cmd : Main.cmdManager.commands) {
            		if(s.split(";")[0].equalsIgnoreCase(cmd.getId())) {
            			cmd.setEnabled(Boolean.parseBoolean(s.split(";")[1]));
            			cmd.respondInWhisper = Boolean.parseBoolean(s.split(";")[2]);
            		}
            	}
        	}
        }
	}

}
