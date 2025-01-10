<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 로그인");
    request.setAttribute("bodyClass", "member");
%>
<jsp:include page="../inc/header.jsp" />
            	<!-- #content 영역 시작 -->
				<div id="content">
					<div id="login_box">
						<form action="/admin/login" method="post" onsubmit="return check(this);">
							<div id="id_box">
								<label for="id">ID:&nbsp;</label>
								<div id="id_input">
									<input type="text" id="id" name="id" placeholder="아이디를 입력하십시오.">
								</div>
							</div>
							<div id="pw_box">
								<label for="pw">PW:&nbsp;</label>
								<div id="pw_input">
									<input type="password" id="pw" name="pw" placeholder="비밀번호를 입력하십시오.">
								</div>
							</div>
							<div id="button_box_01">
								<button id="loginbtn" type="submit">Login</button>
							</div>
						</form>
					</div>
				</div>
				<script src="/resources/js/admin/login.js"></script>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />