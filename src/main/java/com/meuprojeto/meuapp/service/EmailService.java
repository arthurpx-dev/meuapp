package com.meuprojeto.meuapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void envioEmailAlteraSenha(String toEmail, String resetLink) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(toEmail);
            helper.setSubject("Redefinição de Senha");

            String htmlContent = """
                        <html>
                            <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                                <h2 style="color: #333;">Solicitação de Redefinição de Senha</h2>
                                <p>Olá,</p>
                                <p>Recebemos uma solicitação para redefinir sua senha. Para prosseguir com a redefinição, clique no link abaixo:</p>
                                <a href="%s" style="display: inline-block; margin: 10px 0; padding: 10px 20px; background-color: #4285f4; color: white; text-decoration: none; border-radius: 5px;">Redefinir Senha</a>
                                <p>Se você não solicitou a redefinição, ignore este e-mail.</p>
                                <p>Atenciosamente,<br>Teste</p>
                            </body>
                        </html>
                    """
                    .formatted(resetLink);

            helper.setText(htmlContent, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail de redefinição de senha", e);
        }
    }

}
