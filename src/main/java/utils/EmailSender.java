package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import io.github.cdimascio.dotenv.Dotenv;

public class EmailSender {

	public static void sendEmail(String to, String subject, String messageBody) throws Exception {
		// credenciais do remetente
		Dotenv dotenv = Dotenv.load();
		String user = dotenv.get("EMAIL");
		String password = dotenv.get("PASSWORD");

		// Properties para armazenar configurações SMTP
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		// nova sessão de e-mail
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// criação da mensagem
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(user));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(messageBody);

		Transport.send(message);
		System.out.println("E-mail enviado com sucesso!");
	}
}
