package org.radot.hibernate.fox;

public class FoxCfg {

	public static final String FOX_PATH = "D:\\projects\\project-2\\sk-gudang\\dbf";
	
	
	static {
		synchronized (FoxCfg.class) {
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			} catch (final Throwable t) {
				t.printStackTrace();
			}
		}
	}
	
}
