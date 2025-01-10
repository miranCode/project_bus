package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.zerock.service.MailSender;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@Controller
public class VerifyCodeServlet extends HttpServlet {
	//이메일 인증 확인용12
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (MailSender.verifyCode(code)) {
            response.getWriter().write("success");
        } else {
            response.getWriter().write("fail");
        }
    }
}
