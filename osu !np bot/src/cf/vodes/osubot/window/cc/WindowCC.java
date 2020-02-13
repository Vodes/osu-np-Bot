package cf.vodes.osubot.window.cc;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.command.custom.CustomCommand;

public class WindowCC {

	public static void init() {
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 465, 314);
		Main.win.ccPanel.add(scrollPane_1);

		JPanel panel_5 = new JPanel();
		scrollPane_1.setViewportView(panel_5);
		panel_5.setLayout(null);

		int yCord = 12;

		for (CustomCommand cc : Main.ccloader.ccs) {
			// TODO: Custom Command Label
			JLabel lblCC = new JLabel("<html><u>" + cc.getCommand());
			lblCC.setHorizontalAlignment(SwingConstants.CENTER);
			lblCC.setFont(new Font("Dialog", Font.BOLD, 14));
			lblCC.setBounds(0, yCord, 465, 21);
			panel_5.add(lblCC);
			// TODO: Togglebutton Enabled
			JToggleButton tglbtnEnabled = new JToggleButton("Enabled");
			tglbtnEnabled.setSelected(cc.isEnabled());
			tglbtnEnabled.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnEnabled.setBounds(0, yCord + 33, 103, 36);
			tglbtnEnabled.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cc.setEnabled(tglbtnEnabled.isSelected());
					Main.ccloader.save();
				}
			});
			panel_5.add(tglbtnEnabled);
			// TODO: Togglebutton Whisper
			JToggleButton tglbtnWhisper = new JToggleButton("Whisper");
			tglbtnWhisper.setHorizontalAlignment(SwingConstants.LEFT);
			tglbtnWhisper.setBounds(115, yCord + 33, 103, 36);
			tglbtnWhisper.setSelected(cc.respondInWhisper);
			tglbtnWhisper.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cc.respondInWhisper = tglbtnWhisper.isSelected();
					Main.ccloader.save();
				}
			});
//			panel_5.add(tglbtnWhisper);
			// TODO: Button Edit
			JButton btnEdit = new JButton("Edit");
			btnEdit.setBounds(232, yCord + 33, 94, 37);
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					WindowCC.editCC(cc);
				}
			});
			panel_5.add(btnEdit);

			// TODO: Button Remove
			JButton btnRemove = new JButton("Remove");
			btnRemove.setBounds(338, yCord + 33, 127, 37);
			btnRemove.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					WindowCC.removeCC(cc);
					WindowCC.reloadCcPanel();
					Main.ccloader.save();
				}
			});
			panel_5.add(btnRemove);

			yCord += 86;
		}
		scrollPane_1.getVerticalScrollBar().setPreferredSize(new Dimension(6, 0));
		scrollPane_1.getVerticalScrollBar().setUnitIncrement(4);
		panel_5.setPreferredSize(new Dimension(panel_5.WIDTH, yCord + 40));

		JButton btnAddNew = new JButton("Add new");
		btnAddNew.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAddNew.setBounds(0, 318, 465, 37);
		btnAddNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WindowCC.createCC();
			}
		});
		Main.win.ccPanel.add(btnAddNew);
	}

	public static void reloadCcPanel() {
		Main.win.ccPanel.removeAll();
		WindowCC.init();
	}

	private static void editCC(CustomCommand cc) {
		WindowEditCC win = new WindowEditCC();
		win.initialize(cc);
		win.frmEditing.setBounds(Main.win.frmOsunpbot.getMousePosition().x + 50,
		Main.win.frmOsunpbot.getMousePosition().y + 50, win.frmEditing.getWidth(), win.frmEditing.getHeight());
		win.frmEditing.setVisible(true);
		win.frmEditing.toFront();
	}

	public static void removeCC(CustomCommand cc) {
		ArrayList<CustomCommand> temp = new ArrayList<CustomCommand>();
		
		for(CustomCommand cCmd : Main.ccloader.ccs) {
			if(cCmd != cc) {
				temp.add(cCmd);
			}
		}
		
		Main.ccloader.ccs = temp;
	}

	private static void createCC() {
		WindowCreateCC win = new WindowCreateCC();
		win.initialize();
		win.frmEditing.setBounds(Main.win.frmOsunpbot.getMousePosition().x + 50,Main.win.frmOsunpbot.getMousePosition().y + 50, win.frmEditing.getWidth(), win.frmEditing.getHeight());
		win.frmEditing.setVisible(true);
		win.frmEditing.toFront();
	}

}
