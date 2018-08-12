package org.radot.gson.wrappers;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

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
public class ServletConfigGson implements ServletConfig {

	@Expose(serialize = true, deserialize = true)
	private final Map<String, String> initParams = new TreeMap<String, String>();

	@Expose(serialize = true, deserialize = true)
	private final ServletContext servletContext;
	
	@Expose(serialize = true, deserialize = true)
	private final String servletName;
	
	public ServletConfigGson(final ServletConfig object) {
		this.servletContext = object.getServletContext();
		this.servletName = object.getServletName();
		
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
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<ServletConfig> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeHierarchyAdapter(ServletContext.class, new ServletContextGson.Adapter())
				.create();
		}
		
		@Override()
		public ServletConfig deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), ServletConfigGson.class);
		}

		@Override()
		public JsonElement serialize(final ServletConfig object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new ServletConfigGson(object));
		}

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
	public ServletContext getServletContext() {
		return this.servletContext;
	}

	@Override()
	public String getServletName() {
		return this.servletName;
	}

}
