package com.ad.markalive.service.email;

import com.ad.markalive.model.Bookmark;
import com.ad.markalive.repository.BookmarkRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmailSenderService {
    private JavaMailSender mailSender;
    private TemplateEngine templateEngine;
    private BookmarkRepository bookmarkRepository;

    public EmailSenderService(JavaMailSender mailSender, TemplateEngine templateEngine, BookmarkRepository bookmarkRepository) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.bookmarkRepository = bookmarkRepository;
    }

    public void sendEmail(String toAddress, String fromAddress, String subject) throws MessagingException {
        SimpleMailMessage templateMessage = new SimpleMailMessage();
        final Context ctx  = new Context();
        List<Bookmark> bookmarkList = new ArrayList<>();
        this.bookmarkRepository.findAll().forEach(bookmarkList::add);
        ctx.setVariable("name", "Adithya");
        ctx.setVariable("bookmarks", bookmarkList);
        final MimeMessage mimeMessage= this.mailSender.createMimeMessage();
        final MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage, false, "UTF-8");
        mimeMessageHelper.setFrom(fromAddress);
        mimeMessageHelper.setTo(toAddress);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(this.templateEngine.process("bookmark-list.html", ctx), true);
        try{
            this.mailSender.send(mimeMessage);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }

    }
}
