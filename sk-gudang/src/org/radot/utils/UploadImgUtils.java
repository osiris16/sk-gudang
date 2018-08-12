package org.radot.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;


public class UploadImgUtils {
public static final String UPLOADED_IMAGE = "net.radot.upload.banner-image";
public static String UPLOADED_IMAGE_NAME = "net.radot.upload.banner-image-name";
	
	/**
	 * Allowed dimensions set
	 */
	private static final Set<String> IMAGE_DIM_ALLOWED = new HashSet<String>(0);
	
	/**
	 * Allowed content types set
	 */
	static final Set<String> CONTENT_TYPE_ALLOWED = new HashSet<String>(0);
	
	/**
	 * Initializing class
	 */
	static {
		synchronized (UploadImgUtils.class) {
			/**
			 * Set allowed content types
			 */
			UploadImgUtils.CONTENT_TYPE_ALLOWED.add("image/jpeg");
			UploadImgUtils.CONTENT_TYPE_ALLOWED.add("image/jpg");
			UploadImgUtils.CONTENT_TYPE_ALLOWED.add("image/pjpeg");
			UploadImgUtils.CONTENT_TYPE_ALLOWED.add("image/pjpg");
			UploadImgUtils.CONTENT_TYPE_ALLOWED.add("image/png");
			UploadImgUtils.CONTENT_TYPE_ALLOWED.add("image/bmp");
			
			/**
			 * Set allowed dimensions
			 */
			UploadImgUtils.IMAGE_DIM_ALLOWED.add("640x480");
			UploadImgUtils.IMAGE_DIM_ALLOWED.add("1280x800");
		}
	}
public static final class UploadResult {
	
		
		private String contentType;
		private String fieldName;
		private byte[] imageData;
		
		private UploadResult() { }
		
		public String getContentType() {
			return contentType;
		}
		
		private void setContentType(final String contentType) {
			this.contentType = contentType;
		}
		
		public String getFieldName() {
			return fieldName;
		}

		private void setFieldName(final String fieldName) {
			this.fieldName = fieldName;
		}

		public byte[] getImageData() {
			return imageData;
		}

		private void setImageData(final byte[] imageData) {
			this.imageData = imageData;
		}

		public String getData() {
//			 final String _prefix = "data:image/jpeg;base64,";
			final String _prefix = "data:" + this.getContentType() + ";base64,";
			if (null == this.imageData) {
				return _prefix + "";
			}
			
			return _prefix + new String(new Base64().encode(this.imageData));
		}

