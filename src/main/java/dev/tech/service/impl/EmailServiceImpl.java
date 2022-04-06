package dev.tech.service.impl;

import dev.tech.service.EmailService;
import dev.tech.service.util.email.EmailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendEmail() {
        this.logger.info("INFO: E-Mail Service - sendEmail() method called.");
        this.logger.debug("DEBUG: E-Mail Service - sendEmail() method called.");
        this.logger.trace("TRACE: E-Mail Service - sendEmail() method called.");
        this.logger.warn("WARN: E-Mail Service - sendEmail() method called.");
        this.logger.error("ERROR: E-Mail Service - sendEmail() method called.");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(EmailConstants.USERS_EMAILS);
        simpleMailMessage.setSubject(":D TO-DOs :D");
        simpleMailMessage.setText(":D A new to-do is successfully created. :D");

        try {
            this.javaMailSender.send(simpleMailMessage);
            return "E-mail successfully sent.";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    @Override
    public String sendEmailWithAttachments() throws MessagingException {
        this.logger.info("INFO: E-Mail Service - sendEmailWithAttachments() method called.");
        this.logger.debug("DEBUG: E-Mail Service - sendEmailWithAttachments() method called.");
        this.logger.trace("TRACE: E-Mail Service - sendEmailWithAttachments() method called.");
        this.logger.warn("WARN: E-Mail Service - sendEmailWithAttachments() method called.");
        this.logger.error("ERROR: E-Mail Service - sendEmailWithAttachments() method called.");

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        boolean multiPart = true;

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multiPart);
        mimeMessageHelper.setTo(EmailConstants.USERS_EMAILS);
        mimeMessageHelper.setSubject(":D TO-DOs :D");
        mimeMessageHelper.setText(":D A new to-do is successfully created. :D");

        String pathToFile0 = "/D:/Testing Spring E-Mail/HelloWorldInJava.txt";
        String pathToFile1 = "/D:/Testing Spring E-Mail/HelloWorldInC#.txt";

        FileSystemResource file0 = new FileSystemResource(new File(pathToFile0));
        mimeMessageHelper.addAttachment("How to say Hello, world! in Java.", file0);
        FileSystemResource file1 = new FileSystemResource(new File(pathToFile1));
        mimeMessageHelper.addAttachment("How to say Hello, world! in C#.", file1);

        try {
            this.javaMailSender.send(mimeMessage);
            return "E-mail successfully sent.";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    @Override
    public String sendEmailWithHtmlContent() throws MessagingException {
        this.logger.info("INFO: E-Mail Service - sendEmailWithHtmlContent() method called.");
        this.logger.debug("DEBUG: E-Mail Service - sendEmailWithHtmlContent() method called.");
        this.logger.trace("TRACE: E-Mail Service - sendEmailWithHtmlContent() method called.");
        this.logger.warn("WARN: E-Mail Service - sendEmailWithHtmlContent() method called.");
        this.logger.error("ERROR: E-Mail Service - sendEmailWithHtmlContent() method called.");

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        boolean multiPart = true;
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multiPart, "utf-8");

        String htmlMessage = "<div style=\"font-family: monospace; text-align: center;\"><h1>:D TO-DOs :D</h1><h2 >A new to-do is successfully added.</h2></div>";
        mimeMessage.setContent(htmlMessage, "text/html");

        mimeMessageHelper.setTo(EmailConstants.USERS_EMAILS);
        mimeMessageHelper.setSubject(":D TO-DOs :D");

        try {
            this.javaMailSender.send(mimeMessage);
            return "E-mail successfully sent.";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }
}