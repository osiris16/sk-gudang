package org.radot.gson.wrappers;

import java.lang.reflect.Type;

import javax.servlet.http.Cookie;

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
public class CookieGson extends Cookie {
	
	@Expose(serialize = true, deserialize = true)
	private final String comment;
	
	@Expose(serialize = true, deserialize = true)
	private final String domain;

	@Expose(serialize = true, deserialize = true)
	private final int maxAge;

	@Expose(serialize = true, deserialize = true)
	private final String name;

	@Expose(serialize = true, deserialize = true)
	private final String path;

	@Expose(serialize = true, deserialize = true)
	private final boolean secure;

	@Expose(serialize = true, deserialize = true)
	private final String value;

	@Expose(serialize = true, deserialize = true)
	private final int version;
	
	public CookieGson(final Cookie object) {
		super(object.getName(), object.getValue());
		
		this.comment = object.getComment();
		this.domain = object.getDomain();
		this.maxAge = object.getMaxAge();
		this.name = object.getName();
		this.path = object.getPath();
		this.secure = object.getSecure();
		this.value = object.getValue();
		this.version = object.getVersion();
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<Cookie> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		}
		
		@Override()
		public Cookie deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), CookieGson.class);
		}

		@Override()
		public JsonElement serialize(final Cookie object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new CookieGson(object));
		}

	}

	@Override()
	public String getComment() {
		return this.comment;
	}
	
	@Override()
	public String getDomain() {
		return this.domain;
	}
	
	@Override()
	public int getMaxAge() {
		return this.maxAge;
	}
	
	@Override()
	public String getName() {
		return this.name;
	}
	
	@Override()
	public String getPath() {
		return this.path;
	}
	
	@Override()
	public boolean getSecure() {
		return this.secure;
	}
	
	@Override()
	public String getValue() {
		return this.value;
	}
	
	@Override()
	public int getVersion() {
		return this.version;
	}
	
	@Override()
	public void setComment(final String arg0) { }
	
	@Override()
	public void setDomain(final String arg0) { }
	
	@Override()
	public void setMaxAge(final int arg0) { }
	
	@Override()
	public void setPath(final String arg0) { }
	
	@Override()
	public void setSecure(final boolean arg0) { }
	
	@Override()
	public void setValue(final String arg0) { }
	
	@Override()
	public void setVersion(final int arg0) { }
	
}
