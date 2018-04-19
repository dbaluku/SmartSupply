package org.smartsupply.api.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class MailSender {

	public static void SendMailTLS(){

		final String username = "collegemandesystem@gmail.com";
		final String password = "jona2010";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("collegemandesystem@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("balukujr@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("This is ,"
					+ "\n\n No spam to my email, please!");

			Transport.send(message);

//			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void SendMailTLS(String to, String subject, String text) {

		final String username = "collegemandesystem@gmail.com";
		final String password = "jona2010";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("collegemandesystem@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(text, "text/html");
			// message.setText(text);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void SendMailTLS(List<String> to, String subject, String text) {

		final String username = "collegemandesystem@gmail.com";
		final String password = "jona2010";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("collegemandesystem@gmail.com"));
			for (String recipient : to) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
				message.setSubject(subject);
				message.setContent(text, "text/html");
				// message.setText(text);

				Transport.send(message);
			}

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
//	public void SendMailSSL() {
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class",
//				"javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");
//
//		Session session = Session.getDefaultInstance(props,
//				new javax.mail.Authenticator() {
//					protected PasswordAuthentication getPasswordAuthentication() {
//						return new PasswordAuthentication("username",
//								"password");
//					}
//				});
//
//		try {
//
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("from@no-spam.com"));
//			message.setRecipients(Message.RecipientType.TO,
//					InternetAddress.parse("to@no-spam.com"));
//			message.setSubject("Testing Subject");
//			message.setText("Dear Mail Crawler,"
//					+ "\n\n No spam to my email, please!");
//
//			Transport.send(message);
//
//			System.out.println("Done");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}


}
