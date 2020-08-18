package Covert;

import java.lang.reflect.Field;

import entity.Ammeter;
import util.StringUtils;

public class AmmeterConvert {

	public static String toCSV(Object obj, boolean isGroup) {
		StringBuilder stringBuilder = new StringBuilder();
		Class<? extends Object> fieldClass = obj.getClass();
		Field[] fields = fieldClass.getDeclaredFields();
		Object value = null;
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				if (isGroup && "ammeterTime".equals(field.getName())) {
					continue;
				}
				value = field.get(obj);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stringBuilder.append(StringUtils.nullToEmpty(value));
			stringBuilder.append(',');
		}
		if (stringBuilder.length() > 0) {
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		}
		stringBuilder.append("\r\n");
		return stringBuilder.toString();
	}

	public static void toData(Object obj, String params) {
		String[] param = params.split(",");
		Ammeter ammeter = (Ammeter) obj;
		ammeter.setAmmeterId(param[0]);
		if (param.length == 4) {
			ammeter.setAmmeterDate(param[1]);
			ammeter.setAmmeterTime(param[2]);
			ammeter.setAmmeterAmount(NumberCovert.parseBigDecimal(param[3]));
		} else {
			// ammeter.setAmmeterDate(param[1]);
			ammeter.setAmmeterTime(param[1]);
			ammeter.setAmmeterAmount(NumberCovert.parseBigDecimal(param[2]));
		}
	}

}
