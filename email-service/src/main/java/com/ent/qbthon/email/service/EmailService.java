package com.ent.qbthon.email.service;

import java.io.IOException;
import com.ent.qbthon.email.dto.EventDetailsDTO;
import com.ent.qbthon.email.exception.EmailServiceException;

public interface EmailService {
	void sendMail() throws EmailServiceException;
	void sendNominationMail(EventDetailsDTO eventDetails) throws EmailServiceException, IOException;
}
