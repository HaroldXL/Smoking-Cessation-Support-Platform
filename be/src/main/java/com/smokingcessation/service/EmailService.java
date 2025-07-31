package com.smokingcessation.service;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtpEmail(String to, String otpCode, String purpose) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject("Your OTP for " + purpose);
        helper.setText("Your OTP code of smoking cessation is: <b>" + otpCode + "</b>. It is valid for 10 minutes.", true);
        mailSender.send(message);
    }

    // === thông báo nhắc nhở hằng ngày ===
    public void sendReminderEmail(String to, String username) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("Remind to quit smoking");
            String content = "<p>Hello <b>" + username + "</b>,</p>"
                    + "<p>Remember to take note of your quitting process today and keep trying!</p>";
            helper.setText(content, true); // true để gửi email dạng HTML
            mailSender.send(message);
        } catch (MessagingException e) {
            // Có thể log lại lỗi hoặc throw lên tuỳ ý bạn
            e.printStackTrace();
        }
    }
    // === gửi nhiệm vụ checklist hàng ngày cho user ===
    public void sendDailyChecklistEmail(String to, String username, LocalDate date, List<String> tasks) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("Nhiệm vụ bỏ thuốc ngày " + date.toString());

            StringBuilder taskListHtml = new StringBuilder();
            taskListHtml.append("<ul>");
            for (String task : tasks) {
                taskListHtml.append("<li>").append(task).append("</li>");
            }
            taskListHtml.append("</ul>");

            String content = "<p>Xin chào <b>" + username + "</b>,</p>"
                    + "<p>Dưới đây là danh sách nhiệm vụ của bạn trong ngày <b>" + date.toString() + "</b>:</p>"
                    + taskListHtml
                    + "<p>Hãy hoàn thành thật tốt nhé! 💪</p>"
                    + "<p><i>Đội ngũ hỗ trợ bạn bỏ thuốc.</i></p>";

            helper.setText(content, true); // Gửi HTML
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendReminderCheckListEmail(String to, String username) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject("Remind to quit smoking");
            String content = "<p>Hello <b>" + username + "</b>,</p>"
                    + "<p>Remember to complete your task checklist today and keep trying!</p>";
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


}