package org.radot.gson.utils;

import java.util.Locale;

import org.apache.commons.codec.binary.Hex;

/**
 * 
 * @author Iman
 *
 */
public final class ByteTransform {
	private ByteTransform() { }
	
	public static String encode(final byte[] data) {
		String _ret = (String) null;
		try {
			_ret = Hex.encodeHexString(data).toUpperCase(Locale.US);
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
	
	public static byte[] decode(final String data) {
		byte[] _ret = (byte[]) null;
		try {
			_ret = Hex.decodeHex(data.toCharArray());
		} catch (final Throwable t) {
			t.getMessage();
		}
		
		return _ret;
	}
	
}
