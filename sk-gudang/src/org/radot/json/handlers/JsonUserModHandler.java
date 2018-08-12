package org.radot.json.handlers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.hibernate.entities.UserEntity;
import org.radot.json.beans.UserAddParam;
import org.radot.json.servlet.JsonResult;
import org.radot.json.servlet.JsonServletHandler;

public class JsonUserModHandler extends JsonServletHandler<UserAddParam, JsonResult> {

	public JsonUserModHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public void process() throws Throwable {
		
		final UserEntity ent = new UserEntity();
		ent.setRecId(this.param.getId());
		ent.setName(this.param.getName());
		
		ent.modify();
		
		this.result.setCode(0);
		this.result.setMessage("Sukses");
	}

}
