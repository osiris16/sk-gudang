package org.radot.json.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.enums.Errors;
import org.radot.exceptions.ProcessException;
import org.radot.hibernate.entities.UserEntity;
import org.radot.hibernate.persistences.UserPersistence;
import org.radot.html.handlers.WebKaptchaHandler;
import org.radot.json.beans.LoginParam;
import org.radot.json.servlet.JsonResult;
import org.radot.json.servlet.JsonServletHandler;
import org.radot.utils.Md5;

public class JsonLogoutHandler extends JsonServletHandler<LoginParam, JsonResult> {

	public JsonLogoutHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
	
		final HttpSession _session = this.request.getSession();
		_session.removeAttribute(SessionKeysConst.CURRENT_USER);
		this.result.setCode(0);
		this.result.setMessage("OK");
	}

}
