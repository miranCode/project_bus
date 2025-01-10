package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.zerock.service.MailSender;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@Controller
public class EmailVerificationServlet extends HttpServlet {
	//이메일 전송 확인용1
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        try {
            MailSender.sendVerificationEmail(email);
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("fail");
        }
    }
}
