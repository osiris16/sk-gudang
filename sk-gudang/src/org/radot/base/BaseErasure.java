package org.radot.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * @author Iman
 *
 */
public class BaseErasure {
	
	public final Class<?> getErasureType(final int index) {
		Class<?> _ret = (Class<?>) null;
		try {
			final Type _genSupClass = this.getClass().getGenericSuperclass();
			if (!(_genSupClass instanceof ParameterizedType)) {
				throw new Exception();
			}
			
			final ParameterizedType _pType = (ParameterizedType) _genSupClass;
			final Type[] _types = _pType.getActualTypeArguments();
			if (null == _types || _types.length <= index) {
				throw new Exception();
			}
			
			final Type _type = _types[index];
			if (!(_type instanceof Class)) {
				throw new Exception();
			}

			_ret = (Class<?>) _type; 
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
}
