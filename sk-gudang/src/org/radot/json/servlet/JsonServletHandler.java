package org.radot.json.servlet;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.radot.annot.WebPrivilege;
import org.radot.base.BaseErasure;
import org.radot.consts.SessionKeysConst;
import org.radot.enums.Errors;
import org.radot.enums.UserType;
import org.radot.exceptions.ProcessException;
import org.radot.gson.GsonJson;
import org.radot.hibernate.entities.UserEntity;

import com.google.gson.Gson;

public abstract class JsonServletHandler<P extends JsonParam, R extends JsonResult> extends BaseErasure {

	protected final HttpServletRequest request;
	protected final HttpServletResponse response;
	
	protected final P param;
	protected final R result;
	
	private UserEntity currentUser;
	
	protected final Gson gson = GsonJson.getBuilder().create();

	private final String content;

	public JsonServletHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		if (null == this.getParamType()) {
			throw new ProcessException(Errors.IMPLEMENTATION_TYPE_INVALID);
		}
		
		if (null == this.getResultType()) {
			throw new ProcessException(Errors.IMPLEMENTATION_TYPE_INVALID);
		}

		if (null == request || null == response) {
			throw new ProcessException(Errors.IMPLEMENTATION_INVALID);
		}
		
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

		this.content = this.extractContent();
		
		this.param = this.wrapParam();
		this.result = this.wrapResult();
		
	}
	
	@SuppressWarnings("unchecked")
	private final P wrapParam() {
		P _ret = (P) null;
		if (null == this.content) {
			_ret = (P) this.gson.fromJson("{}", this.getParamType());
		} else {
			_ret = (P) this.gson.fromJson(this.content, this.getParamType());
		}
		
		return _ret;
	}

	@SuppressWarnings("unchecked")
	private final R wrapResult() {
		return (R) this.gson.fromJson("{}", this.getResultType());
	}

	private final Class<?> getParamType() {
		return this.getErasureType(0); 
	}

	private final Class<?> getResultType() {
		return this.getErasureType(1); 
	}
	
	private String extractContent() {
		String _ret = (String) null;
		try {
			final ByteArrayOutputStream _baos = new ByteArrayOutputStream();
			IOUtils.copy(this.request.getInputStream(), _baos);
			_ret = _baos.toString();
		} catch (final Throwable t) {
			t.getMessage();
		}
		return _ret;
	}
	
	public UserEntity getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(final UserEntity currentUser) {
		this.currentUser = currentUser;
	}

	public abstract void process() throws Throwable ;

}
