package com.deeps.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	public static LocalDateTime convertThymeleafStringToLocalDateTime(String str) {
		if (str != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			return LocalDateTime.parse(str, formatter);
		}
		return null;
	}

	public static String convertLocalDateTimeToString(LocalDateTime time) {
		if (time != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			return time.format(formatter);
		}
		return null;
	}
}
