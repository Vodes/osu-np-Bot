package cf.vodes.osubot;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;

import javax.swing.Action;
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
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.io.FileUtils;

import cf.vodes.osubot.command.Command;
import cf.vodes.osubot.command.custom.CustomCommand;
import cf.vodes.osubot.options.OptionManager;
import cf.vodes.osubot.threads.StartBotThread;
import cf.vodes.osubot.window.WindowCommands;
import cf.vodes.osubot.window.WindowInfo;
import cf.vodes.osubot.window.WindowMain;
import cf.vodes.osubot.window.cc.WindowCC;
import cf.vodes.vcrypt.EncryptionType;
import cf.vodes.vcrypt.vCrypt;

public class Window {

	public JFrame frmOsunpbot;
	public JTextField textField;
	public JPasswordField passwordField;
	public JTextField textField_1;
	
	public JLabel lblStatus = new JLabel("");
	public JLabel labelSC = new JLabel("");
	public JPanel ccPanel = new JPanel();

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
		frmOsunpbot.setBounds(100, 100, 500, 450);
		frmOsunpbot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmOsunpbot.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.PLAIN, 15));
		tabbedPane.setBounds(12, -11, 470, 420);
		frmOsunpbot.getContentPane().add(tabbedPane);

		//TODO: Main Window
		JPanel panel = new JPanel();
		tabbedPane.addTab("Main", null, panel, null);
		panel.setLayout(null);
		WindowMain.init(panel);


		//TODO: Info Window
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Info", null, panel_1, null);
		panel_1.setLayout(null);
		WindowInfo.init(panel_1);
		
		//TODO: Command Window
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Commands", null, panel_2, null);
		panel_2.setLayout(null);
		WindowCommands.init(panel_2);
		
		tabbedPane.addTab("CustomCommands", null, ccPanel, null);
		ccPanel.setLayout(null);
		WindowCC.init();
		
//		JLabel lblExampleCommand = new JLabel("Example Command 124");
//		lblExampleCommand.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblExampleCommand.setHorizontalAlignment(SwingConstants.CENTER);
//		lblExampleCommand.setBounds(0, 12, 465, 21);
//		panel_5.add(lblExampleCommand);
//		
//		JToggleButton tglbtnEnabled = new JToggleButton("Enabled");
//		tglbtnEnabled.setHorizontalAlignment(SwingConstants.LEFT);
//		tglbtnEnabled.setBounds(0, 45, 103, 36);
//		panel_5.add(tglbtnEnabled);
//		
//		JToggleButton tglbtnWhisper = new JToggleButton("Whisper");
//		tglbtnWhisper.setHorizontalAlignment(SwingConstants.LEFT);
//		tglbtnWhisper.setBounds(115, 45, 103, 36);
//		panel_5.add(tglbtnWhisper);
//		
//		JButton btnEdit = new JButton("Edit");
//		btnEdit.setBounds(232, 45, 94, 37);
//		panel_5.add(btnEdit);
//		
//		JButton btnRemove = new JButton("Remove");
//		btnRemove.setBounds(338, 45, 127, 37);
//		panel_5.add(btnRemove);
//		
//		JLabel lblExampleCommand_1 = new JLabel("Example Command 258");
//		lblExampleCommand_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblExampleCommand_1.setFont(new Font("Dialog", Font.BOLD, 14));
//		lblExampleCommand_1.setBounds(0, 98, 465, 21);
//		panel_5.add(lblExampleCommand_1);
//		
//		JToggleButton toggleButton = new JToggleButton("Enabled");
//		toggleButton.setHorizontalAlignment(SwingConstants.LEFT);
//		toggleButton.setBounds(0, 131, 103, 36);
//		panel_5.add(toggleButton);
//		
//		JToggleButton toggleButton_1 = new JToggleButton("Whisper");
//		toggleButton_1.setHorizontalAlignment(SwingConstants.LEFT);
//		toggleButton_1.setBounds(115, 131, 103, 36);
//		panel_5.add(toggleButton_1);
//		
//		JButton button = new JButton("Edit");
//		button.setBounds(230, 131, 94, 37);
//		panel_5.add(button);
//		
//		JButton button_1 = new JButton("Remove");
//		button_1.setBounds(338, 131, 127, 37);
//		panel_5.add(button_1);
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
