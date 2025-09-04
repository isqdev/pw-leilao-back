package com.isaque.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.standard.expression.MessageExpression;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javamail;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Async
    public void enviarEmailSimples(String para, String assunto, String mensagem) {
        SimpleMailMessage simpleMail = new SimpleMailMessage();
        simpleMail.setTo(para);
        simpleMail.setSubject(assunto);
        simpleMail.setText(mensagem);
        javamail.send(simpleMail);
    }

    @Async
    public void emailTemplate(String para, String assunto, Context variaveisEmail, String arquivoTemplate) {

        String process = templateEngine.process(arquivoTemplate, variaveisEmail);
        MimeMessage message = javamail.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(process, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javamail.send(message);
    }
}
