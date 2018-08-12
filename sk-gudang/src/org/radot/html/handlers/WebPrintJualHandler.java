package org.radot.html.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.html.servlet.WebServletHandler;

public class WebPrintJualHandler extends WebServletHandler {

	public WebPrintJualHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public String process() {
		return "/WEB-INF/jsp/printjual.jsp";
	}

}
