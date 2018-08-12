package org.radot.gson.wrappers;

import java.lang.reflect.Type;
import java.util.Date;

import org.radot.base.JsonSerializerDeserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

/**
 * 
 * @author Iman
 *
 */
public class DateGson {

	public static final class Adapter
	implements JsonSerializerDeserializer<Date> {

		@Override()
		public Date deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return new Date(element.getAsLong());
		}

		@Override()
		public JsonElement serialize(final Date object, final Type type, final JsonSerializationContext context) {
			return new JsonPrimitive(object.getTime());
		}

	}

}
