package com.ad.markalive.service.email;

import com.ad.markalive.model.Bookmark;
import com.ad.markalive.service.BookmarkService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailSenderService {
    private JavaMailSender mailSender;
    private MimeMessage mimeMessage;
    private TemplateEngine templateEngine;
    private BookmarkService bookmarkService;

    public EmailSenderService(JavaMailSender mailSender, TemplateEngine templateEngine, BookmarkService bookmarkService, MimeMessage mimeMessage) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.bookmarkService = bookmarkService;
        this.mimeMessage = mimeMessage;
    }

    public void sendEmail(String toAddress, String fromAddress, String subject, String templateName) throws MessagingException {
        final Context ctx  = new Context();
        List<Bookmark> bookmarkList = new ArrayList<>(this.bookmarkService.getAllBookmarks());
        ctx.setVariable("name", "Adithya");
        ctx.setVariable("bookmarks", bookmarkList);
        mimeMessage.setFrom(fromAddress);
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toAddress));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(this.templateEngine.process(templateName, ctx), "UTF-8", "html");
        try{
            this.mailSender.send(mimeMessage);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
