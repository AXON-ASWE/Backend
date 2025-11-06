package com.swe.project.services.mailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Getter
public abstract class MailTemplate {

    protected final String fromEmail;
    protected final String subject;
    protected final String toUser;
    protected final JavaMailSender mailSender;

    public MailTemplate(String fromEmail, String subject, String toUser, JavaMailSender mailSender) {
        this.fromEmail = fromEmail;
        this.subject = subject;
        this.toUser = toUser;
        this.mailSender = mailSender;
    }

    public abstract String generateContent();

    public void sendEmail(String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(toUser);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setFrom(fromEmail);
        mailSender.send(message);
    }

}
