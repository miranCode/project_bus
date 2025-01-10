<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "사용자 로그인");
    request.setAttribute("bodyClass", "member");
%>

<jsp:include page="../inc/header.jsp" />
				<!-- 회원가입 jsp -->
            	<!-- #content 영역 시작 -->
 		<div class="login-container">
          <div class="sign-in-container">
            <form>
              <h1>로그인</h1>
              <input id="id" type="email" placeholder="Email">
              <input id="pass"type="password" placeholder="Password">
              <button class="form_btn">Sign In</button>
            </form>
          </div>
          <div class="sign-in-option">
            <div>
              <input class="auto_login" type="checkbox"> 자동 로그인
            </div>
            <div>
              <a href="/join">회원가입</a>
              <a href="#">아이디 찾기</a>
              <a href="#">비밀번호 찾기</a> 
            </div> 
          </div>
          <span id="login-span">소셜 로그인</span>
          <div class="social-links">
            <div>
              <a href="#"><i class="xi-google"></i></a>
            </div>
            <div>
              <a href="#"><i class="xi-naver"></i></a>
            </div>
            <div>
              <a href="#"><i class="xi-kakaotalk"></i></a>
            </div>
          </div>
       </div>

				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />