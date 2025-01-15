package org.zerock.service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final String host = "smtp.gmail.com"; // Gmail SMTP 서버
    private final String fromEmail = "yongcheol8912@gmail.com"; // 발신자 이메일
    private final String password = "gcrr hfoh muuz tbxu"; // 앱 비밀번호

    @Override
    public void sendEmail(String to, String subject, String text) {
        // SMTP 설정
    	Properties props = new Properties();
    	props.put("mail.smtp.host", "smtp.gmail.com");
    	props.put("mail.smtp.port", "587");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.smtp.starttls.enable", "true");
    	props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // TLS 버전 명시
    	props.put("mail.debug", "true"); // 디버깅 활성화

    	Session session = Session.getInstance(props, new Authenticator() {
    	    @Override
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication(fromEmail, password);
    	    }
    	});


        try {
            // 이메일 메시지 생성
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);

            // 이메일 발송
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("이메일 발송 실패: " + e.getMessage());
        }
    }
}
