package cf.vodes.osubot.command.custom;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.pircbotx.User;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.irc.whisper.WhisperBot;

public class CustomCommand {
	
	private String command;
	private CCType type;
	private File file;
	private String response;
	private boolean enabled = true;
	public boolean respondInWhisper = false;
	
	public CustomCommand(String command, CCType type, File file, String response) {
		this.command = command;
		this.type = type;
		this.file = file;
		this.response = response;
	}
	
	public void run(User user) {
		if(type == CCType.File) {
			String s = "";
			try {
				s = this.getStringFromFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(s == "" || s.isEmpty()) {
				return;
			}
			
			if(this.respondInWhisper) {
				WhisperBot.whisper(user.getNick(), s);
			} else {
				Main.listener.bot.send().message(Main.listener.channel, s);
			}
		} else if(type == CCType.Text) {
			if(this.respondInWhisper) {
				WhisperBot.whisper(user.getNick(), response);
			} else {
				Main.listener.bot.send().message(Main.listener.channel, response);
			}
		}
	}
	
	private String getStringFromFile() throws IOException {
		if(file == null || !file.exists()) {
			return "";
		}
		return FileUtils.readFileToString(file, "UTF-8");
	}
	
	public static CCType ccTypeForString(String s) {
		if(s.equalsIgnoreCase("text")) {
			return CCType.Text;
		} else {
			return CCType.File;
		}
	}
	
	public static String StringForCCType(CCType type) {
		if(type == CCType.Text) {
			return "text";
		} else {
			return "file";
		}
	}

	public File getFile() {
		return file;
	}

	public String getResponse() {
		return response;
	}

	public CCType getType() {
		return type;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCommand() {
		return command;
	}
}
