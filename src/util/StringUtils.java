package util;

import java.math.BigDecimal;

public class StringUtils {
	private static final String EMPTY = "";

	public static String nullToEmpty(Object obj) {
		if (obj == null) {
			return EMPTY;
		}
		if (obj instanceof BigDecimal) {
			return ((BigDecimal) obj).toPlainString();
		}
		return String.valueOf(obj);
	}

	public static String getFileNameTrimEx(String filename) {
		if (filename == null || "".equals(filename)) {
			return filename;
		}
		int index = filename.lastIndexOf('.');
		if (index < 0) {
			return filename;
		}
		return filename.substring(0, index);
	}
}
