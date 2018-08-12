package org.radot.json.servlet;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.enums.Errors;
import org.radot.exceptions.ProcessException;
import org.radot.gson.GsonJson;

public class JsonServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3565468379020566650L;

	private static boolean isInit = false;
	
	private static final Map<String, String> SERVLET_MAP = new HashMap<String, String>(0);
	
	@Override()
	public void init(final ServletConfig config) {
		if (JsonServlet.isInit) {
			return;
		}
		
		try {
			super.init(config);
			
			final String _param = null == config.getInitParameter("json-handlers")? "": config.getInitParameter("json-handlers").replace(',', '\n');
			final Reader _reader = new StringReader(_param);
			final Properties _prop = new Properties();
			_prop.load(_reader);
			
			for (final Object _oKey: _prop.keySet()) {
				final String _key = (String) _oKey;
				final String _value = _prop.getProperty(_key);
				
				JsonServlet.SERVLET_MAP.put(_key, _value);
			}
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}

		JsonServlet.isInit = true;
	}
	
	@Override()
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) {
		try {
			resp.setContentType("text/plain");
			
			final String _pathInfo = req.getPathInfo();
			if (null == _pathInfo) {
				throw new Exception();
			}
			
			final String _path = _pathInfo.trim();
			final String _implName = JsonServlet.SERVLET_MAP.get(_path);
			if (null == _implName) {
				throw new Exception();
			}
			
			final Class<?> _class = Class.forName(_implName);
			final Constructor<?> _constr = _class.getConstructor(new Class[]{ HttpServletRequest.class, HttpServletResponse.class });
			final Object _oInst = _constr.newInstance(req, resp);
			if (!(_oInst instanceof JsonServletHandler)) {
				throw new Exception();
			}
			final JsonServletHandler<?, ?> _handler = (JsonServletHandler<?, ?>) _oInst;
			_handler.process();
			
			resp.getWriter().print(GsonJson.getBuilder().create().toJson(_handler.result));
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
			this.constructError(t, resp);
		}
	}
	
	@Override()
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
		doPost(req, resp);
	}
	
	
	public final void constructError(final Throwable t, final HttpServletResponse resp) {
		final JsonResult _ret = new JsonResult();
		if (t instanceof ProcessException) {
			_ret.setCode(((ProcessException) t).getError().code);
		} else if (t instanceof InvocationTargetException) {
			final Throwable _th = ((InvocationTargetException) t).getTargetException();
			if (_th instanceof ProcessException) {
				_ret.setCode(((ProcessException) _th).getError().code);
			} else {
				_ret.setCode(Errors.UNKNOWN_ERROR.code);
			}
		} else {
			_ret.setCode(Errors.UNKNOWN_ERROR.code);
		}
		
		_ret.setMessage(t.getMessage());
		try {
			resp.getWriter().print(GsonJson.getBuilder().create().toJson(_ret));
		} catch (final Throwable th) {
			// TODO: handle exception
		}
	}
	
}
