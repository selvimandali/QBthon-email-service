package com.ent.qbthon.email.service;

import com.ent.qbthon.email.exception.EmailServiceException;

public interface EmailService {
 void sendMail() throws EmailServiceException;
}
