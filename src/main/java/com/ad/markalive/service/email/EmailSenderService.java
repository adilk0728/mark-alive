package com.ad.markalive.service.email;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderService {
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public EmailSenderService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String toAddress, String fromAddress, String subject, String body) {
        SimpleMailMessage templateMessage = new SimpleMailMessage();
        templateMessage.setTo(toAddress);
        templateMessage.setFrom(fromAddress);
        templateMessage.setSubject(subject);
        templateMessage.setText(body);
        try{
            this.mailSender.send(templateMessage);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }

    }

    public SimpleMailMessage getTemplateMessage() {
        return templateMessage;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
}
