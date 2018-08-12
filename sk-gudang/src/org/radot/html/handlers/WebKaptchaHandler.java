package org.radot.html.handlers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.radot.html.servlet.WebServletHandler;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

public class WebKaptchaHandler extends WebServletHandler {

	public static final String KAPTCHA_SESSION_KEY = "org.radot.kaptcha.session.key";
	
	private static final Config CONFIG;
	static {
		synchronized (WebKaptchaHandler.class) {
			final Properties _props = new Properties();
			try {
				_props.load(WebKaptchaHandler.class.getResourceAsStream("kaptcha.properties"));
			} catch (final Throwable t) {
			}
			
			CONFIG = new Config(_props);
			WebKaptchaHandler.CONFIG.putProperty(Constants.KAPTCHA_SESSION_CONFIG_KEY, WebKaptchaHandler.KAPTCHA_SESSION_KEY);
		}
	}
	
	public WebKaptchaHandler(final HttpServletRequest request, final HttpServletResponse response) throws Throwable {
		super(request, response);
	}

	@Override()
	public String process() {
		this.response.setContentType("image/jpeg");
		final HttpSession _session = this.request.getSession();
		final Producer _producer = WebKaptchaHandler.CONFIG.getProducerImpl();
		final String _text = _producer.createText();
		_session.setAttribute(WebKaptchaHandler.KAPTCHA_SESSION_KEY, _text);
		
		final BufferedImage _image = _producer.createImage(_text);
		final ByteArrayOutputStream _baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(_image, "jpg", _baos);
			final byte[] _imageData = _baos.toByteArray();
			this.response.getOutputStream().write(_imageData);
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}
		
		return null;
	}

}
