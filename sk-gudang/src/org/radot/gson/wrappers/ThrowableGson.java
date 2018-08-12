package org.radot.gson.wrappers;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
public class ThrowableGson extends Throwable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6914871014465172114L;

	@Expose(serialize = true, deserialize = true)
	private String message;
	
	@Expose(serialize = true, deserialize = true)
	private String[] stackTraces;

	@Expose(serialize = true, deserialize = true)
	private Throwable cause;
	
	public ThrowableGson(final Throwable object) {
		if (null != object.getCause()) {
			this.cause = new ThrowableGson(object.getCause());
		}
		
		final StringBuilder _msg = new StringBuilder();
		_msg.append(object.getClass().getCanonicalName());
		if (null != object.getMessage()) {
			_msg.append(" : ").append(object.getMessage());
		}
		this.message = _msg.toString();
		
		final StackTraceElement[] _stes = object.getStackTrace();
		List<String> _steList = (List<String>) null;
		if (null != _stes) {
			if (_stes.length > 0) {
				_steList = new ArrayList<String>(0);
				for (final StackTraceElement _ste: _stes) {
					if (null == _ste) {
						continue;
					}
					
					final StringBuilder _item = new StringBuilder();
					_item
						.append("class=").append(_ste.getClassName())
						.append(", file=").append(_ste.getFileName())
						.append(", line=").append(_ste.getLineNumber() < 0? "<unknown>": String.valueOf(_ste.getLineNumber()))
						.append(", method=").append(_ste.getMethodName());
					_steList.add(_item.toString());
				}
			}
		}
		
		if (null != _steList) {
			this.stackTraces = _steList.toArray(new String[_steList.size()]);
		}
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<Throwable> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		}
		
		@Override()
		public Throwable deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), ThrowableGson.class);
		}

		@Override()
		public JsonElement serialize(final Throwable object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new ThrowableGson(object));
		}

	}

	@Override()
	public final StackTraceElement[] getStackTrace() {
		StackTraceElement[] _ret = (StackTraceElement[]) null;
		
		List<StackTraceElement> _steList = (List<StackTraceElement>) null;
		if (null != this.stackTraces) {
			_steList = new ArrayList<StackTraceElement>(0);
			final Properties _prop = new Properties();
			for (final String _st: this.stackTraces) {
				final String _str = _st.replace(", ", "\n");
				try {
					_prop.load(new StringReader(_str));
				} catch (final Throwable t) {
					t.getMessage();
				}

				final StackTraceElement _ste = new StackTraceElement(
						_prop.getProperty("class"),
						_prop.getProperty("method"),
						_prop.getProperty("file"),
						"<unknown>".equals(_prop.getProperty("line"))? -1: Integer.valueOf(_prop.getProperty("line")));

				_steList.add(_ste);
			}
		}
		
		if (null != _steList) {
			_ret = _steList.toArray(new StackTraceElement[_steList.size()]);
		}
		
		return _ret;
	}

	@Override
	public void printStackTrace() {
		super.setStackTrace(this.getStackTrace());
		super.printStackTrace(System.out);
	}
	
	@Override
	public void printStackTrace(PrintStream s) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void printStackTrace(PrintWriter s) {
		// TODO Auto-generated method stub
	}
	
	@Override()
	public String getMessage() {
		return this.message;
	}
	
	@Override()
	public Throwable getCause() {
		return this.cause;
	}

	@Override()
	public void setStackTrace(final StackTraceElement[] stackTrace) { }
	
	@Override()
	public synchronized Throwable initCause(final Throwable cause) {
		return this;
	}
	
}
