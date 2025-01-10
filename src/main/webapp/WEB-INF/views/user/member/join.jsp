<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "사용자 회원가입");
    request.setAttribute("bodyClass", "member");
%>
<jsp:include page="../inc/header.jsp" />
				<!-- 회원가입 jsp123 -->
            	<!-- #content 영역 시작 -->
		<div class="login-container">
          <div class="sign-up-container">
            <form id="signup-form">
                <h1>회원가입</h1>
                <!-- 이름 입력 -->
                <input id="name" type="text" placeholder="Name">
                <label id="name-error" style="display: none; color: red;">이름은 숫자와 공백을 포함할 수 없습니다.</label>
              
                <!-- 이메일(아이디) 입력, 인증-->
                <div class="email-wrapper">
                  <input id="id" type="email" placeholder="Email">
                  <button type="button" class="Auth_btn" id="auth-btn">인증</button>
                </div>
                <label id="email-error" style="display: none; color: red;">유효한 이메일을 입력해주세요.</label>
                
                <div class="email-code" id="email-code" style="display: none;">
                  <input id="verification-code" type="text" placeholder="인증번호 입력">
                  <button type="button" class="Auth_btn2" id="auth-btn2">확인</button>
                </div>
                <label id="verification-error" style="display: none; color: red;">인증번호가 일치하지 않습니다.</label>
                  <label id="verification-success" style="display: none; color: green;">인증 완료되었습니다!</label>
              
                <!-- 비밀번호 입력-->
                <input id="pass" type="password" placeholder="Password">
                <label id="password-error" style="display: none; color: red;">비밀번호는 8자 이상, 숫자, 영어, 특수문자를 포함해야 하며 공백을 포함할 수 없습니다.</label>
              
                <!-- 비밀번호 재입력 -->
                <input id="confirm-pass" type="password" placeholder="Re-enter Password">
                <label id="confirm-pass-error" style="display: none; color: red;">비밀번호가 일치하지 않습니다.</label>
              
                <!-- 생년월일 입력 -->
                <input id="dob" type="text" placeholder="yyyyMMdd">
                <label id="dob-error" style="display: none; color: red;">생년월일은 숫자만 입력해야 하며, 8자까지 입력 가능합니다.</label>
              
                <button type="submit" class="form_btn">가입하기</button>
              </form>
              
            <div class="sign-up-option">
                <a href="#">home</a>
            </div>    
        </div>
        </div>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />
<script type="text/javascript" src="/resources/js/user/join.js"></script>