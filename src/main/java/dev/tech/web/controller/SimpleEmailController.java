//package dev.tech.web.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/email")
//public class SimpleEmailController {
//    private final JavaMailSender javaMailSender;
//
//    @Autowired
//    public SimpleEmailController(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    @GetMapping("send-simple-email")
//    public String sendSimpleEmail() {
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo("hieu.minhle@outlook.com");
//        simpleMailMessage.setSubject("Testing Spring E-Mail - Simple Email");
//        simpleMailMessage.setText(":D Hello, Spring E-Mail! :D");
//
//        this.javaMailSender.send(simpleMailMessage);
//        return "Simple E-mail sent.";
//    }
//}