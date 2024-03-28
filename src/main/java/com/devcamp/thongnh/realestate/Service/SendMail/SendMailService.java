package com.devcamp.thongnh.realestate.Service.SendMail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;
@Service
public class SendMailService {
 public void sendMail(String paramText) {
        final String username = "thong3012springmvc@gmail.com";
        final String appPassword = "soqf yogn zwqk svcg";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, appPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("c1190036@gmail.com"));
            message.setSubject("Thông báo RealEstate");
            if(paramText.isBlank()){
                message.setText("Yêu cầu của bạn không được duyệt. Nguyên nhân cho việc này không được các quản trị viên nêu ra nhưng có thể bạn đã vi phạm tiêu chuẩn của chúng tôi.");
            }
            if(!paramText.isBlank()){
                message.setContent(paramText, "text/html");
            }
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
