package cf.vodes.osubot.window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.Command;

public class WindowCommands {
	
	public static JPanel panel_4 = new JPanel();
	public static int yCord = 10;
	
	public static void init(JPanel panel_2) {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 465, 355);
		panel_2.add(scrollPane);
		
		scrollPane.setViewportView(panel_4);
		panel_4.setLayout(null);
		
		
		for(Command cmd : Main.cmdManager.commands) {
			
			String tooltip = "";
			for(String st : cmd.getNames()) {
				tooltip += st + ", ";
			}
			
			//TODO: Label
			JLabel lblCmd = new JLabel(cmd.getId());
			lblCmd.setBounds(12, yCord, 183, 21);
			lblCmd.setToolTipText(tooltip);
			panel_4.add(lblCmd);
			//TODO: Button Enabled
			JToggleButton tglbtnEnabled = new JToggleButton("Enabled");
			tglbtnEnabled.setBounds(213, yCord - 8, 111, 36);
			tglbtnEnabled.setSelected(cmd.isEnabled());
			tglbtnEnabled.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnEnabled.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					cmd.setEnabled(tglbtnEnabled.isSelected());
					Main.cmdManager.saveEnabled();					
				}
			});
			panel_4.add(tglbtnEnabled);
			//TODO: Button Whisper
			JToggleButton tglbtnWhisper = new JToggleButton("Whisper");
			tglbtnWhisper.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnWhisper.setSelected(cmd.respondInWhisper);
			tglbtnWhisper.setBounds(336, yCord - 8, 128, 36);
			tglbtnWhisper.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					cmd.respondInWhisper = tglbtnWhisper.isSelected();
					Main.cmdManager.saveEnabled();					
				}
			});
			yCord += 35;
		}
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(9, 0));
		scrollPane.getVerticalScrollBar().setUnitIncrement(4);
		panel_4.setPreferredSize(new Dimension(panel_4.WIDTH, yCord + 5));
	}

}
