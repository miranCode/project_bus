package org.zerock.service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.stereotype.Service;

@Service
public class MailSender {
    private static String verificationCode;
    //12
    public static void sendVerificationEmail(String toEmail) throws Exception {
        Properties properties = new Properties();
        properties.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));

        String user = properties.getProperty("mail.smtp.user");
        String password = properties.getProperty("mail.smtp.password");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        verificationCode = String.valueOf((int)(Math.random() * 9000) + 1000); // 4자리 난수 생성

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("이메일 인증 코드");
        message.setText("인증 번호: " + verificationCode);

        Transport.send(message);
        System.out.println("이메일 발송 완료: " + toEmail);
    }

    public static boolean verifyCode(String inputCode) {
        return verificationCode != null && verificationCode.equals(inputCode);
    }
}
