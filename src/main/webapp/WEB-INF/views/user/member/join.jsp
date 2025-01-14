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
            <form id="signup-form" action="/member/join" method="post">
                <h1>회원가입</h1>
                <!-- 이름 입력 -->
                <input id="name" name="name" type="text" placeholder="Name">
                <label id="name-error" style="display: none; color: red;">이름은 숫자와 공백을 포함할 수 없습니다.</label>
              	
              	<input id="id" name="id" type="text" placeholder="Id">
              	<label id="id-error" style="display: none; color: red;">잘못된 아이디 양식입니다.</label>
                <!-- 이메일(아이디) 입력, 인증-->
                <div class="email-wrapper">
                  <input id="email" name="email" type="email" placeholder="Email">
                  <button type="button" class="Auth_btn" id="auth-btn">인증</button>
                </div>
                <label id="email-error" style="display: none; color: red;">유효한 이메일을 입력해주세요.</label>
                
                <div class="email-code" id="email-code" style="display: none;">
                  <input id="verification-code" type="text" placeholder="인증번호 입력">
				  <div id="timer"></div> <!-- 타이머 -->
                  <button type="button" class="Auth_btn2" id="auth-btn2">확인</button>
                </div>
                <label id="verification-timeout" style="display: none; color: red;">인증 시간이 만료되었습니다. 재전송 버튼을 눌러주세요.</label>
                <label id="verification-error" style="display: none; color: red;">인증번호가 일치하지 않습니다.</label>
                <label id="verification-success" style="display: none; color: green;">인증 완료되었습니다!</label>
              	<label id="email-duplicate-error" style="display: none; color: red;">이미 존재하는 이메일입니다.</label>
                <!-- 비밀번호 입력-->
                <input id="pass" name="pass" type="password" placeholder="Password">
                <label id="password-error" style="display: none; color: red;">비밀번호는 8자 이상, 숫자, 영어, 특수문자를 포함해야 하며 공백을 포함할 수 없습니다.</label>
              
                <!-- 비밀번호 재입력 -->
                <input id="confirm-pass" type="password" placeholder="Re-enter Password">
                <label id="confirm-pass-error" style="display: none; color: red;">비밀번호가 일치하지 않습니다.</label>
              
                <!-- 생년월일 입력 -->
                <input id="dob" name="dob" type="text" placeholder="yyyyMMdd">
                <label id="dob-error" style="display: none; color: red;">생년월일은 숫자만 입력해야 하며, 8자까지 입력 가능합니다.</label>
              	
              	<!-- 전화번호 입력 -->
              	<input id="phone" name="phone_number" type="text" placeholder="Phone Number">
				<label id="phone-error" style="display: none; color: red;">유효한 전화번호를 입력해주세요 (숫자만 입력).</label>
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