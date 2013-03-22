package com.urexst.model;


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.mail.internet.MimeBodyPart;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.IOUtils;


public class Mail {
	
	public static int sendMail(String dest,String subject, String body ) throws AddressException,MessagingException,UnsupportedEncodingException{

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);


		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("admin@urexst.appspotmail.com", "UR exchange app system"));

		msg.addRecipient(Message.RecipientType.TO,
				new InternetAddress(dest));

		msg.setSubject(subject);
		msg.setText(body);
		Transport.send(msg);
		return 0;

	}


	public static MimeMessage createMimeMessage(HttpServletRequest
			request)
	throws MessagingException, IOException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session,
				request.getInputStream());
		return message;
	}

	// http://commons.apache.org/codec/xref/org/apache/commons/codec/net/QuotedPrintableCodec.html
	private static final byte ESCAPE_CHAR = '=';

	public static String decodeQuotedPrintable(byte[] bytes, String
			charset)
	throws IOException {
		return new String(decodeQuotedPrintable(bytes), charset);
	}

	public static byte[] decodeQuotedPrintable(byte[] bytes) throws
	IOException {
		if (bytes == null) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (int i = 0; i < bytes.length; i++) {
			int b = bytes[i];
			if (b == ESCAPE_CHAR) {
				try {
					if (bytes[i + 1] == 10) {
						// FIX skip newline, lenient
						++i;
					} else {
						int u = digit16(bytes[++i]);
						int l = digit16(bytes[++i]);
						out.write((char) ((u << 4) + l));
					}
				} catch (Exception e) {
					throw new IOException("Invalid quoted-printable	encoding", e);
				}
			} else {
				out.write(b);
			}
		}
		return out.toByteArray();
	}

	public static int digit16(byte b) throws IOException {
		int i = Character.digit(b, 16);
		if (i == -1) {
			throw new IOException("Invalid encoding: not a valid digit	(radix 16): " + b);
		}
		return i;
	}

	public static Object getContent(MimeMessage message) throws
	Exception {
		String charset = contentType2Charset(message.getContentType(),
				null);
		Object content;
		try {
			content = message.getContent();
		} catch (Exception e) {
			try {
				byte[] out = IOUtils.toByteArray(message.getRawInputStream());
				out = decodeQuotedPrintable(out);
				if (charset != null) {
					content = new String(out, charset);
				} else {
					content = new String(out);
				}
			} catch (Exception e1) {
				throw e;
			}
		}
		return content;
	}

	public static Object getContent(MimeBodyPart part) throws
	Exception {
		String charset = contentType2Charset(part.getContentType(),
				null);
		Object content;
		try {
			content = part.getContent();
		} catch (Exception e) {
			try {
				byte[] out = IOUtils.toByteArray(part.getRawInputStream());
				out = decodeQuotedPrintable(out);
				if (charset != null) {
					content = new String(out, charset);
				} else {
					content = new String(out);
				}
			} catch (Exception e1) {
				throw e;
			}
		}
		return content;
	}

	public static String contentType2Charset(String contentType,
			String defaultCharset) {
		String charset = defaultCharset;
		if (contentType.indexOf("charset=") != -1) {
			String[] split = contentType.split("charset=");
			if (split.length > 1) {
				charset = split[1];
				if (charset.indexOf(';') >= 0) {
					charset = charset.substring(0,
							charset.indexOf(';'));
				}
				charset = charset.replaceAll("\"", "");
				charset = charset.trim();
			}
		}
		return charset;
	}

	
}



