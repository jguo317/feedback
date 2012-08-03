package com.active.feedback.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.active.feedback.util.Constant;

public class EmailHandle {
	
	public void sendEmail(String emailSubject, String emailContent, String emailFrom, String emailTo){
		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server
		props.put("mail.smtp.host", Constant.EMAIL_HOST);
		
		javax.mail.Session msession = javax.mail.Session.getInstance(props,null);
		MimeMessage message = new MimeMessage(msession);
		try {
			message.setFrom(new InternetAddress(emailFrom));
			String[] recipients = emailTo.split(";");
			for (int i = 0; i < recipients.length; i++) {
				message.addRecipients(Message.RecipientType.TO, recipients[i]);				
			}
			
			message.setSubject(emailSubject);
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			
			StringBuffer content = new StringBuffer();
			content.append(emailContent);
			
			messageBodyPart.setContent(content.toString(), "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			// Put parts in message
			message.setContent(multipart);
			// Send the message
			Transport.send(message);
			// System.out.println("sent");

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmailHandle mail = new EmailHandle();
		mail.sendEmail("360feedback test","360feedback test","jay.guo@active.com","jay.guo@active.com");
		System.out.println("Email sent successfully!");
	}

}
