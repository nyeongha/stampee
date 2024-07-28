package service;

import java.util.Properties;
import javax.mail.* ;
import javax.mail.internet.* ;

import util.ConfigLoader;

public class MailService {
	private static final String EMAIL;
	private static final String APP_PASSWORD;

	static {
		String mail_address;
		String password;
		try {
			mail_address = ConfigLoader.getEmail();
			password = ConfigLoader.getAPP_PASSWORD();
		} catch (IllegalStateException e) {
			e.getMessage();
			mail_address = "DUMMY_EMAIL"; // 또는 다른 적절한 기본값
			password = "DUMMY_APPPASSWORD";
		}
		EMAIL = mail_address;
		APP_PASSWORD = password;
	}
	/** TODO 사용 예제
		try{
			MailService.sendMail(
				"thdms1313@gmail.com",
				"human9062@gmail.com",
				"쿠폰 만료 임박 안내입니다."
					+ "3일 내로 사용해주시면 감사하겠습니다."
			);
			System.out.println("great!") ;
		} catch(MessagingException e) {
			System.out.println("send fail");
		}
	 **/

	// 받는 사람 to , 보내는 사람 from
	public void sendMail(String to, String from, String notice) throws MessagingException {
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com");		// Gmail SMTP 서버 주소
		prop.put("mail.smtp.port", "587");					// TLS 인증 PORT
		prop.put("mail.smtp.auth", "true");					// 인증 필요하다는 의미
		prop.put("mail.smtp.starttls.enable", "true");		// TLS 사용

		Session session = Session.getInstance(prop, new Authenticator() {
			// @Override
			protected PasswordAuthentication getPasswordAuthentication() {			// Gmail 계정 및 앱 비밀번호
				return new PasswordAuthentication(EMAIL, APP_PASSWORD) ;
			}
		}) ;

		Message msg = new MimeMessage(session) ;
		msg.setFrom(new InternetAddress(from));				// 발신자 설정
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));		// 수신자 설정
		msg.setSubject("카페 쿠폰 만료 예정 안내");				// 제목 설정
		msg.setText(notice);

		Transport.send(msg);
	}
}
