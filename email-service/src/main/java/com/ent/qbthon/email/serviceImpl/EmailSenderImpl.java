package com.ent.qbthon.email.serviceImpl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.ent.qbthon.email.exception.EmailServiceException;
import com.ent.qbthon.email.service.EmailSender;
@Service
public class EmailSenderImpl implements EmailSender {
	@Autowired
    private JavaMailSender emailSender;

	public EmailSenderImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendMail(String emailBody) throws EmailServiceException {
		// TODO Auto-generated method stub
		MimeMessagePreparator preparator= mimeMessage->{
			MimeMessageHelper msg= new MimeMessageHelper(mimeMessage,true);
			msg.setFrom("cognizantleanrn@cognizant.com");
			msg.setTo("selvimandali2096@gmail.com");
			msg.setSubject("QBthon Nominations - Here are the skill areas!");
			msg.setText(emailBody,true);
			};
			emailSender.send(preparator);
	}

}
