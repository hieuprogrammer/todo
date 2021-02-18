package dev.tech.service.impl;

import dev.tech.service.EmailService;
import dev.tech.util.email.EmailConstants;
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
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendEmail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(EmailConstants.USERS_EMAILS);
        simpleMailMessage.setSubject("Testing Spring E-Mail");
        simpleMailMessage.setText(":D Hello, Spring E-Mail! :D");

        try {
            this.javaMailSender.send(simpleMailMessage);
            return "E-mail successfully sent.";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    @Override
    public String sendEmailWithAttachments() throws MessagingException {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        boolean multiPart = true;

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multiPart);
        mimeMessageHelper.setTo(EmailConstants.USERS_EMAILS);
        mimeMessageHelper.setSubject("Testing Spring E-Mail - Email with Attachments");
        mimeMessageHelper.setText(":D Hello, Spring E-Mail! :D");

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
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        boolean multiPart = true;
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, multiPart, "utf-8");

        String htmlMessage = "<div style=\"font-family: monospace; text-align: center;\"><h1>😃📒TO-DOs📒😃</h1><h2 >A new to-do is successfully added.</h2></div>";
        mimeMessage.setContent(htmlMessage, "text/html");

        mimeMessageHelper.setTo(EmailConstants.USERS_EMAILS);
        mimeMessageHelper.setSubject("Testing Spring E-Mail - Email with HTML Content");

        try {
            this.javaMailSender.send(mimeMessage);
            return "E-mail successfully sent.";
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }
}