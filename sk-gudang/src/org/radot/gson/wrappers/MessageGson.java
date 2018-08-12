package org.radot.gson.wrappers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import org.apache.commons.lang.SerializationUtils;
import org.radot.base.JsonSerializerDeserializer;
import org.radot.gson.utils.ByteTransform;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;

/**
 * 
 * @author Iman
 *
 */
public class MessageGson extends Message {

	private int lineCount;
	private String fileName;
	private int size;
	private String disposition;
	private String contentType;
	private String description;
	private Date receivedDate;
	private Date sentDate;
	private String subject;
	private Address[] froms;
	private Address[] replyTo;
	private Map<String, Address[]> recipientsMap = new TreeMap<String, Address[]>();
	private Address[] allRecipients;
	private String content;

	private Map<String, String[]> headersMap = new HashMap<String, String[]>(0);
	
	private boolean expunge;
	private int messageNumber;
	
	public MessageGson(final Message object) {
		Enumeration<?> _headers = (Enumeration<?>) null;
		try {
			_headers = object.getAllHeaders();
			
			this.replyTo = object.getReplyTo();
			this.expunge = object.isExpunged();
			this.messageNumber = object.getMessageNumber();
			this.lineCount = object.getLineCount();
			this.fileName = object.getFileName();
			this.size = object.getSize();
			this.disposition = object.getDisposition();
			this.contentType = object.getContentType();
			this.receivedDate = object.getReceivedDate();
			this.sentDate = object.getSentDate();
			this.subject = object.getSubject();
			this.froms = object.getFrom();
			
			this.allRecipients = object.getAllRecipients();

			this.recipientsMap.put(RecipientType.TO.toString(), object.getRecipients(RecipientType.TO));
			this.recipientsMap.put(RecipientType.CC.toString(), object.getRecipients(RecipientType.CC));
			this.recipientsMap.put(RecipientType.BCC.toString(), object.getRecipients(RecipientType.BCC));
			
			final Object _content = object.getContent();
			if (_content instanceof Serializable) {
				final byte[] _serBytes = SerializationUtils.serialize((Serializable) _content);
				this.content = ByteTransform.encode(_serBytes);
			}
		} catch (final Throwable t) {
		}
		
		while (null != _headers && _headers.hasMoreElements()) {
			final Object _item = _headers.nextElement();
			if (!(_item instanceof String)) {
				continue;
			}
			
			final String _key = (String) _item;
			try {
				final String[] _val = object.getHeader(_key);
				if (null != _val) {
					this.headersMap.put(_key, _val);
				}
			} catch (final Throwable t) {
				t.getMessage();
			}
		}
	}

	@Override()
	public int getMessageNumber() {
		return this.messageNumber;
	}
	
	@Override()
	public void addRecipient(final RecipientType arg0, final Address arg1)
	throws MessagingException { }
	
	@Override()
	public Address[] getReplyTo() throws MessagingException {
		return this.replyTo;
	}
	
	@Override
	public boolean isExpunged() {
		return this.expunge;
	}

	@Override()
	protected void setExpunged(final boolean arg0) { }
	
	public static final class Adapter
	implements JsonSerializerDeserializer<Message> {

		private final Gson gson;
		
		public Adapter() {
			this.gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeHierarchyAdapter(Address.class, new AddressGson.Adapter())
				.create();
		}
		
		@Override()
		public Message deserialize(final JsonElement element, final Type type, final JsonDeserializationContext context) throws JsonParseException {
			return this.gson.fromJson(element.getAsJsonObject(), MessageGson.class);
		}

		@Override()
		public JsonElement serialize(final Message object, final Type type, final JsonSerializationContext context) {
			return this.gson.toJsonTree(new MessageGson(object));
		}

	}

	
	@Override()
	public Address[] getAllRecipients()
	throws MessagingException {
		return this.allRecipients;
	}
	
	@Override()
	public void addHeader(final String arg0, final String arg1)
	throws MessagingException { }

	@Override()
	public Enumeration<?> getAllHeaders() throws MessagingException {
		return Collections.enumeration(this.headersMap.keySet());
	}


