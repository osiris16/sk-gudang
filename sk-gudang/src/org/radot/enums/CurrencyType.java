package org.radot.enums;

import javax.persistence.AttributeConverter;

public enum CurrencyType {

	IDR,
	USD,
	SGD,
	AUD,
	HKD,
	UNKNOWN
	;
	
	public static class Converter implements AttributeConverter<CurrencyType, String> {

		@Override()
		public String convertToDatabaseColumn(final CurrencyType obj) {
			String _ret = (String) null;
			try {
				_ret = obj.name();
			} catch (final Throwable t) {
				t.getMessage();
			}
			return _ret;
		}

		@Override()
		public CurrencyType convertToEntityAttribute(final String string) {
			CurrencyType _ret;
			try {
				_ret = CurrencyType.valueOf(string);
			} catch (final Throwable t) {
				_ret = CurrencyType.UNKNOWN;
			}
			
			return _ret;
		}
		
	}
}
