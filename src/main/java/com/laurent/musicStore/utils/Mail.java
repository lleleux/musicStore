package com.laurent.musicStore.utils;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class Mail {
	
	private MailSender mailSender;
 
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String msg) {
		Thread thread = new SendMailThread(from, to, subject, msg);
		thread.start();
	}
	
	class SendMailThread extends Thread {
		
		private String from;
		private String to;
		private String subject;
		private String msg;

		public SendMailThread(String from, String to, String subject, String msg) {
			super();
			this.from = from;
			this.to = to;
			this.subject = subject;
			this.msg = msg;
		}

		@Override
		public void run() {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(msg);
			mailSender.send(message);	
		}
		
	}
}