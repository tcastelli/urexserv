package com.urexst;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart; 
import javax.mail.internet.MimeMessage; 
import javax.servlet.http.*; 

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import com.urexst.model.Mail;
import com.urexst.model.News;


public class MailHandlerServlet extends HttpServlet { 

	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest req, 
			HttpServletResponse resp) 
	throws IOException { 


		try {
			MimeMessage message = Mail.createMimeMessage(req);

			if (processMessage(message)) {
				System.err.println("Incoming email handled");
			} else {
				System.err.println("Failed to handle incoming email");
			}
		} catch (MessagingException e) {
			System.err.println("MessagingException: " + e);
			e.printStackTrace();
		}
	}


	private boolean processMessage(MimeMessage message) throws MessagingException {
		Date date = getMessageDate(message);
		String subject = message.getSubject();
		String from = "unknown";

		try {
			from = message.getFrom()[0].toString();
			Object content = Mail.getContent(message);

			if (message.getContentType().startsWith("text/plain")) {
				processMail(from, subject, date, (String) content, "text/plain");
				return true;
			} 
			else if (message.getContentType().startsWith("text/html")){
				processMail(from, subject, date, (String) content, "text/html");
				return true;			
			}
			
			else if (content instanceof Multipart) {
				Multipart mp = (Multipart) content;
				for (int i = 0; i < mp.getCount(); i++) {
					if (handlePart(from, subject, date, mp.getBodyPart(i))) {
						return true;
					}
				}
				return false;
			} else {
				System.err.println("Unable to process message content - unknown content type");
			}
		} catch (IOException e) {
			System.err.println("Exception handling incoming email " + e);
		} catch (MessagingException e) {
			System.err.println("Exception handling incoming email " + e);
		} catch (Exception e) {
			System.err.println("Exception handling incoming email " + e);
		}

		return false;
	}

	private void processMail(String from, String subject, Date date, String content, String type) {

		News n = new News();
		n.setAuthor(from);
		n.setSubject(subject);
		n.setType(type);


		n.setDate(date.getTime());
		n.setEnglishTxt(content);

		if (n.getEnglishTxt()!=null && n.getEnglishTxt().length()>0){
			Translate.setClientId("urexst");
			Translate.setClientSecret("cPFJu70lglgPwon28Vmuk7pz31Idlk81fc32NXjT9Ds=");

			try {
				n.setSpanishTxt(Translate.execute(n.getEnglishTxt(), Language.ENGLISH,Language.SPANISH));
				n.setGermanTxt(Translate.execute(n.getEnglishTxt(), Language.ENGLISH,Language.GERMAN));
				n.setFrenchTxt(Translate.execute(n.getEnglishTxt(), Language.ENGLISH,Language.FRENCH));
				n.setItalianTxt(Translate.execute(n.getEnglishTxt(), Language.ENGLISH,Language.ITALIAN));	
				//Need to add different groups to the message
				ArrayList<String> groups = new ArrayList<String>();	
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());
				groups.add(String.valueOf(cal.get(Calendar.YEAR)));					
				n.setGroupList(groups);

				//Update the database with the new info with sync call
				OfyService.ofy().save().entity(n).now();
				//OfyService.ofy().load().type(News.class).order("date").limit(10).list();


			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}



	private boolean handlePart(String from, String subject, Date date, BodyPart part)
	throws MessagingException, IOException {
		if (part.getContentType().startsWith("text/plain")){
			processMail(from, subject, date, (String) part.getContent(),"text/plain");
			return true;

		}
		else if ( part.getContentType().startsWith("text/html")) {
			processMail(from, subject, date, (String) part.getContent(),"text/html");
			return true;
		}
		else {
			if (part.getContent() instanceof Multipart) {
				Multipart mp = (Multipart) part.getContent();
				System.err.println("Handling a multipart sub-message with " + mp.getCount() + " sub-parts");
				for (int i = 0; i < mp.getCount(); i++) {
					if (handlePart(from, subject, date, mp.getBodyPart(i))) {
						return true;
					}
				}
				System.err.println("No text or HTML part in the multipart mime sub-message");
			}
			return false;
		}
	}




	private Date getMessageDate(Message message) {
		Date when = null;
		try {
			when = message.getReceivedDate();
			if (when == null) {
				when = message.getSentDate();
			}
			if (when == null) {
				return null;
			}
		} catch (MessagingException e) {
			System.err.println("Cannot get message date: " + e);
			e.printStackTrace();
			return null;
		}

		//DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		return (when);
	}


}
