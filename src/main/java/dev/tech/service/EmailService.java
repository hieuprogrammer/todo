package dev.tech.service;

import javax.mail.MessagingException;

public interface EmailService {
    String sendEmail();

    String sendEmailWithAttachments() throws MessagingException;

    String sendEmailWithHtmlContent() throws MessagingException;
}