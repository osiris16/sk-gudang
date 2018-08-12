package org.radot.html.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.consts.SessionKeysConst;
import org.radot.hibernate.entities.UserEntity;
import org.radot.html.servlet.WebServletHandler;
import org.radot.utils.ClearAttributeSession;

public class WebPembelianHandler extends WebServletHandler {

	public WebPembelianHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public String process() {
		
		HttpSession _session = this.request.getSession();
		
		try {
			UserEntity _user =  (UserEntity) _session.getAttribute(SessionKeysConst.CURRENT_USER);
			System.out.println(_user);
			
			if(_user == null){
				return "/WEB-INF/jsp/login.jsp";
			}
			String _type= _user.getType().toString();
			if(!_type.equalsIgnoreCase("ADMIN")){
				return "/WEB-INF/jsp/beranda.jsp";
			}
			
		} 
		catch (Exception e) {
		e.printStackTrace();
		}
		ClearAttributeSession.clear(this.request.getSession());
		return "/WEB-INF/jsp/pembelian.jsp";
	}

}
