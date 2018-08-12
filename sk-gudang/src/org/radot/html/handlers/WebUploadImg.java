package org.radot.html.handlers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.radot.html.servlet.WebServletHandler;
import org.radot.utils.UploadImgUtils;
import org.radot.utils.UploadImgUtils.UploadResult;

public class WebUploadImg extends WebServletHandler {
	

	public WebUploadImg(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public String process() {
		
		List<UploadResult> _resultImg = UploadImgUtils.uploadFile(request);
		return "/WEB-INF/jsp/upload.jsp";
	}


}
