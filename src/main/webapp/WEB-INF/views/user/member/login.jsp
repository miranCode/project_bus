<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "사용자 로그인");
    request.setAttribute("bodyClass", "member login");
%>

<jsp:include page="../inc/header.jsp" />
				<!-- 회원가입 jsp12 -->
            	<!-- #content 영역 시작 -->
    
 		<div class="login-container">
          <div class="sign-in-container">
            <form id="login-form" action="/member/login" method="post" >
              <h1>로그인</h1>
              <input name= "id" id="id" type="text" placeholder="Id">
              <input name="pass" id="pass"type="password" placeholder="Password">
              <button type="submit" class="form_btn">Sign In</button>
            </form>
          </div>
          <div class="sign-in-option">
            <div>
              <input id="auto" name="auto" class="auto_login" type="checkbox" /> <label for="auto">자동 로그인</label>
            </div>
            <div>
              <a href="join">회원가입</a>
              <a href="#">아이디 찾기</a>
              <a href="#">비밀번호 찾기</a> 
            </div> 
          </div>
          <span id="login-span">소셜 로그인</span>
          <div class="social-links">
            <div>
              <a href="/google/login"><i class="xi-google"></i></a>
            </div>
            <div>
              <a href="/naver/login"><i class="xi-naver"></i></a>
            </div>
            <div>
              <a href="https://kauth.kakao.com/oauth/authorize?client_id=558503d359c98d21606dc6d90c6795aa&redirect_uri=http://localhost:8080/auth/kakao/callback&response_type=code"><i class="xi-kakaotalk"></i></a>
            </div>
          </div>
       </div>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />
<script type="text/javascript" src="/resources/js/user/login.js"></script>