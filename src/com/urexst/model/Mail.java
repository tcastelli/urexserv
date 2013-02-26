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


public class Mail {

	private Mail(){

	}

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






}
