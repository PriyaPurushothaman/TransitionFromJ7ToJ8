package com.setmore.minions.utils;

import java.util.Collection;



@SuppressWarnings("unused")
public final class ObjUtil {

	public static String nullToDefault(String obj, String defaultValue) {
		return isNullStr(obj) ? defaultValue : obj;
	}

	public static boolean isNullStr(String obj) {
		return (obj == null || obj.trim().equalsIgnoreCase("null"));
	}

	public static String nullToEmpty(String obj) {
		return obj == null ? "" : obj;
	}

}
