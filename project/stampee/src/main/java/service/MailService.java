package service;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import util.MailAPIConfigLoader;

public class MailService {
	private static final String EMAIL = MailAPIConfigLoader.getEmail();
	private static final String APP_PASSWORD = MailAPIConfigLoader.getAPP_PASSWORD();

	// 받는 사람 to , 보내는 사람 from
	public void sendMail(String to, String from, String notice, String subject) throws MessagingException {
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com");        // Gmail SMTP 서버 주소
		prop.put("mail.smtp.port", "587");                    // TLS 인증 PORT
		prop.put("mail.smtp.auth", "true");                    // 인증 필요하다는 의미
		prop.put("mail.smtp.starttls.enable", "true");        // TLS 사용

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {            // Gmail 계정 및 앱 비밀번호
				return new PasswordAuthentication(EMAIL, APP_PASSWORD);
			}
		});

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));                // 발신자 설정
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));        // 수신자 설정
		msg.setSubject(subject);                // 제목 설정
		msg.setText(notice);

		Transport.send(msg);
	}
}
