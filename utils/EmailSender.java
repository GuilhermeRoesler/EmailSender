package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class EmailSender {

	public static void sendEmail(String to, String subject, String messageBody) {
		// credenciais do remetente
		String user = "youremail@gmail.com";
		String password = "yourpassword";

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
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(messageBody);

			Transport.send(message);
			System.out.println("E-mail enviado com sucesso!");
			JOptionPane.showMessageDialog(null, "Email enviado com sucesso!!!");
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null,
					"Houve algum erro durante o processo, por favor, troque as credenciais e tente novamente!");
			e.printStackTrace();
		}

	}
}
