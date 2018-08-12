package org.radot.html.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.annot.WebPrivilege;
import org.radot.consts.SessionKeysConst;
import org.radot.enums.Errors;
import org.radot.enums.UserType;
import org.radot.exceptions.ProcessException;
import org.radot.hibernate.entities.UserEntity;

public abstract class WebServletHandler {

	protected final HttpServletRequest request;
	protected final HttpServletResponse response;
	
	protected UserEntity currentUser;
	
	public WebServletHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		this.request = request;
		this.response = response;
		
		final HttpSession _session = this.request.getSession();
		final Object _oCUser = _session.getAttribute(SessionKeysConst.CURRENT_USER);
		if (_oCUser instanceof UserEntity) {
			this.currentUser = (UserEntity) _oCUser;
		}
		
		final WebPrivilege _priv = this.getClass().getAnnotation(WebPrivilege.class);
		if (null != _priv && _priv.userTypes().length > 0) {
			if (null == this.currentUser) {
				throw new ProcessException(Errors.PRIVILEGE_INVALID);
			}
			
			boolean _hasAccess = false;
			final UserType[] _uTypes = _priv.userTypes();
			for (final UserType _uType: _uTypes) {
				if (_uType.equals(this.currentUser.getType())) {
					_hasAccess = true;
					break;
				}
			}
			
			if (!_hasAccess) {
				throw new ProcessException(Errors.PRIVILEGE_INVALID);
			}
		}
	}
	
	public abstract String process();

}
