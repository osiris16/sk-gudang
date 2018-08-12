package org.radot.html.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.enums.Errors;
import org.radot.html.servlet.WebServletHandler;

public class WebConstHandler extends WebServletHandler {
		
	public WebConstHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public String process() {
		this.response.setContentType("application/javascript");
		final StringBuilder _builder = new StringBuilder();
		_builder.append("var ERRORS={};\n");
		
		for (final Errors _err: Errors.values()) {
			_builder.append("ERRORS[").append(_err.code).append("]=")
				.append("{")
					.append("'id':'").append(_err.name()).append("'")
					.append(",'description':'").append(_err.description).append("'")
				.append("}")
				.append(";\n");
		}

		
		_builder.append("document['CORP_NAME']='a';\n");
		_builder.append("document['AUTHKEY']='';\n");
		_builder.append("document['CONTEXT_PATH']='").append(this.request.getContextPath()).append("';\n");
		_builder.append("document['ADDR']='").append(this.request.getRemoteAddr()).append("';\n");
		_builder.append("document['PORT']=").append(this.request.getRemotePort()).append(";\n");
		
		if (null != this.currentUser) {
			if (null != this.currentUser.getName()) {
				_builder.append("document['USER_NAME']='").append(this.currentUser.getName()).append("';\n");
			}

			if (null != this.currentUser.getType()) {
				_builder.append("document['USER_TYPE']='").append(this.currentUser.getType()).append("';\n");
			}
		}
		
		try {
			this.response.getOutputStream().write(_builder.toString().getBytes());
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
		
		return null;
	}

}
