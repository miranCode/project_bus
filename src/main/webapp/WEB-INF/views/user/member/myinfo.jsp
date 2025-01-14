<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "사용자 정보 페이지");
    request.setAttribute("bodyClass", "member");
%>
<jsp:include page="../inc/header.jsp" />
				<!-- 회원가입 jsp123 -->
            	<!-- #content 영역 시작 -->
		<div class="login-container">
          <div class="sign-up-container">
            <form id="signup-form">
                <h1>나의 정보</h1>
                <!-- 이름 입력 -->
                <label>
                이름 : 
                <input id="name" name="name" type="text" value={userinfo.name}>
                <!-- 이메일(아이디) 입력, 인증-->
                </label>
                <label>
                이메일(아이디) : 
                <div class="email-wrapper">
                  <input id="id" name="id" type="email" value={userinfo.email}>
                </div>
                </label>
                생년월일 : 
                <!-- 생년월일 입력 -->
                <label>
                <input id="dob" name="dob" type="text" value={userinfo.dob}>
              	</label>
              	<!-- 전화번호 입력 -->
              	<label>
              	전화번호 : 
              	<input id="phone" name="phone" type="text" value={userinfo.phone_number}>
              	</label>
              </form>
              
            <div class="sign-up-option">
                <a href="#">home</a>
            </div>    
        </div>
        </div>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />