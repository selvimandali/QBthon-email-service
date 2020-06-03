package com.ent.qbthon.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ent.qbthon.email.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	@GetMapping("/sendMail")
	public String getAllEvent(/*@PathVariable("userid") String userId*/
			@RequestParam(value = "version", required = false) String apiVersion) {
		emailService.sendMail();
		return "success";
	}

}
