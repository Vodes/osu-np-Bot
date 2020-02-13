package cf.vodes.osubot.command.custom;

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
import cf.vodes.osubot.options.OptionManager;

//Custom Command Loader
public class CCLoader {
	
	public ArrayList<CustomCommand> ccs;
	
	public File ccFile = new File(Main.files.directory, "customcommands.txt");
	
	public void runCommand(String command, User user) {
		String toEdit = StringUtils.removeIgnoreCase(command, ((String)OptionManager.getOptionValue("Command-Prefix")));
		for(CustomCommand cc : ccs) {
			if(toEdit.equalsIgnoreCase(cc.getCommand())) {
				cc.run(user);
			}
		}
	}
	
	public void load() {
		Sys.out("Loading Customcommands...");
		ccs = new ArrayList<CustomCommand>();
		if(ccFile.exists()) {
			try {
				if(!FileUtils.readFileToString(ccFile, "UTF-8").isEmpty()) {
	                BufferedReader reader = new BufferedReader(new FileReader(ccFile));
	                ArrayList<String> lines = new ArrayList<String>();
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    lines.add(line);
	                }
	                
	                for (String s : lines) {
	                    if (s.contains(";") && !s.startsWith("#")) {
	                    	String[] parts = s.split(";");
	                    	CustomCommand cc = new CustomCommand(parts[0], CustomCommand.ccTypeForString(parts[1]), new File(parts[2]), parts[3]);
	                    	if(parts.length > 4) {
	                    		cc.setEnabled(Boolean.parseBoolean(parts[4]));
	                    		cc.respondInWhisper = Boolean.parseBoolean(parts[5]);
	                    	}
	                    	ccs.add(cc);
	                    }
	                }
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Sys.out(ccs.isEmpty() ? "No customcommands found." : ("Loaded Customcommands! (" + ccs.size() + ")"));
	}
	
	public void save() {
		if(ccFile.exists()) {
			ccFile.delete();
		}
		try {
			ccFile.createNewFile();
            FileWriter fw = new FileWriter(ccFile, true);
			for(CustomCommand cmd : ccs) {
				fw.write(cmd.getCommand() + ";" + CustomCommand.StringForCCType(cmd.getType()) + ";" + cmd.getFile().getAbsolutePath() + ";" + cmd.getResponse() + ";" + cmd.isEnabled() + ";" + cmd.respondInWhisper +  "\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
