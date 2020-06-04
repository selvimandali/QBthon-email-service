package com.ent.qbthon.email.service;

import com.ent.qbthon.email.exception.EmailServiceException;

public interface EmailSender {
	void sendMail(String emailBody) throws EmailServiceException;
}
