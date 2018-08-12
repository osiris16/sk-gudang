package org.radot.gson.wrappers;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
public class ServletGson implements Servlet {

	@Expose(serialize = true, deserialize = true)
	private final String servletInfo;

	@Expose(serialize = true, deserialize = true)
	private final ServletConfig servletConfig;

	public ServletGson(final Servlet object) {
		this.servletInfo = object.getServletInfo();
		this.servletConfig = object.getServletConfig();
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<Servlet> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeHierarchyAdapter(ServletConfig.class, new ServletConfigGson.Adapter())
				.create();
		}
		
		@Override()
		public Servlet deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), ServletGson.class);
		}

		@Override()
		public JsonElement serialize(final Servlet object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new ServletGson(object));
		}

	}


	@Override()
	public void destroy() { }


	@Override()
	public ServletConfig getServletConfig() {
		return this.servletConfig;
	}


	@Override()
	public String getServletInfo() {
		return this.servletInfo;
	}


	@Override()
	public void init(final ServletConfig arg0) throws ServletException { }


	@Override
	public void service(final ServletRequest arg0, final ServletResponse arg1) throws ServletException, IOException { }

}
