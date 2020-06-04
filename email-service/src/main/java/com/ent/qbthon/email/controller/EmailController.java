package com.ent.qbthon.email.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ent.qbthon.email.dto.EventDetailsDTO;
import com.ent.qbthon.email.exception.EmailServiceException;
import com.ent.qbthon.email.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	@GetMapping("/sendMail")
	public String getAllEvent(@RequestParam(value= "eventDetails" ,required= true) EventDetailsDTO eventDetails) throws EmailServiceException, IOException {
		emailService.sendNominationMail(eventDetails);
		return "success";
	}

}
