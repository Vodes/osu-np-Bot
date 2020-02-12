package cf.vodes.osubot.threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import cf.vodes.osubot.Main;

public class StreamCompanionListener extends Thread{
	
	@Override
	public void run() {
		
		try {
			this.sleep(1000L);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true){
			try {
				Main.scRunning = isProcessRunning("streamcompanion.exe");
				Main.win.labelSC.setText((Main.scRunning ? "<html><font color='lime'>Running" : "<html><font color='red'>Probably not Running"));
				
				this.sleep(3500L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
	}
	
	public boolean isProcessRunning(String process) throws Exception {
		String line;
		String pidInfo = "";

		Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");

		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

		while ((line = input.readLine()) != null) {
			pidInfo += line;
		}

		input.close();

		if (StringUtils.containsIgnoreCase(pidInfo, process)) {
			return true;
		}

		return false;
	}

}
