package org.radot.json.handlers;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

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

public class JsonLoginHandler extends JsonServletHandler<LoginParam, JsonResult> {

	public JsonLoginHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		final HttpSession _session = this.request.getSession();
		String validate = "";
		if (null == this.param.getKaptcha()) {
			throw new ProcessException(Errors.KAPTCHA_INVALID);
		}

		final Object _oKaptcha = _session.getAttribute(WebKaptchaHandler.KAPTCHA_SESSION_KEY);
		_session.removeAttribute(WebKaptchaHandler.KAPTCHA_SESSION_KEY);
		if (!(_oKaptcha instanceof String)) {
			throw new ProcessException(Errors.KAPTCHA_INVALID);
		}

		final String _kaptcha = (String) _oKaptcha;
		if (!this.param.getKaptcha().equals(_kaptcha)) {
			throw new ProcessException(Errors.KAPTCHA_INVALID);
		}
		
		final UserPersistence _uPer = new UserPersistence();
		String _userStr = this.param.getUsername();
		/* NANTI KALO KENA MEMORY LAGI CHECK LOG MAX MEMORY NYA NANTI DI XMX NYA NAIKIN MAX NYA 
		 * SESUAI AMA USED KALO BISA DI LEBIHIN
		 * KALO HANDLE DI CODE GW BELUM TAU CARA NYA DI JAVA */
		UserEntity _user =null;
		try {
			_user = _uPer.getByName(_userStr);
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			System.out.println("GAGAL AKSES DB");
			validate = "Out of memory";
			try {
				MemoryUsage _usage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
				//System.out.println("MAX MEMORY = " + _usage.getMax());
				//System.out.println("USED MEMORY = " + _usage.getUsed());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			this.result.setCode(2);
			this.result.setMessage("Out of memory");
			throw new ProcessException(Errors.USERNAME_OR_PASSWORD_INVALID);
		}
		if (null == _user) {
			//System.out.println("user tidak ada");
			validate = "user tidak ada";
			this.result.setCode(2);
			this.result.setMessage("user tidak ada");
			throw new ProcessException(Errors.USERNAME_OR_PASSWORD_INVALID);
		}
		
		if (null == this.param.getPassword()) {
			throw new ProcessException(Errors.USERNAME_OR_PASSWORD_INVALID);
		}
		String _pSha = Md5.decript(this.param.getPassword());
		if (!_pSha.equals(_user.getPassword())) {
			//System.out.println("password salah");
			validate = "password salah";
			this.result.setCode(2);
			this.result.setMessage("password salah");
			throw new ProcessException(Errors.USERNAME_OR_PASSWORD_INVALID);
		}
		final UserEntity _u = new UserEntity();
		_u.setName(this.param.getUsername());
		
		_session.setAttribute(SessionKeysConst.CURRENT_USER, _u);
		_session.setAttribute(SessionKeysConst.TYPE_USER, _u.getType());
		//System.out.println("password benar");
		_session.setAttribute(SessionKeysConst.CURRENT_USER, _user);
		if(validate.equalsIgnoreCase("")){
			this.result.setCode(0);
			this.result.setMessage("OK");
			this.result.setUsrType(String.valueOf( _user.getType()));
		}
		
		
	}

}
