package io.github.marcoantoniossilva.assets_manager.domain.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void send(String email, String text, String subject) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setText(text);
    message.setTo(email);
    message.setSubject(subject);
    message.setFrom("assets-manager@noreply.com");

    try {
      mailSender.send(message);
    } catch (Exception e) {
      LOGGER.error(String.format("Ocorreu um erro ao enviar o email!%n%s%n%s", e.getMessage(), e.getMessage()));
    }

  }

}
