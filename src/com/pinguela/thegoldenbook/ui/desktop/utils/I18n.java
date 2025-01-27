package com.pinguela.thegoldenbook.ui.desktop.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
	
	private static ResourceBundle rb = ResourceBundle.getBundle("i18n/Messages", new Locale("en"));
	
	public static final String getString(String key) {
		return rb.getString(key);
	}
}