	@Override()
	public Object getContent() throws IOException, MessagingException {
		Object _ret = (Object) null;
		try {
			final byte[] _bytes = ByteTransform.decode(this.content);
			_ret = SerializationUtils.deserialize(_bytes);
		} catch (final Throwable t) {
			t.getMessage();
		}

		return _ret;
	}

	@Override()
	public String getContentType()
	throws MessagingException {
		return this.contentType;
	}

	@Override
	public DataHandler getDataHandler() throws MessagingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override()
	public String getDescription()
	throws MessagingException {
		return this.description;
	}

	@Override()
	public String getDisposition()
	throws MessagingException {
		return this.disposition;
	}

	@Override()
	public String getFileName()
	throws MessagingException {
		return this.fileName;
	}

	@Override()
	public String[] getHeader(final String key)
	throws MessagingException {
		return this.headersMap.get(key);
	}

	@Override()
	public InputStream getInputStream() throws IOException, MessagingException {
		InputStream _ret = (InputStream) null;
		try {
			final byte[] _bytes = ByteTransform.decode(this.content);
			_ret = new ByteArrayInputStream(_bytes);
		} catch (final Throwable t) {
			t.getMessage();
		}

		return _ret;
	}

	@Override()
	public int getLineCount() throws MessagingException {
		return this.lineCount;
	}

	@Override
	public Enumeration<?> getMatchingHeaders(String[] arg0)
			throws MessagingException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Enumeration<?> getNonMatchingHeaders(String[] arg0)
	throws MessagingException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override()
	public int getSize()
	throws MessagingException {
		return this.size;
	}


	@Override()
	public boolean isMimeType(final String arg0) throws MessagingException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override()
	public void removeHeader(final String arg0)
	throws MessagingException { }

	@Override()
	public void setContent(final Multipart arg0)
	throws MessagingException { }

	@Override()
	public void setContent(final Object arg0, final String arg1)
	throws MessagingException { }


	@Override()
	public void setDataHandler(final DataHandler arg0)
	throws MessagingException { }

	@Override()
	public void setDescription(final String arg0)
	throws MessagingException { }

	@Override()
	public void setDisposition(final String arg0)
	throws MessagingException { }

	@Override()
	public void setFileName(final String arg0)
	throws MessagingException { }

	@Override()
	public void setHeader(final String arg0, final String arg1)
	throws MessagingException { }

	@Override()
	public void setText(final String arg0)
	throws MessagingException { }

	@Override()
	public void writeTo(final OutputStream arg0)
	throws IOException, MessagingException { }

	@Override()
	public void addFrom(final Address[] arg0)
	throws MessagingException { }

	@Override
	public void addRecipients(final RecipientType arg0, final Address[] arg1)
	throws MessagingException { }

	@Override
	public Flags getFlags() throws MessagingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override()
	public Address[] getFrom()
	throws MessagingException {
		return this.froms;
	}

	@Override()
	public Date getReceivedDate()
	throws MessagingException {
		return this.receivedDate;
	}

	@Override()
	public Address[] getRecipients(final RecipientType recType)
	throws MessagingException {
		return this.recipientsMap.get(recType.toString());
	}

	@Override()
	public Date getSentDate()
	throws MessagingException {
		return this.sentDate;
	}

	@Override()
	public String getSubject()
	throws MessagingException {
		return this.subject;
	}

	@Override()
	public Message reply(final boolean arg0)
	throws MessagingException {
		return null;
	}

	@Override()
	public void saveChanges()
	throws MessagingException { }

	@Override()
	public void setFlags(final Flags arg0, final boolean arg1)
	throws MessagingException { }

	@Override()
	public void setFrom()
	throws MessagingException { }

	@Override()
	public void setFrom(final Address arg0)
	throws MessagingException { }

	@Override()
	public void setRecipients(final RecipientType arg0, final Address[] arg1)
	throws MessagingException { }

	@Override()
	public void setSentDate(final Date sentDate) throws MessagingException { }

	@Override()
	public void setSubject(final String subject) throws MessagingException { }

}
