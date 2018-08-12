package org.radot.enums;

import javax.persistence.AttributeConverter;

public enum ProductUnit {

	DOZEN,
	LSN,
	PR,
	BOX,
	CART,
	PCS,
	UNIT,
	
	UNKNOWN
	;
	
	public static class Converter implements AttributeConverter<ProductUnit, String> {

		@Override()
		public String convertToDatabaseColumn(final ProductUnit obj) {
			String _ret = (String) null;
			try {
				_ret = obj.name();
			} catch (final Throwable t) {
				t.getMessage();
			}
			return _ret;
		}

		@Override()
		public ProductUnit convertToEntityAttribute(final String string) {
			ProductUnit _ret;
			try {
				_ret = ProductUnit.valueOf(string);
			} catch (final Throwable t) {
				_ret = ProductUnit.UNKNOWN;
			}
			
			return _ret;
		}
		
	}
}
