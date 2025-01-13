package org.zerock.service;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.mapper.MemberMapper;
import org.zerock.service.EncryptionUtil;

@Service
public class MailSender {

    private static String verificationCode;
    
    @Autowired
    private MemberMapper memberMapper;  // DB 접근을 위한 의존성 주입

    public  void sendVerificationEmail(String toEmail) throws Exception {
        Properties properties = new Properties();
        properties.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));
        
        String user = properties.getProperty("mail.smtp.user");
        System.out.println(user);
        
        // 🔐 DB에서 암호화된 비밀번호 가져와 복호화
        String encryptedPassword = memberMapper.getEncryptedPassword(user);
        String password = EncryptionUtil.decrypt(encryptedPassword); // 복호화 메서드 사용

        // SMTP 인증 세션 설정
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        // 4자리 인증 코드 생성
        verificationCode = String.valueOf((int)(Math.random() * 9000) + 1000);

        // 이메일 작성 및 발송
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("이메일 인증 코드");
        message.setText("인증 번호: " + verificationCode);

        Transport.send(message);
        System.out.println("이메일 발송 완료: " + toEmail);
    }

    // 인증 코드 검증
    public static boolean verifyCode(String inputCode) {
        return verificationCode != null && verificationCode.equals(inputCode);
    }
}
