package com.mackenzie.cif.person.common;

import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
public class SendEmail {

    public static void sendSimpleEmail(String emailTo, String body){
        String from = "aplicacaocif@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                log.info("Authenticating email");
                return new PasswordAuthentication("aplicacaocif@gmail.com", "MackenzieCIF!");
            }
        });

        log.info("Email authenticated!!");

        try {
            log.info("Configuring email!!");
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject("Cadastro Aplicação CIF");
            message.setText(body);

            log.info("Sending email!!");
            Transport.send(message);
            log.info("Email sent successfully!!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
