package File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Covert.AmmeterConvert;
import constant.Constant;
import entity.Ammeter;
import util.CollectorsUtil;
import util.StringUtils;

public class InputFile {

	public void inputFile(String fileName) {
		String file = Constant.INPUTFILE_PATH + fileName;
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), Constant.ENCODING))) {
			String res = bufferedReader.readLine();
			Ammeter ammeter = null;
			while (res != null) {
				ammeter = new Ammeter();
				AmmeterConvert.toData(ammeter, res);
				ammeter.setAmmeterDate(StringUtils.getFileNameTrimEx(fileName));
				new OutputFile().write(ammeter, Constant.OUTPUT_TEMP_PATH);
				System.out.println(res);
				res = bufferedReader.readLine();
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public void inputFileOrderData(String fileName) {
		String file = Constant.OUTPUT_TEMP_PATH + fileName;
		List<Ammeter> ammeterList = new ArrayList<>();
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), Constant.ENCODING))) {
			String res = bufferedReader.readLine();
			Ammeter ammeter = null;
			while (res != null) {
				ammeter = new Ammeter();
				AmmeterConvert.toData(ammeter, res);
				ammeterList.add(ammeter);
				System.out.println(res);
				res = bufferedReader.readLine();
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		OutputFile outputFile = new OutputFile();
		List<Ammeter> ammeterSort = ammeterList.stream()
				.sorted(Comparator.comparing(Ammeter::getAmmeterAmount).reversed()
						.thenComparing(Ammeter::getAmmeterDate).thenComparing(Ammeter::getAmmeterTime))
				.collect(Collectors.toList());
		outputFile.writeAll(ammeterSort, Constant.OUTPUTFILE_PATH);
		List<Ammeter> ammeterGroup = new ArrayList<>();
		ammeterSort.stream()
				.collect(Collectors.groupingBy(ammeter -> new Ammeter(ammeter.getAmmeterId(), ammeter.getAmmeterDate()),
						CollectorsUtil.summingBigDecimal(ammeter -> ammeter.getAmmeterAmount())))
				.forEach((key, value) -> {
					key.setAmmeterAmount(value);
					ammeterGroup.add(key);
				});
		List<Ammeter> ammeterGroupOrder = ammeterGroup.stream().sorted(
				Comparator.comparing(Ammeter::getAmmeterAmount).reversed().thenComparing(Ammeter::getAmmeterDate))
				.collect(Collectors.toList());
		outputFile.writeCount(ammeterGroupOrder, Constant.OUTPUTFILE_PATH);
	}

	public void loopInputFile() {
		File filepath = new File(Constant.INPUTFILE_PATH);
		File[] files = filepath.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				System.out.println("loopInputFile" + file.getName());
				inputFile(file.getName());
			}
		}
	}

	public void loopOutputFile() {
		File filepath = new File(Constant.OUTPUT_TEMP_PATH);
		File[] files = filepath.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				System.out.println("loopOutputFile" + file.getName());
				inputFileOrderData(file.getName());
			}
		}
	}
}
