package File;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import Covert.AmmeterConvert;
import constant.Constant;
import entity.Ammeter;

public class OutputFile {

	public void writeAll(List<Ammeter> ammeters, String path) {
		for (Ammeter ammeter : ammeters) {
			write(ammeter, path);
		}
	}

	public void writeCount(List<Ammeter> ammeters, String path) {
		for (Ammeter ammeter : ammeters) {
			writeGroup(ammeter, path);
		}
	}

	public void write(Ammeter ammeter, String path) {
		String filePath = new StringBuilder().append(path).append(ammeter.getAmmeterId()).append(".csv").toString();
		System.out.println(filePath);
		File file = new File(filePath);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
		}
		try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true),
				Constant.ENCODING)) {
			outputStreamWriter.write(AmmeterConvert.toCSV(ammeter, false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeGroup(Ammeter ammeter, String path) {
		String filePath = path + "AmmeterGroup.csv";
		File file = new File(filePath);
		if (!file.exists()) {
			file.getParentFile().mkdirs();
		}
		try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file, true),
				Constant.ENCODING)) {
			outputStreamWriter.write(AmmeterConvert.toCSV(ammeter, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean deleteAll(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return false;
		}
		String[] files = file.list();
		File temp = null;
		for (String fileName : files) {
			temp = new File(path, fileName);
			if (temp.isDirectory()) {
				deleteAll(temp.getAbsolutePath());
				temp.delete();
			} else {
				if (!temp.delete()) {
					return false;
				}
			}
		}
		return true;
	}

}
