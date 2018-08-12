package org.radot.gson.wrappers;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

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
@SuppressWarnings("deprecation")
public class HttpSessionGson implements HttpSession {

	@Expose(serialize = true, deserialize = true)
	private final Map<String, Object> attributes = new HashMap<String, Object>(0);
	
	@Expose(serialize = true, deserialize = true)
	private final long creationTime;
	
	@Expose(serialize = true, deserialize = true)
	private final String id;
	
	@Expose(serialize = true, deserialize = true)
	private final long lastAccessedTime;
	
	@Expose(serialize = true, deserialize = true)
	private final int maxInactiveInterval;
	
	@Expose(serialize = true, deserialize = true)
	private final ServletContext servletContext;
	
	private final HttpSessionContext sessionContext;
	
	public HttpSessionGson(final HttpSession session) {
		final Enumeration<?> _enum = session.getAttributeNames();
		while (null != _enum && _enum.hasMoreElements()) {
			final Object _item = _enum.nextElement();
			if (!(_item instanceof String)) {
				continue;
			}
			
			final String _key = (String) _item;
			final Object _value = session.getAttribute(_key);
			if (null == _value) {
				continue;
			}
			
			this.attributes.put(_key, _value);
		}
		
		this.creationTime = session.getCreationTime();
		this.id = session.getId();
		this.lastAccessedTime = session.getLastAccessedTime();
		this.maxInactiveInterval = session.getMaxInactiveInterval();
		this.servletContext = session.getServletContext();
		this.sessionContext = session.getSessionContext();
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<HttpSession> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.registerTypeHierarchyAdapter(ServletContext.class, new ServletContextGson.Adapter())
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		}
		
		@Override()
		public HttpSession deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), HttpSessionGson.class);
		}

		@Override()
		public JsonElement serialize(final HttpSession object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new HttpSessionGson(object));
		}
		
	}

	@Override()
	public Object getAttribute(final String key) {
		return this.attributes.get(key);
	}

	@Override()
	public Enumeration<?> getAttributeNames() {
		return Collections.enumeration(this.attributes.keySet());
	}

	@Override()
	public long getCreationTime() {
		return this.creationTime;
	}

	@Override()
	public String getId() {
		return this.id;
	}

	@Override()
	public long getLastAccessedTime() {
		return this.lastAccessedTime;
	}

	@Override()
	public int getMaxInactiveInterval() {
		return this.maxInactiveInterval;
	}

	@Override()
	public ServletContext getServletContext() {
		return this.servletContext;
	}

	@Override()
	public HttpSessionContext getSessionContext() {
		return this.sessionContext;
	}

	@Override()
	public Object getValue(final String key) {
		return this.attributes.get(key);
	}

	@Override()
	public String[] getValueNames() {
		return this.attributes.keySet().toArray(new String[this.attributes.size()]);
	}

	@Override()
	public void invalidate() { }

	@Override()
	public boolean isNew() {
		return false;
	}

	@Override()
	public void putValue(final String key, final Object value) { }

	@Override()
	public void removeAttribute(final String key) { }

	@Override()
	public void removeValue(final String value) { }

	@Override()
	public void setAttribute(final String key, final Object value) { }

	@Override()
	public void setMaxInactiveInterval(final int millsecs) { }
	
	public static String extractRealSessionId(final HttpSession session) {
		if (null == session) {
			return "";
		}

		String _ret = "";
		try {
			final String _sessId = session.getId();
			final String[] _split = _sessId.split("!");
			_ret = _split.length > 0? _split[0].trim(): _sessId.trim();
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
}
