package cf.vodes.osubot;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.io.FileUtils;

import cf.vodes.osubot.options.OptionManager;
import cf.vodes.osubot.threads.StartBotThread;
import cf.vodes.vcrypt.EncryptionType;
import cf.vodes.vcrypt.vCrypt;

public class Window {

	public JFrame frmOsunpbot;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	
	public JLabel lblStatus = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.initialize();
					window.frmOsunpbot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		setUIFont(new FontUIResource(new Font("Dialog", Font.PLAIN, 13)));
		frmOsunpbot = new JFrame();
		frmOsunpbot.setTitle("osu!np-Bot");
		frmOsunpbot.setResizable(false);
		frmOsunpbot.setBounds(100, 100, 450, 400);
		frmOsunpbot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOsunpbot.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.PLAIN, 15));
		tabbedPane.setBounds(12, -11, 420, 369);
		frmOsunpbot.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Main", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTwitchChannel = new JLabel("Twitch Username:");
		lblTwitchChannel.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblTwitchChannel.setBounds(12, 12, 312, 21);
		panel.add(lblTwitchChannel);
		
		textField = new JTextField();
		textField.setBounds(12, 33, 312, 26);
		panel.add(textField);
		textField.setText((String)OptionManager.getOptionValue("Username"));
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OptionManager.setOptionValue("Username", textField.getText());
				OptionManager.saveOptions();
			}
		});
		btnSave.setBounds(336, 28, 79, 37);
		panel.add(btnSave);
		
		JLabel lblOauthkey = new JLabel("oAuth-Key:");
		lblOauthkey.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblOauthkey.setBounds(12, 71, 312, 21);
		panel.add(lblOauthkey);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(12, 98, 312, 26);
		passwordField.setText(Main.oauthKey);
		panel.add(passwordField);
		
		JButton btnSave_1 = new JButton("Save");
		btnSave_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.files.oAuthFile.exists()){
					Main.files.oAuthFile.delete();
				}
				try {
					Main.files.oAuthFile.createNewFile();
					Main.oauthKey = passwordField.getText();
					FileUtils.writeStringToFile(Main.files.oAuthFile, vCrypt.encode(passwordField.getText(), EncryptionType.Base16), false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave_1.setBounds(336, 92, 79, 37);
		panel.add(btnSave_1);
		
		JLabel lblStreamcompanionfilesDirectory = new JLabel("StreamCompanion \"Files\" Directory Path:");
		lblStreamcompanionfilesDirectory.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblStreamcompanionfilesDirectory.setBounds(12, 136, 312, 21);
		panel.add(lblStreamcompanionfilesDirectory);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText((String)OptionManager.getOptionValue("StreamCompanion-Files-Path"));
		textField_1.setBounds(12, 156, 312, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnFile = new JButton("File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				f.showSaveDialog(Main.win.frmOsunpbot);
				
				textField_1.setText(f.getSelectedFile().getAbsolutePath());
				OptionManager.setOptionValue("StreamCompanion-Files-Path", textField_1.getText());
				OptionManager.saveOptions();
			}
		});
		btnFile.setBounds(336, 151, 79, 37);
		panel.add(btnFile);
		
		JLabel lbls = new JLabel("Status:");
		lbls.setFont(new Font("Dialog", Font.BOLD, 14));
		lbls.setHorizontalAlignment(SwingConstants.CENTER);
		lbls.setBounds(0, 194, 415, 21);
		panel.add(lbls);
		
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblStatus.setBounds(0, 216, 415, 21);
		panel.add(lblStatus);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatus.setText("Connecting...");
				new StartBotThread().start();
			}
		});
		btnStart.setFont(new Font("Dialog", Font.BOLD, 15));
		btnStart.setBounds(12, 255, 391, 37);
		panel.add(btnStart);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Info", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 391, 179);
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
		btnGetOauthkey.setBounds(257, 255, 146, 37);
		panel_1.add(btnGetOauthkey);
	}

	public void setUIFont(FontUIResource f) {
		Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, f);
			}
		}
	}
}
