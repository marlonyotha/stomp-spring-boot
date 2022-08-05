package br.com.yotha.stompwebchat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtils {

	private StringUtils() {
	}

	private static final String TIME_FORMATTER = "HH:mm:ss";

	public static String getCurrentTimeStamp() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMATTER);
		return LocalDateTime.now().format(formatter);
	}
}