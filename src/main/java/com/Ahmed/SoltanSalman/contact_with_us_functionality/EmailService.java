package com.Ahmed.SoltanSalman.contact_with_us_functionality;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendComplaintConfirmation(String toEmail, String userName) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("شكوتك تحت المراجعة");
            helper.setText(
                    "مرحبًا " + userName + "،\n\n" +
                            "لقد استلمنا شكواك، وجاري مراجعتها من قبل الفريق المختص " +
                            "في شركتنا للمقاولات العامه.\n" +
                            "سنقوم بالرد عليك في أقرب وقت ممكن.\n\n" +
                            "شكرًا لتواصلك معنا.\n\n" +
                            "فريق الدعم",
                    false
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("فشل إرسال الإيميل: " + e.getMessage());
        }
    }

    public void sendComplaintToReceiverEmail(String msg) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo("3li.brol91@gmail.com");
            helper.setSubject("User Send Message");
            helper.setText(
                    msg,
                    false
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("فشل إرسال الإيميل: " + e.getMessage());
        }
    }
}