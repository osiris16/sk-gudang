package org.radot.gson.wrappers;

import java.lang.reflect.Type;

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
public class ClassGson {

	public static final class Adapter
	implements JsonSerializerDeserializer<Class<?>> {

		@Override()
		public Class<?> deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			Class<?> _ret = (Class<?>) null;
			try {
				_ret = Class.forName(element.getAsString());
			} catch (final Throwable t) {
				t.getMessage();
			}
			
			return _ret;
		}

		@Override()
		public JsonElement serialize(final Class<?> object, final Type type, final JsonSerializationContext context) {
			return new JsonPrimitive(object.getCanonicalName());
		}

	}

}
