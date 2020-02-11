package cf.vodes.osubot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import cf.vodes.osubot.options.OptionManager;
import cf.vodes.vcrypt.EncryptionType;
import cf.vodes.vcrypt.vCrypt;

public class Files {
	
	//General Stuff
    public File directory = new File(System.getProperty("user.home"), "Vodes//osu-twitch-bot");
    public File oAuthFile = new File(directory, "key.txt");
    
    //Logging
	public File logdirectory = new File(System.getProperty("user.home"), "Vodes//Logs");
	public File logfile = new File(logdirectory, "log-osu-np-bot.txt");
	
	//StreamCompanion Files
	public File npAll;
	public File mods;
	public File npDL;
	
	
	public void load() {
		if(!directory.exists()) {
			Sys.out("Seems like this has been used for the first time.", "WARN");
			Sys.out("Please setup everything.", "WARN");
			directory.mkdirs();
			return;
		}
		
		Sys.out("Loading Files and Options.");
		
		try {
			if(oAuthFile.exists() && !FileUtils.readFileToString(oAuthFile).isEmpty()) {
				Main.oauthKey = vCrypt.decode(FileUtils.readFileToString(oAuthFile), EncryptionType.Base16);
			}
		} catch (IOException e) {
			Sys.out("Couldnt read oAuthKey File!", "error");
			e.printStackTrace();
		}
		
		
		OptionManager.loadOptions();
		
		Sys.out("Done.");
	}

}
