package org.radot.gson;

import java.security.cert.X509Extension;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.radot.gson.wrappers.CalendarGson;
import org.radot.gson.wrappers.ClassGson;
import org.radot.gson.wrappers.CookieGson;
import org.radot.gson.wrappers.DateGson;
import org.radot.gson.wrappers.HttpServletRequestGson;
import org.radot.gson.wrappers.HttpSessionGson;
import org.radot.gson.wrappers.ServletContextGson;
import org.radot.gson.wrappers.X509ExtensionGson;


import com.google.gson.GsonBuilder;

/**
 * 
 * @author Iman
 *
 */
public class GsonJson {

	private static final String[] SEEK_FIELDS = { 
		"password",
		"oldPassword",
		"newPassword"
	};

	public static GsonBuilder getBuilder() {
		return new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation()
			
			.registerTypeHierarchyAdapter(HttpServletRequest.class, new HttpServletRequestGson.Adapter())
			.registerTypeHierarchyAdapter(ServletContext.class, new ServletContextGson.Adapter())
			.registerTypeHierarchyAdapter(HttpSession.class, new HttpSessionGson.Adapter())
			.registerTypeHierarchyAdapter(X509Extension.class, new X509ExtensionGson.Adapter())
			.registerTypeHierarchyAdapter(Date.class, new DateGson.Adapter())
			.registerTypeHierarchyAdapter(Class.class, new ClassGson.Adapter())
			.registerTypeHierarchyAdapter(Calendar.class, new CalendarGson.Adapter())
			.registerTypeHierarchyAdapter(Cookie.class, new CookieGson.Adapter())
			
			;
	}
	
	public static final String maskSensitiveJson(final String jsonData) {
		if (null == jsonData) {
			return "null"; // NOPMD by Iman on 8/28/15 3:03 PM
		}
		
		String _ret = jsonData; // NOPMD by Iman on 8/28/15 3:02 PM
		for (final String _masker: GsonJson.SEEK_FIELDS) {
			final String _normalRegex = "\"" + _masker + "\":\"[^\"]*";
			final String _normalReplace = "\"" + _masker + "\":\"****";
			_ret = _ret.replaceAll(_normalRegex, _normalReplace);
			
			final String _escaRegex = "\\\\\"" + _masker + "\\\\\":\\\\\"[^\\\\\"]*";
			final String _escaReplace = "\\\\\"" + _masker + "\\\\\":\\\\\"****";
			_ret = _ret.replaceAll(_escaRegex, _escaReplace);
			
		}
		return _ret;
	}
	
	public static void main(String[] args) {
		System.out.println(getBuilder().create().toJson(String.class));
	}
}
