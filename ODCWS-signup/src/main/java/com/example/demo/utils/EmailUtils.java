package com.example.demo.utils;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailUtils {

	@Autowired
	private JavaMailSender emailSender;

	// not completed
	public void userRegistration(String to, String subject, String name) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("nikhil7singh02@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		String htmlMsg = "<p><b>Hello " + name + ", </b><br>" + "Greetings from the CarWash... <br>"
				+ "You are successfully registered now. Book your carwash now....<br>"
				+ "<a href=\"http://localhost:8081/api/auth/login\" target=\"_blank\">Click here to login</a><br>"
				+ "</p>";
		message.setContent(htmlMsg, "text/html");
		emailSender.send(message);
	}

	/**
	 * public void userRegistration(String to, String subject, String name) throws
	 * MessagingException { MimeMessage message = emailSender.createMimeMessage();
	 * MimeMessageHelper helper = new MimeMessageHelper(message, true);
	 * helper.setFrom("nikhil7singh02@gmail.com"); helper.setTo(to);
	 * helper.setSubject(subject); String html= "<b>Hello " + name + ",<b>" + "
	 * <p>
	 * Greetings from Carwash.
	 * </p>
	 * " + "
	 * <p>
	 * Your registration was successful. Please verify and activate your account by
	 * clicking the activation link below:
	 * </p>
	 * " + "
	 * <p>
	 * <a href=\"\" target='_blank'>Activate Your Account</a>
	 * </p>
	 * " + "
	 * <p>
	 * Get your car washed now and enjoy our services!
	 * </p>
	 * " + "
	 * <p>
	 * <a href=\"http://localhost:8081/api/auth/login\" target='_blank'>Click here
	 * to login</a>
	 * </p>
	 * ";
	 * 
	 * 
	 * message.setContent(htmlMsg,"text/html"); emailSender.send(message); }
	 * @throws MessagingException 
	 **/
	public void userLogin(String to, String subject) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("nikhil7singh02@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		String htmlMsg = "<p><b>Hello User, </b><br>" + "You just Logged in.<br>"
				+ "If it is not you. Contact Admin now.<br>"
				+ "</p>";
		message.setContent(htmlMsg, "text/html");
		emailSender.send(message);
	}

	// testing
	public void sendSimpleMessaage(String to, String subject, String text, List<String> list) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("nikhil7singh02@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		if (list != null && list.size() > 0) {
			message.setCc(getCcArray(list));

		}
		emailSender.send(message);
	}

	private String[] getCcArray(List<String> ccList) {
		String[] cc = new String[ccList.size()];
		for (int i = 0; i < ccList.size(); i++) {
			cc[i] = ccList.get(i);
		}
		return cc;
	}

	public void forgotMail(String to, String subject, String password) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("nikhil7singh02@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);
		String htmlMsg = "<p><b>Your Login details for Cafe Management System</b><br>" + "<b>Email: </b>" + to + "<br>"
				+ "<b>Password: </b>" + password + "<br>"
				+ "<a href=\"http://localhost:8081/api/auth/login\">Click here to login</a></p>";
		message.setContent(htmlMsg, "text/html");
		emailSender.send(message);
	}

}
