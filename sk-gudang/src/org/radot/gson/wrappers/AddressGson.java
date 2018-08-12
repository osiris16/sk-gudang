package org.radot.gson.wrappers;

import java.lang.reflect.Type;

import javax.mail.Address;

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
public class AddressGson extends Address {

	/**
	 * 
	 */
	private static final long serialVersionUID = 368458075771261242L;
	
	@Expose(serialize = true, deserialize = true)
	private final String type;

	@Expose(serialize = true, deserialize = true)
	private final String string;
	
	public AddressGson(final Address object) {
		this.type = object.getType();
		this.string = object.toString();
	}

	
	public static final class Adapter implements JsonSerializerDeserializer<Address> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		}
		
		@Override()
		public Address deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), AddressGson.class);
		}

		@Override()
		public JsonElement serialize(final Address object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new AddressGson(object));
		}

	}

	@Override()
	public boolean equals(final Object other) {
		if (null == other) {
			return false;
		}
		
		return other.equals(this);
	}

	@Override()
	public String getType() {
		return this.type;
	}

	@Override()
	public String toString() {
		return this.string;
	}

}
