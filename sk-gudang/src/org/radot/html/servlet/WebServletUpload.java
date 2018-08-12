package org.radot.html.servlet;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebServletUpload extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8348179441194206481L;

	private static boolean isInit = false;
	
	private static final Map<String, String> SERVLET_MAP = new HashMap<String, String>(0);
	
	@Override()
	public void init(final ServletConfig config) {
		if (WebServletUpload.isInit) {
			return;
		}
		
		try {
			super.init(config);
			
			final String _param = null == config.getInitParameter("web-handlers")? "": config.getInitParameter("web-handlers").replace(',', '\n');
			final Reader _reader = new StringReader(_param);
			final Properties _prop = new Properties();
			_prop.load(_reader);
			
			for (final Object _oKey: _prop.keySet()) {
				final String _key = (String) _oKey;
				final String _value = _prop.getProperty(_key);
				
				WebServletUpload.SERVLET_MAP.put(_key, _value);
			}
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
		
		WebServletUpload.isInit = true;
	}
	
	@Override()
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
		try {
			
			final String _pathInfo = req.getPathInfo();
			if (null == _pathInfo) {
				throw new Exception();
			}
			
			final String _path = _pathInfo.trim();
			final String _implName = WebServletUpload.SERVLET_MAP.get(_path);
			if (null == _implName) {
				throw new Exception();
			}
			
			final Class<?> _class = Class.forName(_implName);
			final Constructor<?> _constr = _class.getConstructor(new Class[]{ HttpServletRequest.class, HttpServletResponse.class });
			final Object _oInst = _constr.newInstance(req, resp);
			if (!(_oInst instanceof WebServletHandler)) {
				throw new Exception();
			}
			final WebServletHandler _handler = (WebServletHandler) _oInst;
			final String _fwd = _handler.process();
			
			if (null != _fwd) {
				this.forward(req, resp, _fwd);
			}
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
	}
	
	private void forward(final HttpServletRequest req, final HttpServletResponse resp, final String jspViewer) throws Exception {
		final ServletContext _thisPage = this.getServletContext();
		final ServletContext _fwdPage = _thisPage.getContext(req.getContextPath());
		final RequestDispatcher _jsp = _fwdPage.getRequestDispatcher(jspViewer);
		if (null == _jsp) {
			throw new FileNotFoundException("unable to find viewer file '" + jspViewer + "'");
		}
		
		_jsp.forward(req, resp);
	}
}
