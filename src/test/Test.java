package test;

import File.InputFile;
import File.OutputFile;
import constant.Constant;

public class Test {

	public static void main(String[] args) {
		OutputFile.deleteAll(Constant.OUTPUTFILE_PATH);
		InputFile inputFile = new InputFile();
		inputFile.loopInputFile();
		inputFile.loopOutputFile();
		OutputFile.deleteAll(Constant.OUTPUT_TEMP_PATH);
	}

}
