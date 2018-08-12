package org.radot.gson.wrappers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.Principal;
import java.security.cert.X509Extension;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class HttpServletRequestGson implements HttpServletRequest {

	@Expose(serialize = true, deserialize = true)
	private String authType;

	@Expose(serialize = true, deserialize = true)
	private final Map<String, Object> attributes = new HashMap<String, Object>(0);
	
	@Expose(serialize = true, deserialize = true)
	private String characterEncoding;
	
	@Expose(serialize = true, deserialize = true)
	private int contentLength;
	
	@Expose(serialize = true, deserialize = true)
	private String contentType;
	
	@Expose(serialize = true, deserialize = true)
	private String contextPath;
	
	@Expose(serialize = true, deserialize = true)
	private Cookie[] cookies;
	
	@Expose(serialize = true, deserialize = true)
	private final Map<String, String> headers = new HashMap<String, String>(0);

	@Expose(serialize = true, deserialize = true)
	private String localAddr;
	
	@Expose(serialize = true, deserialize = true)
	private Locale locale;
	
	@Expose(serialize = true, deserialize = true)
	private final Enumeration<?> locales;
	
	@Expose(serialize = true, deserialize = true)
	private String localName;
	
	@Expose(serialize = true, deserialize = true)
	private int localPort;
	
	@Expose(serialize = true, deserialize = true)
	private String method;
	
	@Expose(serialize = true, deserialize = true)
	private final Map<String, String[]> parameters = new HashMap<String, String[]>(0);

	@Expose(serialize = true, deserialize = true)
	private String pathInfo;
	
	@Expose(serialize = true, deserialize = true)
	private String pathTranslated;
	
	@Expose(serialize = true, deserialize = true)
	private String protocol;
	
	@Expose(serialize = true, deserialize = true)
	private String queryString;

	@Expose(serialize = true, deserialize = true)
	private final HttpSession session;

	private final Principal userPrincipal;
	
	private void setupAttributes(final HttpServletRequest req) {
		final Enumeration<?> _enum = req.getAttributeNames();
		while (null != _enum && _enum.hasMoreElements()) {
			final Object _item = _enum.nextElement();
			if (!(_item instanceof String)) {
				continue;
			}
			
			final String _key = (String) _item;
			final Object _val = req.getAttribute(_key);
			if (null == _val) {
				continue;
			}

			this.attributes.put(_key, _val);
		}
	}
	
	private void setupHeaders(final HttpServletRequest req) {
		final Enumeration<?> _enum = req.getHeaderNames();
		while (null != _enum && _enum.hasMoreElements()) {
			final Object _item = _enum.nextElement();
			if (!(_item instanceof String)) {
				continue;
			}
			
			final String _key = (String) _item;
			final String _val = req.getHeader(_key);
			if (null == _val) {
				continue;
			}

			this.headers.put(_key, _val);		}
	}
	
	private void setupParameters(final HttpServletRequest req) {
		final Enumeration<?> _enum = req.getParameterNames();
		while (null != _enum && _enum.hasMoreElements()) {
			final Object _item = _enum.nextElement();
			if (!(_item instanceof String)) {
				continue;
			}
			
			final String _key = (String) _item;
			String[] _params = req.getParameterValues(_key);
			if (null == _params) {
				_params = new String[]{ req.getParameter(_key) };
			}
			this.parameters.put(_key, _params);
		}
	}
	
	/**
	 * 
	 * @param req
	 */
	@SuppressWarnings("deprecation")
	public HttpServletRequestGson(final HttpServletRequest req) {
		this.setupAttributes(req);
		this.setupHeaders(req);
		this.setupParameters(req);
		
		this.authType = req.getAuthType();
		
		this.characterEncoding = req.getCharacterEncoding();
		this.contentLength = req.getContentLength();
		this.contextPath = req.getContextPath();
		this.cookies = req.getCookies();
		this.contentType = req.getContentType();
		
		this.localAddr = req.getLocalAddr();
		this.locale = req.getLocale();
		this.locales = req.getLocales();
		this.localName = req.getLocalName();
		this.localPort = req.getLocalPort();
		
		this.method = req.getMethod();
		
		this.pathInfo = req.getPathInfo();
		this.pathTranslated = req.getPathTranslated();
		this.protocol = req.getProtocol();
		
		this.queryString = req.getQueryString();
		
		this.remoteAddr = req.getRemoteAddr();
		this.remoteHost = req.getRemoteHost();
		this.remotePort = req.getRemotePort();
		this.remoteUser = req.getRemoteUser();
		this.requestedSessionId = req.getRequestedSessionId();
		this.requestedSessionIdFromCookie = req.isRequestedSessionIdFromCookie();
		this.requestedSessionIdFromUrl = req.isRequestedSessionIdFromUrl();
		this.requestedSessionIdFromURL = req.isRequestedSessionIdFromURL();
		this.requestedSessionIdValid = req.isRequestedSessionIdValid();
		this.requestURI = req.getRequestURI();
		this.requestURL = req.getRequestURL();

		this.scheme = req.getScheme();
		this.secure = req.isSecure();
		this.serverName = req.getServerName();
		this.serverPort = req.getServerPort();
		this.servletPath = req.getServletPath();
		this.session = req.getSession();
		
		this.userPrincipal = req.getUserPrincipal();
	}
	
	@Override()
	public Object getAttribute(final String key) {
		return this.attributes.get(key);
	}

	@Override()
	public Enumeration<?> getAttributeNames() {
		return Collections.enumeration(this.attributes.keySet());
	}

	@Override()
	public String getCharacterEncoding() {
		return this.characterEncoding;
	}

	@Override()
	public int getContentLength() {
		return this.contentLength;
	}

	@Override()
	public String getContentType() {
		return this.contentType;
	}

	@Override()
	public ServletInputStream getInputStream() throws IOException {
		return (ServletInputStream) null;
	}

	@Override()
	public String getLocalAddr() {
		return this.localAddr;
	}

	@Override()
	public String getLocalName() {
		return this.localName;
	}

	@Override()
	public int getLocalPort() {
		return this.localPort;
	}

	@Override()
	public Locale getLocale() {
		return this.locale;
	}

	@Override()
	public Enumeration<?> getLocales() {
		return this.locales;
	}

	@Override()
	public String getParameter(final String key) {
		StringBuilder _ret = (StringBuilder) null;
		final String[] _params = this.parameters.get(key);
		if (null != _params) {
			_ret = new StringBuilder();
			for (int i = 0; i < _params.length; i++) {
				if (i > 0) {
					_ret.append(", ");
				}
				_ret.append(_params[i]);
			}
		}
		
		return null == _ret? (String) null: _ret.toString();
	}

	@Override()
	public Map<?, ?> getParameterMap() {
		return Collections.unmodifiableMap(this.parameters);
	}

	@Override()
	public Enumeration<?> getParameterNames() {
		return Collections.enumeration(this.parameters.keySet());
	}

	@Override()
	public String[] getParameterValues(final String key) {
		return this.parameters.get(key);
	}

	@Override()
	public String getProtocol() {
		return this.protocol;
	}

	@Override()
	public BufferedReader getReader() throws IOException {
		return (BufferedReader) null;
	}

	@Override()
	public String getRealPath(final String path) {
		return (String) null;
	}

	@Expose(serialize = true, deserialize = true)
	private String remoteAddr;

	@Override()
	public String getRemoteAddr() {
		return this.remoteAddr;
	}

	@Expose(serialize = true, deserialize = true)
	private String remoteHost;

	@Override()
	public String getRemoteHost() {
		return this.remoteHost;
	}

	@Expose(serialize = true, deserialize = true)
	private int remotePort;

	@Override()
	public int getRemotePort() {
		return this.remotePort;
	}

	@Override()
	public RequestDispatcher getRequestDispatcher(final String arg0) {
		return (RequestDispatcher) null;
	}

	@Expose(serialize = true, deserialize = true)
	private String scheme;
	
	@Override()
	public String getScheme() {
		return this.scheme;
	}

	@Expose(serialize = true, deserialize = true)
	private String serverName;

	@Override()
	public String getServerName() {
		return this.serverName;
	}

	@Expose(serialize = true, deserialize = true)
	private int serverPort;
	
	@Override()
	public int getServerPort() {
		return this.serverPort;
	}

	@Expose(serialize = true, deserialize = true)
	private boolean secure;
	
	@Override()
	public boolean isSecure() {
		return this.secure;
	}

	@Override()
	public void removeAttribute(final String key) { }

	@Override()
	public void setAttribute(final String key, final Object value) { }

	@Override()
	public void setCharacterEncoding(final String charset) throws UnsupportedEncodingException { }

	@Override()
	public String getAuthType() {
		return this.authType;
	}

	@Override()
	public String getContextPath() {
		return this.contextPath;
	}

	@Override()
	public Cookie[] getCookies() {
		return this.cookies;
	}

	@Override
	public long getDateHeader(String arg0) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override()
	public String getHeader(final String key) {
		return this.headers.get(key);
	}

	@Override()
	public Enumeration<?> getHeaderNames() {
		return Collections.enumeration(this.headers.keySet());
	}

	@Override
	public Enumeration<?> getHeaders(String arg0) {
		// TODO Auto-generated method stub
		return (Enumeration<?>) null;
	}

	@Override
	public int getIntHeader(String arg0) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override()
	public String getMethod() {
		return this.method;
	}

	@Override()
	public String getPathInfo() {
		return this.pathInfo;
	}

	@Override()
	public String getPathTranslated() {
		return this.pathTranslated;
	}

	@Override()
	public String getQueryString() {
		return this.queryString;
	}

	@Expose(serialize = true, deserialize = true)
	private String remoteUser;
	
	@Override()
	public String getRemoteUser() {
		return this.remoteUser;
	}

	@Expose(serialize = true, deserialize = true)
	private String requestURI;
	
	@Override()
	public String getRequestURI() {
		return this.requestURI;
	}

	@Expose(serialize = true, deserialize = true)
	private final StringBuffer requestURL;
	
	@Override()
	public StringBuffer getRequestURL() {
		return this.requestURL;
	}

	@Expose(serialize = true, deserialize = true)
	private String requestedSessionId;
	
	@Override()
	public String getRequestedSessionId() {
		return this.requestedSessionId;
	}

	@Expose(serialize = true, deserialize = true)
	private String servletPath;
	
	@Override()
	public String getServletPath() {
		return this.servletPath;
	}

	@Override()
	public HttpSession getSession() {
		return this.session;
	}

	@Override()
	public HttpSession getSession(final boolean create) {
		return this.getSession();
	}

	@Override()
	public Principal getUserPrincipal() {
		return this.userPrincipal;
	}

	@Expose(serialize = true, deserialize = true)
	private boolean requestedSessionIdFromCookie;
	
	@Override()
	public boolean isRequestedSessionIdFromCookie() {
		return this.requestedSessionIdFromCookie;
	}

	@Expose(serialize = true, deserialize = true)
	private boolean requestedSessionIdFromURL;
	
	@Override()
	public boolean isRequestedSessionIdFromURL() {
		return this.requestedSessionIdFromURL;
	}

	@Expose(serialize = true, deserialize = true)
	private boolean requestedSessionIdFromUrl;
	
	@Override()
	public boolean isRequestedSessionIdFromUrl() {
		return this.requestedSessionIdFromUrl;
	}

	@Expose(serialize = true, deserialize = true)
	private boolean requestedSessionIdValid;
	
	@Override()
	public boolean isRequestedSessionIdValid() {
		return this.requestedSessionIdValid;
	}

	@Override()
	public boolean isUserInRole(final String arg0) {
		return false;
	}

	
	public static final class Adapter
	implements JsonSerializerDeserializer<HttpServletRequest> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.registerTypeHierarchyAdapter(HttpSession.class, new HttpSessionGson.Adapter())
				.registerTypeHierarchyAdapter(Cookie.class, new CookieGson.Adapter())
				.registerTypeHierarchyAdapter(X509Extension.class, new X509ExtensionGson.Adapter())
				.excludeFieldsWithoutExposeAnnotation()
				.create();
		}
		
		@Override()
		public HttpServletRequest deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), HttpServletRequestGson.class);
		}

		@Override()
		public JsonElement serialize(final HttpServletRequest object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new HttpServletRequestGson(object));
		}
		
	}
}
