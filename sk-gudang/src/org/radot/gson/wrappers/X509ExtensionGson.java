package org.radot.gson.wrappers;

import java.lang.reflect.Type;
import java.security.cert.X509Extension;
import java.util.Set;

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
public class X509ExtensionGson implements X509Extension {
	
	@Expose(serialize = true, deserialize = true)
	private final boolean unsupportedCriticalExtension;

	@Expose(serialize = true, deserialize = true)
	private final Set<String> criticalExtensionOIDs;

	@Expose(serialize = true, deserialize = true)
	private final Set<String> nonCriticalExtensionOIDs;
	
	public X509ExtensionGson(final X509Extension object) {
		this.unsupportedCriticalExtension = object.hasUnsupportedCriticalExtension();
		this.criticalExtensionOIDs = object.getCriticalExtensionOIDs();
		this.nonCriticalExtensionOIDs = object.getNonCriticalExtensionOIDs();
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<X509Extension> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		}
		
		@Override()
		public X509Extension deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), X509ExtensionGson.class);
		}

		@Override()
		public JsonElement serialize(final X509Extension object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new X509ExtensionGson(object));
		}

	}


	@Override()
	public boolean hasUnsupportedCriticalExtension() {
		return this.unsupportedCriticalExtension;
	}


	@Override()
	public Set<String> getCriticalExtensionOIDs() {
		return this.criticalExtensionOIDs;
	}


	@Override()
	public Set<String> getNonCriticalExtensionOIDs() {
		return this.nonCriticalExtensionOIDs;
	}


	@Override()
	public byte[] getExtensionValue(final String oid) {
		return (byte[]) null;
	}

}
