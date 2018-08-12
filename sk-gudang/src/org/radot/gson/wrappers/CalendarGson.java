package org.radot.gson.wrappers;

import java.lang.reflect.Type;
import java.util.Calendar;

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
public class CalendarGson {

	public static final class Adapter
	implements JsonSerializerDeserializer<Calendar> {

		@Override()
		public Calendar deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			final Calendar _ret = Calendar.getInstance();
			_ret.setTimeInMillis(element.getAsLong());
			return _ret;
		}

		@Override()
		public JsonElement serialize(final Calendar object, final Type type, final JsonSerializationContext context) {
			return new JsonPrimitive(object.getTimeInMillis());
		}

	}

}
