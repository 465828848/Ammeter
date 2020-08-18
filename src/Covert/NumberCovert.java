package Covert;

import java.math.BigDecimal;

public class NumberCovert {
	public static BigDecimal parseBigDecimal(String str) {
		if (null == str || "".equals(str.trim())) {
			return BigDecimal.ZERO;
		}
		if (!str.matches("-?[0-9]+.?[0-9]*")) {
			return BigDecimal.ZERO;
		}
		return new BigDecimal(str);
	}
}
