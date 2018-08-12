package org.radot.gson.wrappers;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.radot.base.JsonSerializerDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.annotations.Expose;

/**
 * 
 * @author Iman
 *
 */
public class ServletContextGson implements ServletContext {

	@Expose(serialize = true, deserialize = true)
	private final String contextPath;

	@Expose(serialize = true, deserialize = true)
	private final int majorVersion;

	@Expose(serialize = true, deserialize = true)
	private final int minorVersion;

	@Expose(serialize = true, deserialize = true)
	private final String serverInfo;

	@Expose(serialize = true, deserialize = true)
	private final String servletContextName;

	@Expose(serialize = true, deserialize = true)
	private final Map<String, String> initParams = new TreeMap<String, String>();

	@Expose(serialize = true, deserialize = true)
	private final Map<String, Servlet> servlets = new TreeMap<String, Servlet>();

	@SuppressWarnings("deprecation")
	public ServletContextGson(final ServletContext object) {
		this.contextPath = object.getContextPath();
		this.majorVersion = object.getMajorVersion();
		this.minorVersion = object.getMinorVersion();
		this.serverInfo = object.getServerInfo();
		this.servletContextName = object.getServletContextName();
		
		Enumeration<?> _enum;
		
		_enum = object.getInitParameterNames();
		while (null != _enum && _enum.hasMoreElements()) {
			final Object _item = _enum.nextElement();
			if (!(_item instanceof String)) {
				continue;
			}
			
			final String _key = (String) _item;
			final String _val = object.getInitParameter(_key);
			if (null == _val) {
				continue;
			}
			
			this.initParams.put(_key, _val);
		}
		
		_enum = object.getServletNames();
		while (null != _enum && _enum.hasMoreElements()) {
			final Object _item = _enum.nextElement();
			if (!(_item instanceof String)) {
				continue;
			}
			
			final String _key = (String) _item;
			try {
				this.servlets.put(_key, object.getServlet(_key));
			} catch (final Throwable t) {
				t.getMessage();
			}
			
		}
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<ServletContext> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				//.registerTypeHierarchyAdapter(Servlet.class, new ServletGson.Adapter())
				.create();
		}
		
		@Override()
		public ServletContext deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), ServletContextGson.class);
		}

		@Override()
		public JsonElement serialize(final ServletContext object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new ServletContextGson(object));
		}
		
	}


	@Override()
	public Object getAttribute(final String arg0) {
		return (Object) null;
	}

	@Override()
	public Enumeration<?> getAttributeNames() {
		return (Enumeration<?>) null;
	}


	@Override()
	public ServletContext getContext(final String arg0) {
		return (ServletContext) null;
	}


	@Override()
	public String getContextPath() {
		return this.contextPath;
	}


	@Override()
	public String getInitParameter(final String key) {
		return this.initParams.get(key);
	}


	@Override()
	public Enumeration<?> getInitParameterNames() {
		return Collections.enumeration(this.initParams.keySet());
	}


	@Override()
	public int getMajorVersion() {
		return this.majorVersion;
	}


	@Override
	public String getMimeType(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override()
	public int getMinorVersion() {
		return this.minorVersion;
	}


	@Override()
	public RequestDispatcher getNamedDispatcher(final String arg0) {
		return (RequestDispatcher) null;
	}

	@Override()
	public String getRealPath(final String arg0) {
		return (String) null;
	}

	@Override
	public RequestDispatcher getRequestDispatcher(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public URL getResource(String arg0) throws MalformedURLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public InputStream getResourceAsStream(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Set<?> getResourcePaths(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override()
	public String getServerInfo() {
		return this.serverInfo;
	}


	@Override()
	public Servlet getServlet(final String key) throws ServletException {
		return this.servlets.get(key);
	}


	@Override()
	public String getServletContextName() {
		return this.servletContextName;
	}


	@Override()
	public Enumeration<?> getServletNames() {
		return Collections.enumeration(this.servlets.keySet());
	}


	@Override()
	public Enumeration<?> getServlets() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override()
	public void log(final String arg0) { }

	@Override()
	public void log(final Exception arg0, final String arg1) { }

	@Override
	public void log(final String arg0, final Throwable arg1) { }

	@Override()
	public void removeAttribute(final String arg0) { }

	@Override()
	public void setAttribute(final String arg0, final Object arg1) { }

}
