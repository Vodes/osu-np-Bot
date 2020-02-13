package cf.vodes.osubot.window;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.commons.io.FileUtils;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.options.OptionManager;
import cf.vodes.osubot.threads.StartBotThread;
import cf.vodes.vcrypt.EncryptionType;
import cf.vodes.vcrypt.vCrypt;

public class WindowMain {
	
	public static void init(JPanel panel) {
		JLabel lblTwitchChannel = new JLabel("Twitch Username:");
		lblTwitchChannel.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblTwitchChannel.setBounds(12, 12, 312, 21);
		panel.add(lblTwitchChannel);
		
		Main.win.textField = new JTextField();
		Main.win.textField.setBounds(12, 33, 312, 26);
		Main.win.textField.setText((String)OptionManager.getOptionValue("Username"));
		Main.win.textField.setColumns(10);
		panel.add(Main.win.textField);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OptionManager.setOptionValue("Username", Main.win.textField.getText());
				OptionManager.saveOptions();
			}
		});
		btnSave.setBounds(336, 28, 117, 37);
		panel.add(btnSave);
		
		JLabel lblOauthkey = new JLabel("oAuth-Key:");
		lblOauthkey.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblOauthkey.setBounds(12, 71, 312, 21);
		panel.add(lblOauthkey);
		
		Main.win.passwordField = new JPasswordField();
		Main.win.passwordField.setBounds(12, 98, 312, 26);
		Main.win.passwordField.setText(Main.oauthKey);
		panel.add(Main.win.passwordField);
		
		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.files.oAuthFile.exists()){
					Main.files.oAuthFile.delete();
				}
				try {
					Main.files.oAuthFile.createNewFile();
					Main.oauthKey = Main.win.passwordField.getText();
					FileUtils.writeStringToFile(Main.files.oAuthFile, vCrypt.encode(Main.win.passwordField.getText(), EncryptionType.Base16), false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave_1.setBounds(336, 92, 117, 37);
		panel.add(btnSave_1);
		
		JLabel lblStreamcompanionfilesDirectory = new JLabel("StreamCompanion \"Files\" Directory Path:");
		lblStreamcompanionfilesDirectory.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblStreamcompanionfilesDirectory.setBounds(12, 136, 312, 21);
		panel.add(lblStreamcompanionfilesDirectory);
		
		Main.win.textField_1 = new JTextField();
		Main.win.textField_1.setEditable(false);
		Main.win.textField_1.setText((String)OptionManager.getOptionValue("StreamCompanion-Files-Path"));
		Main.win.textField_1.setBounds(12, 156, 312, 26);
		panel.add(Main.win.textField_1);
		Main.win.textField_1.setColumns(10);
		
		JButton btnFile = new JButton("File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				f.showSaveDialog(Main.win.frmOsunpbot);
				
				Main.win.textField_1.setText(f.getSelectedFile().getAbsolutePath());
				OptionManager.setOptionValue("StreamCompanion-Files-Path", Main.win.textField_1.getText());
				OptionManager.saveOptions();
			}
		});
		btnFile.setBounds(336, 151, 117, 37);
		panel.add(btnFile);
		
		JLabel lbls = new JLabel("Status:");
		lbls.setFont(new Font("Dialog", Font.BOLD, 14));
		lbls.setHorizontalAlignment(SwingConstants.CENTER);
		lbls.setBounds(0, 194, 465, 21);
		panel.add(lbls);
		
		Main.win.lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		Main.win.lblStatus.setFont(new Font("Dialog", Font.PLAIN, 13));
		Main.win.lblStatus.setBounds(0, 216, 465, 21);
		panel.add(Main.win.lblStatus);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.win.lblStatus.setText("Connecting...");
				new StartBotThread().start();
				btnStart.setEnabled(false);
			}
		});
		btnStart.setFont(new Font("Dialog", Font.BOLD, 15));
		btnStart.setBounds(12, 306, 441, 37);
		panel.add(btnStart);
		
		JLabel lblStreamcompanion = new JLabel("StreamCompanion:");
		lblStreamcompanion.setHorizontalAlignment(SwingConstants.CENTER);
		lblStreamcompanion.setFont(new Font("Dialog", Font.BOLD, 14));
		lblStreamcompanion.setBounds(0, 249, 465, 21);
		panel.add(lblStreamcompanion);
		
		Main.win.labelSC.setHorizontalAlignment(SwingConstants.CENTER);
		Main.win.labelSC.setFont(new Font("Dialog", Font.PLAIN, 13));
		Main.win.labelSC.setBounds(0, 273, 465, 21);
		panel.add(Main.win.labelSC);
	}

}
