package cf.vodes.osubot.options;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cf.vodes.osubot.Main;
import cf.vodes.osubot.Sys;
import cf.vodes.osubot.options.optiontypes.EnumOptionTypes;
import cf.vodes.osubot.options.optiontypes.OptionBoolean;
import cf.vodes.osubot.options.optiontypes.OptionDouble;
import cf.vodes.osubot.options.optiontypes.OptionString;
import cf.vodes.osubot.options.optiontypes.OptionStringArray;

public class OptionManager {

	public static ArrayList<Option> options = new ArrayList<Option>();

	private static File file;
	private static FileWriter fw;
	private static BufferedWriter writer;

	public static void saveOptions() {
		file = new File(Main.files.directory.getAbsolutePath(), "options.txt");
		if (!Main.files.directory.exists()) {
			Main.files.directory.mkdir();
		}
		if (file.exists()) {
			file.delete();
		}
		BufferedWriter writer1;
		try {
			writer1 = new BufferedWriter(new FileWriter(file));
			writer1.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			fw = new FileWriter(file, true);
			writer = new BufferedWriter(fw);
			for (Option op : OptionManager.options) {
				String optionname = op.getName();
				if (op.getType().equals(EnumOptionTypes.Boolean)) {
					OptionBoolean opb = (OptionBoolean) op;
					writer.write(opb.value + ";" + optionname);
					writer.newLine();
				} else if (op.getType().equals(EnumOptionTypes.Double)) {
					OptionDouble opd = (OptionDouble) op;
					writer.write(opd.getValue() + ";" + optionname);
					writer.newLine();
				} else if (op.getType().equals(EnumOptionTypes.String)) {
					OptionString ops = (OptionString) op;
					writer.write(ops.getValue() + ";" + optionname);
					writer.newLine();
				} else if (op.getType().equals(EnumOptionTypes.StringArray)) {
					OptionStringArray opd = (OptionStringArray) op;
					writer.write(opd.getCurrentMode() + ";" + optionname);
					writer.newLine();
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			Sys.out("What might help is deleting the options.txt in " + Main.files.directory.getAbsolutePath(),
					"error");
		}
	}

	public static void loadOptions() {
		file = new File(Main.files.directory.getAbsolutePath(), "options.txt");
		if (!file.exists()) {
			return;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {
				String optionname = line.split(";")[1];
				for (Option op : OptionManager.options) {
					if (op.getName().equalsIgnoreCase(optionname)) {
						if (op.getType().equals(EnumOptionTypes.Boolean)) {
							op.setValueGeneral(Boolean.parseBoolean(line.split(";")[0]));
						} else if (op.getType().equals(EnumOptionTypes.Double)) {
							op.setValueGeneral(Double.parseDouble(line.split(";")[0]));
						} else if (op.getType().equals(EnumOptionTypes.String)) {
							op.setValueGeneral((line.split(";")[0]));
						} else if (op.getType().equals(EnumOptionTypes.StringArray)) {
							op.setValueGeneral(line.split(";")[0]);
						} else if (op.getType().equals(EnumOptionTypes.StringArray)) {
							op.setValueGeneral(line.split(";")[0]);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			Sys.out("What might help is deleting the options.txt in " + Main.files.directory.getAbsolutePath(),
					"error");
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			Sys.out("What might help is deleting the options.txt in " + Main.files.directory.getAbsolutePath(),
					"error");
		}
	}

	public static void setOptionValue(String name, Object value) {
		for (Option op : OptionManager.options) {
			if (name.equalsIgnoreCase(op.getName())) {
				op.setValueGeneral(value);
			}
		}
	}

	public static Object getOptionValue(String name) {
		if (name.equalsIgnoreCase("Darkmode")) {
			return OptionManager.isDarkmode();
		}
		for (Option op : OptionManager.options) {
			if (name.equalsIgnoreCase(op.getName())) {
				return (op.getValueGeneral(op));
			}
		}
		return null;
	}

	public static boolean isDarkmode() {
		if (getOptionValue("Theme").toString().equalsIgnoreCase("WhiteTheme")) {
			return false;
		}
		return true;
	}

}
