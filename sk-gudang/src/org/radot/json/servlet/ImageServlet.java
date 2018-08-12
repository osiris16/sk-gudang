package org.radot.json.servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.radot.utils.ExportToPdf;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = -7671624423911816553L;

	@Override()
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		String _pathInfo = null == req.getPathInfo()? "/": req.getPathInfo();
		try {
			ByteArrayOutputStream _baos = new ByteArrayOutputStream();
			String _url = "C:/work/back up/projects/project-3/fileImgSk/" + _pathInfo;
			if(!ExportToPdf._osName.startsWith("Windows")){
				_url = "/fileImgSk/" + _pathInfo;
			}
			InputStream _instream = new FileInputStream(_url);
			IOUtils.copy(_instream, _baos);
			_instream.close();
			
			resp.getOutputStream().write(_baos.toByteArray());
		} catch (Throwable e) {
			
		}
	}
}
