package com.urexst;

import java.io.IOException; 
import java.util.Properties; 

import javax.mail.MessagingException;
import javax.mail.Session; 
import javax.mail.internet.MimeMessage; 
import javax.servlet.http.*; 

public class MailHandlerServlet extends HttpServlet { 
    public void doPost(HttpServletRequest req, 
                       HttpServletResponse resp) 
            throws IOException { 
        Properties props = new Properties(); 
        Session session = Session.getDefaultInstance(props, null); 
        try {
			MimeMessage message = new MimeMessage(session, req.getInputStream());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
    