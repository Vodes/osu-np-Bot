package cf.vodes.osubot.window;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import cf.vodes.osubot.Main;

public class WindowInfo {
	
	public static void init(JPanel panel_1) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 441, 179);
		panel_1.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		textPane.setText(Main.infotext);
		
		JButton btnGithub = new JButton("Github");
		btnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI("https://github.com/Vodes/osu-np-Bot/"));
					} catch (IOException e) {
						e.printStackTrace();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnGithub.setBounds(12, 255, 146, 37);
		panel_1.add(btnGithub);
		
		JButton btnGetOauthkey = new JButton("Get oAuthKey");
		btnGetOauthkey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI("https://twitchapps.com/tmi/"));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnGetOauthkey.setBounds(307, 255, 146, 37);
		panel_1.add(btnGetOauthkey);
		
		JButton btnCommands = new JButton("Commands");
		btnCommands.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI("https://github.com/Vodes/osu-np-Bot/blob/master/commands.txt"));
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnCommands.setBounds(12, 304, 146, 37);
		panel_1.add(btnCommands);
	}

}
