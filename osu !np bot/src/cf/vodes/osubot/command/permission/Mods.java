package cf.vodes.osubot.command.permission;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;

public class Mods {

	public ArrayList<String> mods = new ArrayList<String>();

	private File modfile = new File(Main.files.directory, "mods.txt");
	private FileWriter fw;
	private BufferedWriter writer;

	public void init() {
		Sys.out("Loading Mods...");
		try {
			this.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sys.out("Mods loaded! (" + mods.size() + ")");
	}
	
	public boolean isMod(String user) {
		boolean isMod = false;
		for(String s : mods) {
			if(user.equalsIgnoreCase(s)) {
				isMod = true;
			}
		}
		return isMod;
	}

	private void load() throws IOException {
		if(!modfile.exists() || FileUtils.readFileToString(modfile).isEmpty()) {
			return;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(modfile))) {
			for (String line; (line = br.readLine()) != null;) {
				mods.add(line);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public void save() {
		if (modfile.exists()) {
			modfile.delete();
		}
		try {
			modfile.createNewFile();
			fw = new FileWriter(modfile, true);
			writer = new BufferedWriter(fw);

			for (String s : mods) {
				writer.write(s);
				writer.newLine();
			}

			writer.close();
			writer = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
