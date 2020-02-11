package cf.vodes.osubot;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.io.output.TeeOutputStream;

import cf.vodes.osubot.irc.Listener;
import cf.vodes.osubot.options.OptionManager;
import cf.vodes.osubot.options.optiontypes.OptionString;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;

public class Main {
	
	public static String oauthKey = "";
	public static final String VERSION = "0.1";
	
	public static String infotext;
	
	public static Files files;
	
	public static Window win;
	
	public static Listener listener;
	
	public static void main(String[] args) {
		files = new Files();
		setupLog();
		Sys.out("Starting osu!np-Bot v" + VERSION);
		OptionManager.options.add(new OptionString("Username", "Set this up."));
		OptionManager.options.add(new OptionString("StreamCompanion-Files-Path", "Put in your path here."));
		files.load();
		
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel());
			MaterialLookAndFeel.changeTheme(new JMarsDarkTheme());
		} catch (UnsupportedLookAndFeelException e) {
			Sys.out("Something failed while setting the theme.", "ERROR");
			e.printStackTrace();
		}
		
		infotext = Util.resolveText("https://raw.githubusercontent.com/Vodes/osu-np-Bot/master/info.txt");
		
		win = new Window();
		win.setUIFont(new FontUIResource(new Font("Dialog", Font.PLAIN, 13)));
		win.initialize();
		win.frmOsunpbot.setVisible(true);
		win.frmOsunpbot.toFront();
		
		win.frmOsunpbot.setTitle("osu-np-bot " + (Util.checkForNewerVersions() ? "(Outdated, please Update!)" : ""));
	}
	
	private static void setupLog() {
		if(!files.logdirectory.exists()) {
			files.logdirectory.mkdirs();
		}
		
		if(files.logfile.exists()) {
			files.logfile.delete();
		}
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(files.logfile));
			writer.close();
			TeeOutputStream multiOutput = new TeeOutputStream(System.out, new PrintStream(files.logfile));
			PrintStream output = new PrintStream(multiOutput, true);
			System.setOut(output);
			System.setErr(output);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	

}
