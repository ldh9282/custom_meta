package com.custom.met.cmmn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


	/***
	 * <pre>
	 * 메서드명: parseDate
	 * 설명: {@code java.util.Date} 반환 
	 * </pre>
	 * @param object {@code String} 혹은 {@code java.util.Date}
	 * @return
	 */
	public static Date parseDate(Object object) {
		
		if (object == null) {
			return null;
		}
		
		if (object instanceof String) {
			String str = (String) object;
			
			if ("".equals(str)) {
				return null;
			}
			
			String format = "yyyy-MM-dd";
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				return sdf.parse(str);
			} catch (ParseException e) {
				throw new IllegalArgumentException("cannot parse string[" + str + "] to date[" + format + "]", e);
			} catch (Exception e) {
				throw new IllegalArgumentException("cannot parse string[" + str + "] to date[" + format + "]", e);
			}
		} else if (object instanceof Date) {
			return (Date) object;
		} else {
			throw new IllegalArgumentException("Unsupported object type");
		}
	}
	/***
	 * <pre>
	 * 메서드명: formatString
	 * 설명: format 형식의 {@code String} 반환
	 * </pre>
	 * @param object {@code String} 혹은 {@code java.util.Date}
	 * @param format
	 * @return 
	 */
	public static String formatString(Object object, String format) {
		
		if (object == null) {
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (object instanceof String) {
			String str = (String) object;
			
			if ("".equals(str)) {
				return "";
			}
			
			try {
				Date date = sdf.parse(str);
				
				return sdf.format(date);
			} catch (ParseException e) {
				throw new IllegalArgumentException("cannot parse string[" + str + "] to date[" + format + "]", e);
			} catch (Exception e) {
				throw new IllegalArgumentException("cannot parse string[" + str + "] to date[" + format + "]", e);
			}
		} else if (object instanceof Date) {
			return sdf.format((Date) object);
		} else {
			throw new IllegalArgumentException("Unsupported object type");
		}
	}
}
