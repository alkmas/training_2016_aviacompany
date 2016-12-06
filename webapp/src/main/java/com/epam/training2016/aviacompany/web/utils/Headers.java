package com.epam.training2016.aviacompany.web.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Получаем headers
 *
 */
public class Headers {
	// get request headers
	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {

		Map<String, String> map = new HashMap<String, String>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}
}
