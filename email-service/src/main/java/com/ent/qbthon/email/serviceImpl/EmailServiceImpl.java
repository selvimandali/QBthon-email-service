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
import com.ent.qbthon.email.service.EmailService;
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private JavaMailSender emailSender;
	
	public EmailServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendMail() throws EmailServiceException {
		// TODO Auto-generated method stub
		MimeMessagePreparator preparator= mimeMessage->{
		MimeMessageHelper msg= new MimeMessageHelper(mimeMessage,true);
		msg.setFrom("cognizantleanrn@cognizant.com");
		msg.setTo("selvimandali2096@gmail.com");
		msg.setSubject("QBthon Nominations - Here are the skill areas!");
		msg.setText(getMsgBody(),true);
		};
		emailSender.send(preparator);
		
	}
	private String getMsgBody() throws IOException {
		String TemplateFileName="templates/QBthon Nominations - Here are the Skill areas!.htm";
		File templateFile=new ClassPathResource(TemplateFileName).getFile();
		String str=FileUtils.readFileToString(templateFile,"UTF-8");
		return str;
	}
	

}
