package cf.vodes.osubot.window.cc;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.custom.CCType;
import cf.vodes.osubot.command.custom.CustomCommand;

public class WindowCreateCC {

	public JFrame frmEditing;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowCreateCC window = new WindowCreateCC();
//					window.initialize();
					window.frmEditing.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
//	public WindowEditCC() {
//		initialize();
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmEditing = new JFrame();
		frmEditing.setResizable(false);
		frmEditing.setTitle("Create a new Custom Command");
		frmEditing.setBounds(100, 100, 450, 325);
		frmEditing.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditing.getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name/Command (without Prefix)");
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(10, 11, 424, 21);
		frmEditing.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(10, 30, 424, 26);
		frmEditing.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAction = new JLabel("Action");
		lblAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblAction.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAction.setBounds(10, 67, 424, 21);
		frmEditing.getContentPane().add(lblAction);
		
		JRadioButton rdbtnTextResponse = new JRadioButton("Text Response");
		rdbtnTextResponse.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnTextResponse.setSelected(true);
		rdbtnTextResponse.setBounds(10, 95, 182, 29);
		
		JRadioButton rdbtntxtFileText = new JRadioButton(".txt File Text Response");
		rdbtntxtFileText.setHorizontalAlignment(SwingConstants.RIGHT);
		rdbtntxtFileText.setBounds(252, 95, 182, 29);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtntxtFileText);
		group.add(rdbtnTextResponse);
		frmEditing.getContentPane().add(rdbtntxtFileText);
		frmEditing.getContentPane().add(rdbtnTextResponse);
		
		JLabel lblTextifText = new JLabel("Text (If Text Response)");
		lblTextifText.setFont(new Font("Dialog", Font.BOLD, 13));
		lblTextifText.setBounds(10, 131, 202, 21);
		frmEditing.getContentPane().add(lblTextifText);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 163, 424, 26);
		frmEditing.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lbltxtFilePath = new JLabel(".txt File path");
		lbltxtFilePath.setFont(new Font("Dialog", Font.BOLD, 13));
		lbltxtFilePath.setBounds(10, 193, 182, 21);
		frmEditing.getContentPane().add(lbltxtFilePath);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 218, 304, 26);
		frmEditing.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnPathchooser = new JButton("Pathchooser");
		btnPathchooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser f = new JFileChooser();
				f.setFileSelectionMode(JFileChooser.FILES_ONLY);
				f.showSaveDialog(frmEditing);
				
				textField_2.setText(f.getSelectedFile().getAbsolutePath());
			}
		});
		btnPathchooser.setFont(new Font("Dialog", Font.BOLD, 12));
		btnPathchooser.setBounds(319, 213, 115, 37);
		frmEditing.getContentPane().add(btnPathchooser);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()) {
					return;
				}
				CustomCommand newcc;
				CCType type = (rdbtnTextResponse.isSelected() ? CCType.Text : CCType.File);
				newcc = new CustomCommand(textField.getText(), type, new File(textField_2.getText()), textField_1.getText());
				Main.ccloader.ccs.add(newcc);
				WindowCC.reloadCcPanel();
				frmEditing.dispose();
				Main.win.frmOsunpbot.toFront();
			}
		});
		btnSave.setBounds(10, 255, 424, 37);
		frmEditing.getContentPane().add(btnSave);
	}
}
