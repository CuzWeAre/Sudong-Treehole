package com.cwr.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;


@Slf4j
@Service
public class MailService {

    //指定发送者
    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendVerificationMail(String to, String subject, String username, String verificationCode) throws MessagingException {

        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("verificationCode", verificationCode);
        String content = templateEngine.process("mail", context);


        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        mimeMessageHelper.setFrom(from);

        try {
            mailSender.send(mimeMailMessage);
            log.info(username + "@" + verificationCode +"：验证码邮件发送成功");
        } catch (MailException e) {
            log.error(username + "@" + verificationCode +"：验证码邮件发送失败！", e);
        }
    }


    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(from);

        //发送邮件
        try {
            mailSender.send(simpleMailMessage);
            log.info("简单邮件发送成功");
        } catch (MailException e) {
            log.error("简单邮件发送失败", e);
        }
    }


    public void sendHtmlMail(String to, String subject, String content) throws MessagingException {


        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);
        mimeMessageHelper.setFrom(from);

        try {
            mailSender.send(mimeMailMessage);
            log.info("HTML邮件发送成功！");
        } catch (MailException e) {
            log.error("HTML邮件发送失败！", e);
        }
    }


}