		public String getSize() {
//			String _ret = (String) null;
//			if (null != this.imageData) {
//				final NumberFormat _format = new DecimalFormat("#,###,###,###,###,###,###,###.#0");
//				final long _size = this.imageData.length;
//				if (_size >= Math.pow(2, 70)) {
//					_ret = _format.format(_size / (Math.pow(2, 70))) + "?B";
//				} else if (_size >= Math.pow(2, 60)) {
//					_ret = _format.format(_size / (Math.pow(2, 60))) + "?B";
//				} else if (_size >= Math.pow(2, 50)) {
//					_ret = _format.format(_size / (Math.pow(2, 50))) + "?B";
//				} else if (_size >= Math.pow(2, 40)) {
//					_ret = _format.format(_size / (Math.pow(2, 40))) + "TB";
//				} else if (_size >= Math.pow(2, 30)) {
//					_ret = _format.format(_size / (Math.pow(2, 30))) + "GB";
//				} else if (_size >= Math.pow(2, 20)) {
//					_ret = _format.format(_size / (Math.pow(2, 20))) + "MB";
//				} else if (_size >= Math.pow(2, 10)) {
//					_ret = _format.format(_size / (Math.pow(2, 10))) + "KB";
//				} else {
//					_ret = _format.format(_size) + " Byte(s)";
//				}
//			}
			
			return "" + this.getImageData().length;
		}
	}
	public static List<UploadResult> parseRequest(final HttpServletRequest req) {
//		final List<UploadResult> _ret = new ArrayList<UploadImgUtils.UploadResult>(0);

//		final List<UploadResult> _ret = new ArrayList<UploadImgUtils.UploadResult>(0);
		final List<UploadResult> _ret = new ArrayList<UploadImgUtils.UploadResult>(0);
		try {
			final FileItemFactory _factory = (FileItemFactory) new DiskFileItemFactory();
			final ServletFileUpload _upload = new ServletFileUpload((org.apache.commons.fileupload.FileItemFactory) _factory);
			final List<?> _items = _upload.parseRequest(req);
			
			/**
			 * Check whether not empty
			 */
			if (null == _items || _items.isEmpty()) {
				// throw new DataFormatException("Invalid form data");
				return _ret;
			}

			int _index = 0;
			for (final Object _objItem: _items) {
				
				/**
				 * Check whether disk file item
				 */
				if (!(_objItem instanceof DiskFileItem)) {
					// throw new InvalidObjectException("Invalid form item");
					continue;
				}

				/**
				 * Check whether is not form field
				 */
				final DiskFileItem _item = (DiskFileItem) _objItem;
				if (_item.isFormField()) {
					// throw new InvalidObjectException("Invalid form field");
					continue;
				}

				/**
				 * Check content type
				 */
				final String _contentType = _item.getContentType();
				if (!UploadImgUtils.CONTENT_TYPE_ALLOWED.contains(_contentType)) {
					// throw new ImageFormatException("File format is not JPG or JPEG");
					continue;
				}

				/**
				 * Convert into array of bytes
				 */
				final byte[] _imageData = IOUtils.toByteArray(_item.getInputStream());
				
				/**
				 * Check image dimension W x H
				 */
				final BufferedImage _image = ImageIO.read(new ByteArrayInputStream(_imageData));
				final int _height = _image.getHeight();
				final int _width = _image.getWidth();
				final String _dimension = _width + "x" + _height;
				System.out.println("dimension of " + _item.getName() + " = (" + _dimension + ")");
				if (!UploadImgUtils.IMAGE_DIM_ALLOWED.contains(_dimension)) {
//					continue;
				}
				
				// final String _filename = _item.getName();
				// final String _targetFilename = "/D:/Works/workspaces/" + _filename;
				// final OutputStream _os = new FileOutputStream(_targetFilename);
				// IOUtils.copy(new ByteArrayInputStream(_imageData), _os);

				/**
				 * Set new item
				 */
				final UploadResult _uploadItem = new UploadResult();
				_uploadItem.setImageData(_imageData);
				_uploadItem.setContentType(_contentType);
				_uploadItem.setFieldName(_item.getName());
				
				/**
				 * Add to return list
				 */
				_ret.add(_uploadItem);
				
				req.setAttribute("imageData" + _index, _uploadItem.getData());
				/*tambahan attribut dari taufik >>*/
				req.setAttribute("sizeImg", _imageData.length);
				req.setAttribute("nameImg", _item.getName());
				req.setAttribute("dimensionImg", _dimension);
				/*<<tambahan attribut dari taufik*/
				/*System.out.println("imageData" + _index);*/
				_index++;
			}
			req.getSession().setAttribute(UploadImgUtils.UPLOADED_IMAGE, _ret);
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}

	
		return _ret;
		
	}
	public static List<UploadResult> uploadFile(final HttpServletRequest req) {
		final ArrayList<UploadResult> _ret = new ArrayList<UploadImgUtils.UploadResult>(0);
		 String _namingFolderAndFile= "";
		try {
//			final FileItemFactory _factory = (FileItemFactory) new DiskFileItemFactory();
			final org.apache.commons.fileupload.FileItemFactory _factory = new DiskFileItemFactory();
			final ServletFileUpload _upload = new ServletFileUpload((org.apache.commons.fileupload.FileItemFactory) _factory);
			final List<?> _items = _upload.parseRequest(req);
			String _path = req.getParameter("dimension");;
			_path = "/"+_path;
			System.out.println("---- "+_path);
			/**
			 * Check whether not empty
			 */
			if (null == _items || _items.isEmpty()) {
				// throw new DataFormatException("Invalid form data");
				return _ret;
			}

			int _index = 0;
			for (final Object _objItem: _items) {
				
				/**
				 * Check whether disk file item
				 */
				if (!(_objItem instanceof DiskFileItem)) {
					// throw new InvalidObjectException("Invalid form item");
					continue;
				}

				/**
				 * Check whether is not form field
				 */
				final DiskFileItem _item = (DiskFileItem) _objItem;
				if (_item.isFormField()) {
					// throw new InvalidObjectException("Invalid form field");
					continue;
				}

				/**
				 * Check content type
				 */
				final String _contentType = _item.getContentType();
				if (!UploadImgUtils.CONTENT_TYPE_ALLOWED.contains(_contentType)) {
					// throw new ImageFormatException("File format is not JPG or JPEG");
					continue;
				}

				/**
				 * Convert into array of bytes
				 */
				_item.getName();
				
				System.out.println("imageNama " + _item.getName());
				System.out.println("imageNama content" + _item.getContentType());
				
//				Session _session = new SessionService().getByOwner(_sessExtractor.getUser());
//				 _sessExtractor.getUser().getRecId(); 
				String _a=	System.getProperty("os.name");
				File _folder = null;
				if(_a.startsWith("Windows")){
					 _folder = new File("D:/fileImgSk");
				}else{
					 _folder = new File("/fileImgSk");
				}
			
				 File[] _listFile = _folder.listFiles();
				 int _jmlFile = _listFile.length;
				 Long _nameFileWith = new Date().getTime();
				
				 System.out.println(_nameFileWith);
				 System.out.println(_jmlFile);
				 _jmlFile= _jmlFile+1;
				 if(_a.startsWith("Windows")){
					 _namingFolderAndFile =  "D:/fileImgSk/"+_jmlFile+"_"+_nameFileWith+_item.getContentType().replace("image/", ".");
				 }else{
					 _namingFolderAndFile =  "/fileImgSk/"+_jmlFile+"_"+_nameFileWith+_item.getContentType().replace("image/", ".");
				 }
				final String _nameFileToDb = _jmlFile+"_"+_nameFileWith+_item.getContentType().replace("image/", ".");
				System.out.println(_nameFileToDb);
				 System.out.println(_namingFolderAndFile);
				
				 File _dirUser = null;
			
				 if(_a.startsWith("Windows")){
					 _dirUser = new File("D:/fileImgSk/");
				 }else{
					 _dirUser = new File("/fileImgSk/");
				 }
				
				if (!_dirUser.exists()) {
					_dirUser.mkdirs();
				}
			
				
				
				final byte[] _imageData = IOUtils.toByteArray(_item.getInputStream());
				ByteArrayInputStream _bais = new ByteArrayInputStream(_imageData);
				java.io.OutputStream _out = new FileOutputStream(_namingFolderAndFile);
				IOUtils.copy(_bais, _out);
				_out.close();
//				req.setAttribute("nameImg", _session.getSessionId()+"."+_item.getContentType().replaceAll("\\/", "."));
//				if(_path.equalsIgnoreCase("/landscape")){
//					req.setAttribute("filePath", _sessExtractor.getUser().getRecId().toString()+"/templandscape/");
//				}else{
//					req.setAttribute("filePath", _sessExtractor.getUser().getRecId().toString()+"/temp"+_path);
//				}
				req.setAttribute("sizeImg", _item.getSize());
				HttpSession _session = req.getSession();
				req.getSession().setAttribute(UploadImgUtils.UPLOADED_IMAGE, _ret);
				_session.setAttribute(UploadImgUtils.UPLOADED_IMAGE_NAME, _nameFileToDb);
		
				
				
			}
			
		} catch (final Throwable t) {
			t.printStackTrace(System.out);
		}

	
		return _ret;
	}
}
