package cf.vodes.osubot;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class Util {
	
	public static boolean checkForNewerVersions() {
		try {
			Double using = Double.parseDouble(Main.VERSION);
			String s;
            URL urL = new URL("https://raw.githubusercontent.com/Vodes/osu-np-Bot/master/latestversion.txt");
            BufferedReader connection = new BufferedReader(new InputStreamReader(urL.openStream()));
            s = connection.readLine();
			Double latest = Double.parseDouble(s);
			if(using < latest) {
				if(Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(new URI("https://github.com/Vodes/osu-np-Bot/releases"));
				}
				return true;
			}
		} catch(Exception e) {
			
		}
		
		return false;
	}
	
    public static String resolveText(String url){
        String fina = "";
        ArrayList<String> lines = new ArrayList<String>();
        try{
            URL urL = new URL(url);
            BufferedReader connection = new BufferedReader(new InputStreamReader(urL.openStream()));
            String line;
            while ((line = connection.readLine()) != null) {
                lines.add(line);
            }
            connection.close();
        }catch (Exception io){
            io.printStackTrace();
        }
        for (String s : lines) {
            fina = (fina + s + "\n");
        }
        return fina;
    }

}
