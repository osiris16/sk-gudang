package org.radot.base;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

/**
 * 
 * @author Iman
 *
 * @param <T>
 */
public interface JsonSerializerDeserializer<T> extends JsonSerializer<T>, JsonDeserializer<T> {

}
