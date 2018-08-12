package org.radot.gson.wrappers;

import java.lang.reflect.Type;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
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
public class ConnectionGson implements Connection {

	@Expose(serialize = true, deserialize = true)
	public boolean autoCommit;

	@Expose(serialize = true, deserialize = true)
	public boolean closed;

	public DatabaseMetaData metaData;

	@Expose(serialize = true, deserialize = true)
	public boolean readOnly;

	@Expose(serialize = true, deserialize = true)
	public String catalog;

	@Expose(serialize = true, deserialize = true)
	public int transactionIsolation;
	
	public Map<String, Class<?>> typeMap;
	
	@Expose(serialize = true, deserialize = true)
	public int holdability;

	public Properties clientInfo;
	
	public ConnectionGson(final Connection object) {
		try {
			this.autoCommit = object.getAutoCommit();
			this.closed = object.isClosed();
			this.metaData = object.getMetaData();
			this.readOnly = object.isReadOnly();
			this.catalog = object.getCatalog();
			this.transactionIsolation = object.getTransactionIsolation();
			this.typeMap = object.getTypeMap();
			this.holdability = object.getHoldability();
			this.clientInfo = object.getClientInfo();		
		} catch (final Throwable t) {
			t.getMessage();
		}
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<Connection> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		}
		
		@Override()
		public Connection deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), ConnectionGson.class);
		}

		@Override()
		public JsonElement serialize(final Connection object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new ConnectionGson(object));
		}
		
	}

	@Override()
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return (T) null;
	}

	@Override()
	public boolean isWrapperFor(final Class<?> iface) throws SQLException {
		return false;
	}

	@Override()
	public Statement createStatement() throws SQLException {
		return (Statement) null;
	}

	@Override()
	public PreparedStatement prepareStatement(final String sql) throws SQLException {
		return (PreparedStatement) null;
	}

	@Override()
	public CallableStatement prepareCall(final String sql) throws SQLException {
		return (CallableStatement) null;
	}

	@Override()
	public String nativeSQL(final String sql) throws SQLException {
		return (String) null;
	}

	@Override()
	public void setAutoCommit(final boolean autoCommit) throws SQLException { }

	@Override()
	public boolean getAutoCommit() throws SQLException {
		return this.autoCommit;
	}

	@Override()
	public void commit() throws SQLException { }

	@Override()
	public void rollback() throws SQLException { }

	@Override()
	public void close() throws SQLException { }

	@Override
	public boolean isClosed() throws SQLException {
		return this.closed;
	}

	@Override()
	public DatabaseMetaData getMetaData() throws SQLException {
		return this.metaData;
	}

	@Override()
	public void setReadOnly(final boolean readOnly) throws SQLException { }

	@Override()
	public boolean isReadOnly() throws SQLException {
		return this.readOnly;
	}

	@Override()
	public void setCatalog(final String catalog) throws SQLException { }

	@Override()
	public String getCatalog() throws SQLException {
		return this.catalog;
	}

	@Override()
	public void setTransactionIsolation(final int level) throws SQLException { }

	@Override()
	public int getTransactionIsolation() throws SQLException {
		return this.transactionIsolation;
	}

	@Override()
	public SQLWarning getWarnings() throws SQLException {
		return (SQLWarning) null;
	}

	@Override()
	public void clearWarnings() throws SQLException { }

	@Override()
	public Statement createStatement(final int resultSetType, final int resultSetConcurrency)
			throws SQLException {
		return (Statement) null;
	}

	@Override()
	public PreparedStatement prepareStatement(final String sql, final int resultSetType,
			final int resultSetConcurrency) throws SQLException {
		return (PreparedStatement) null;
	}

	@Override()
	public CallableStatement prepareCall(final String sql, final int resultSetType,
			final int resultSetConcurrency) throws SQLException {
		return (CallableStatement) null;
	}

	@Override()
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return this.typeMap;
	}

	@Override()
	public void setTypeMap(final Map<String, Class<?>> map) throws SQLException { }

	@Override()
	public void setHoldability(int holdability) throws SQLException { }

	@Override()
	public int getHoldability() throws SQLException {
		return this.holdability;
	}

	@Override()
	public Savepoint setSavepoint() throws SQLException {
		return (Savepoint) null;
	}

	@Override()
	public Savepoint setSavepoint(String name) throws SQLException {
		return (Savepoint) null;
	}

	@Override()
	public void rollback(final Savepoint savepoint) throws SQLException { }

	@Override()
	public void releaseSavepoint(final Savepoint savepoint) throws SQLException { }

	@Override()
	public Statement createStatement(final int resultSetType,
			final int resultSetConcurrency, final int resultSetHoldability)
			throws SQLException {
		return (Statement) null;
	}

	@Override()
	public PreparedStatement prepareStatement(final String sql, final int resultSetType,
			final int resultSetConcurrency, final int resultSetHoldability)
			throws SQLException {
		return (PreparedStatement) null;
	}

	@Override()
	public CallableStatement prepareCall(final String sql, final int resultSetType,
			final int resultSetConcurrency, final int resultSetHoldability) throws SQLException {
		return (CallableStatement) null;
	}

	@Override
	public PreparedStatement prepareStatement(final String sql, final int autoGeneratedKeys) throws SQLException {
		return (PreparedStatement) null;
	}

	@Override()
	public PreparedStatement prepareStatement(final String sql, final int[] columnIndexes) throws SQLException {
		return (PreparedStatement) null;
	}

	@Override()
	public PreparedStatement prepareStatement(final String sql, final String[] columnNames) throws SQLException {
		return (PreparedStatement) null;
	}

	@Override()
	public Clob createClob() throws SQLException {
		return (Clob) null;
	}

	@Override()
	public Blob createBlob() throws SQLException {
		return (Blob) null;
	}

	@Override()
	public NClob createNClob() throws SQLException {
		return (NClob) null;
	}

	@Override()
	public SQLXML createSQLXML() throws SQLException {
		return (SQLXML) null;
	}

	@Override()
	public boolean isValid(final int timeout) throws SQLException {
		return false;
	}

	@Override()
	public void setClientInfo(final String name, final String value) throws SQLClientInfoException { }

	@Override()
	public void setClientInfo(final Properties properties) throws SQLClientInfoException { }

	@Override()
	public String getClientInfo(final String name) throws SQLException {
		return (String) null;
	}

	@Override()
	public Properties getClientInfo() throws SQLException {
		return this.clientInfo;
	}

	@Override()
	public Array createArrayOf(final String typeName, final Object[] elements) throws SQLException {
		return (Array) null;
	}

	@Override()
	public Struct createStruct(final String typeName, final Object[] attributes) throws SQLException {
		return (Struct) null;
	}

}
