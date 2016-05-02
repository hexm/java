package com.icbc.hexm.mail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.icbc.hexm.util.Log4jUtil;

/**
 * @author Administrator
 * 
 */
public class EmailSender {

	// private static final ExecutorService pool =
	// Executors.newSingleThreadExecutor();

	private static final Logger log = Log4jUtil.getLogger(EmailSender.class.getName());

	// private static final String PASSWORD = "tph105";
	// private static final String USER_NAME = "95688516@qq.com";
	// private static final String SMTP_HOST = "smtp.qq.com";

	private static final String PASSWORD = "icbc2012";
	private static final String USER_NAME = "heming568@163.com";
	private static final String SMTP_HOST = "smtp.163.com";

	private static final String SMTP_AUTH = "true";
	private static final String SMTP_PORT = "25";

	public static void main(String args[]) {
		try {
			EmailSender.sendEmail("95688516@qq.com", "有人取消关注啦！", "有一个用户取消关注你的微信公众号");
			// sendEmail(USER_NAME, "subject", "content");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param subject
	 * @param content
	 * @throws AddressException
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static void sendEmail(String subject, String content) {
		sendEmail(USER_NAME, subject, content);
	}

	public static void sendEmail(String[] tos, String subject, String content) {
		for (String to : tos) {
			sendEmail(to, subject, content);
		}
	}

	/**
	 * @param to
	 * @param subject
	 * @param content
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void sendEmail(final String to, final String subject, final String content) {
		log.debug("Submit send email thread to thread pool.");
		new Thread(new Runnable() {

			public void run() {
				try {
					log.debug("sendEmail to " + to + " begin.");
					Properties properties = new Properties();
					properties.put("mail.smtp.host", SMTP_HOST);
					properties.put("mail.smtp.port", SMTP_PORT);
					properties.put("mail.smtp.auth", SMTP_AUTH);
					Authenticator authenticator = new EmailAuthenticator(USER_NAME, PASSWORD);
					javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
					MimeMessage mailMessage = new MimeMessage(sendMailSession);

					mailMessage.setFrom(new InternetAddress(USER_NAME));

					// Message.RecipientType.TO属性表示接收者的类型为TO
					mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
					mailMessage.setSubject(subject + "[" + System.currentTimeMillis() +"]", "UTF-8");
					mailMessage.setSentDate(new Date());
					// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
					Multipart mainPart = new MimeMultipart();
					// 创建一个包含HTML内容的MimeBodyPart
					BodyPart html = new MimeBodyPart();
					html.setContent("[" + System.currentTimeMillis() +"]" + content.trim(), "text/html; charset=utf-8");
					mainPart.addBodyPart(html);
					mailMessage.setContent(mainPart);
					Transport.send(mailMessage);
					log.debug("sendEmail to " + to + " end.");
				} catch (Exception e) {
					log.debug("EmailSender sendEmail to " + to + " exception:", e);
				}
			}

		}).start();
	}
}
