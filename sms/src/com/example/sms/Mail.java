package com.example.sms;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;



public class Mail{


	public static void setmail() throws Exception
	{
		 Properties props = System.getProperties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.qq.com");
	        props.put("mail.smtp.user", "1247686880@qq.com");
	        props.put("mail.smtp.password", "lovemyself");
	        props.put("mail.smtp.port", "25");
	        props.put("mail.smtp.auth", "true");
	        
	        Session session = Session.getDefaultInstance(props, null);
	        DataHandler handler = new DataHandler(new ByteArrayDataSource("".getBytes(), "text/plain"));
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("1247686880@qq.com"));
	        message.setDataHandler(handler);
	        MimeMultipart multiPart = new MimeMultipart();
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress("1247686880@qq.com"));
	        message.setSubject("Hello World Subject");
	        message.setContent(multiPart);
	        message.setText("Hello World Text");

	        Transport transport = session.getTransport("smtp");
	       
	        transport.connect("smtp.qq.com", "1247686880@qq.com","lovemyself");
	        
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	}
}