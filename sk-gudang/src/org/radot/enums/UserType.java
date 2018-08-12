package org.radot.enums;

import javax.persistence.AttributeConverter;

public enum UserType {

	USER,
	ADMIN,
	RESELLER,
	
	UNKNOWN
	;
	
	public static class Converter implements AttributeConverter<UserType, String> {

		@Override()
		public String convertToDatabaseColumn(final UserType obj) {
			String _ret = (String) null;
			try {
				_ret = obj.name();
			} catch (final Throwable t) {
				t.getMessage();
			}
			return _ret;
		}

		@Override()
		public UserType convertToEntityAttribute(final String string) {
			UserType _ret;
			try {
				_ret = UserType.valueOf(string);
			} catch (final Throwable t) {
				_ret = UserType.UNKNOWN;
			}
			
			return _ret;
		}
		
	}
}
