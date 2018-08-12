package org.radot.enums;

import javax.persistence.AttributeConverter;

public enum MarkupUnit {

	PERCENT,
	EXACT,
	
	UNKNOWN
	;
	
	public static class Converter implements AttributeConverter<MarkupUnit, String> {

		@Override()
		public String convertToDatabaseColumn(final MarkupUnit obj) {
			String _ret = (String) null;
			try {
				_ret = obj.name();
			} catch (final Throwable t) {
				t.getMessage();
			}
			return _ret;
		}

		@Override()
		public MarkupUnit convertToEntityAttribute(final String string) {
			MarkupUnit _ret;
			try {
				_ret = MarkupUnit.valueOf(string);
			} catch (final Throwable t) {
				_ret = MarkupUnit.UNKNOWN;
			}
			
			return _ret;
		}
		
	}
}
