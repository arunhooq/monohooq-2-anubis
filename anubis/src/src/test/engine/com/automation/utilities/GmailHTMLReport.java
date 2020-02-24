package com.automation.utilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.model.Message;

public class GmailHTMLReport {
	
	public static void sendEmailWithHTMLReport(String listOfRecipient , String Subject , String htmlReportPath ) throws MessagingException, IOException {

		try {
		//HTML parse
        Document doc = Jsoup.parse(new File(htmlReportPath), "utf-8"); 
        
        Elements elements = doc.select("img");
		elements.remove();
		
        Elements divTag = doc.getElementsByTag("html");
        
        String body = divTag.first().html();
     
		String htmlText = "<html>"+ body +"</html>";
	
		GmailAPI.getGmailService();
		MimeMessage mimemsg =createHTMLEmail(listOfRecipient, Subject, htmlText);
		
		Message message = createMessageWithEmail(mimemsg);

		message = GmailAPI.service.users().messages().send("me", message).execute();

		System.out.println(message.toPrettyString());
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("Cause is : "+e.getMessage());

		
	}
	}


	public static Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		emailContent.writeTo(buffer);
		byte[] bytes = buffer.toByteArray();
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
		Message message = new Message();
		message.setRaw(encodedEmail);
		return message;
	}

//	public static MimeMessage createEmailWithAttachment(String to, String from, String subject, String bodyText,
//			File file) throws MessagingException, IOException {
//		Properties props = new Properties();
//		Session session = Session.getDefaultInstance(props, null);
//
//		MimeMessage email = new MimeMessage(session);
//
//		email.setFrom(new InternetAddress(from));
//		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
//		email.setSubject(subject);
//
//		MimeBodyPart mimeBodyPart = new MimeBodyPart();
//		mimeBodyPart.setContent(bodyText, "text/html");
//
//		Multipart multipart = new MimeMultipart();
//		multipart.addBodyPart(mimeBodyPart);
//
//		mimeBodyPart = new MimeBodyPart();
//		DataSource source = new FileDataSource(file);
//
//		mimeBodyPart.setDataHandler(new DataHandler(source));
//		mimeBodyPart.setFileName(file.getName());
//		
//
//		multipart.addBodyPart(mimeBodyPart);
//		email.setContent(multipart,"text/html");
//
//		return email;
//	}

	public static MimeMessage createHTMLEmail(String to, String subject, String html) throws AddressException, MessagingException {
        
		String[] split = to.split(",");
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        MimeMessage email = new MimeMessage(session);
        Multipart multiPart = new MimeMultipart("alternative");
        email.setFrom(new InternetAddress("me"));
        
        
        for(int i=0;i<split.length;i++) {
        	email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(split[i]));
        }
    
        email.setSubject(subject);

        //MimeBodyPart textPart = new MimeBodyPart();
        //textPart.setText(text, "utf-8");

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(html, "text/html; charset=utf-8");

       // multiPart.addBodyPart(textPart);
        multiPart.addBodyPart(htmlPart);
        email.setContent(multiPart);
        return email;
    }
	
	public static void main(String... args) {

		try {
			sendEmailWithHTMLReport("ali@hooq.tv,afsarali273@gmail.com","This a Automated Email with HTML","/Users/mdafsarali/repository/HOOQ_QA_Cigniti/test-output/emailable-report.html");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
