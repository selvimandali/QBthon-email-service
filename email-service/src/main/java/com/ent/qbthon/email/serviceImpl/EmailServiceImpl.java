package com.ent.qbthon.email.serviceImpl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.ent.qbthon.email.dto.EventDetailsDTO;
import com.ent.qbthon.email.exception.EmailServiceException;
import com.ent.qbthon.email.service.EmailSender;
import com.ent.qbthon.email.service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
    private  EmailSender emailSender;
	
	@Autowired
    private JavaMailSender javaEmailSender;
	
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
		javaEmailSender.send(preparator);
		
	}
	private String getMsgBody() throws IOException {
		String TemplateFileName="templates/QBthon Nominations - Here are the Skill areas!.htm";
		File templateFile=new ClassPathResource(TemplateFileName).getFile();
		String str=FileUtils.readFileToString(templateFile,"UTF-8");
		return str;
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> processEventDetails(EventDetailsDTO eventDetails) throws EmailServiceException {
		Map<String, String> eventDetailsPropertyMap;
		ObjectMapper objectMapper= new ObjectMapper();
		String date= eventDetails.getDate();
		String day=date.split("-")[0];
		String month=date.split("-")[1];
		eventDetailsPropertyMap=objectMapper.convertValue(eventDetails, Map.class);
		eventDetailsPropertyMap.put("day", day);
		eventDetailsPropertyMap.put("month", month);
		StringBuilder skillTable=new StringBuilder();
		skillTable.append("<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=0 style='width:7.25in;border-collapse:collapse;mso-yfti-tbllook:1184;mso-padding-alt:0in 0in 0in 0in'>");
		for(String skill:eventDetails.getSkills()) {
			if(eventDetails.getSkills().indexOf(skill)%2==0)
			{
				int i =0;
				if(eventDetails.getSkills().indexOf(skill)==0) {
					skillTable.append("<tr style='mso-yfti-irow:"+String.valueOf(i)+";mso-yfti-firstrow:yes;height:20.1pt'>");
					i++;
				}
				else
				{
					skillTable.append("<tr style='mso-yfti-irow:"+String.valueOf(i)+";height:20.1pt'>");
				}
				skillTable.append("<td width=300 style='width:225.0pt;border-top:solid #C9C9C9 1.0pt;\r\n" + 
						"      border-left:none;border-bottom:solid white 1.0pt;border-right:none;\r\n" + 
						"      background:#0033CC;padding:0in 5.4pt 0in 5.4pt;height:20.1pt'>\r\n" + 
						"      <p class=MsoNormal><b><span style='font-family:\"Arial\",sans-serif;\r\n" + 
						"      color:#1F497D'>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;</span></b><b><span\r\n" + 
						"      style='font-family:\"Arial\",sans-serif;color:white'>"+skill+"<o:p></o:p></span></b></p>\r\n" + 
						"      </td>");
			}
			else {
				skillTable.append("<td width=396 style='width:297.0pt;border-top:solid #C9C9C9 1.0pt;\r\n" + 
						"      border-left:none;border-bottom:solid white 1.0pt;border-right:none;\r\n" + 
						"      background:#0033CC;padding:0in 5.4pt 0in 5.4pt;height:20.1pt'>\r\n" + 
						"      <p class=MsoNormal><b><span style='font-family:\"Arial\",sans-serif;\r\n" + 
						"      color:white'>"+skill+"<o:p></o:p></span></b></p>\r\n" + 
						"      </td>\r \n"+
						"	</tr>");
			}
		}
		if(eventDetails.getSkills().size()%2==0) {
			skillTable.append("</tr>");
		}
		skillTable.append("</table>");
		eventDetailsPropertyMap.put("skillTable", skillTable.toString());
		return eventDetailsPropertyMap;
	}

	@Override
	public void sendNominationMail(EventDetailsDTO eventDetails) throws EmailServiceException, IOException {
		Map<String, String>eventDetailsPropertyMap=processEventDetails(eventDetails);
		String TemplateFileName="templates/QBthon Nominations - Here are the Skill areas!.htm";
		File templateFile=new ClassPathResource(TemplateFileName).getFile();
		FileReader reader=new FileReader(templateFile);
		Template template= Mustache.compiler().compile(reader);
		String emailBody=template.execute(eventDetailsPropertyMap);
		emailSender.sendMail(emailBody);
	}

	
	

}
