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
               <h2 id="myinfo-title">나의 정보</h2>
          <div class="myinfo-container">
            <form id="myinfo-form">
                <div id="myinfo">
                <i class="xi-user-o"></i> 이름 : <c:if test="${not empty uname}"> ${ uname } </c:if> <c:if test="${empty uname}"> 미등록 </c:if> 
                </div>
                <div id="myinfo">
                <i class="xi-user-o"></i> 아이디 : <c:if test="${not empty id}"> ${ id } </c:if> <c:if test="${empty id}"> 미등록 </c:if>
                </div>
                <div id="myinfo">
               	<i class="xi-mail-o"></i> 이메일 : <c:if test="${not empty email}"> ${ email } </c:if> <c:if test="${empty email}"> 미등록 </c:if>
              	</div>
                <div id="myinfo">
                <i class="xi-calendar"></i> 생년월일 : <c:if test="${not empty dob}"> ${ dob } </c:if> <c:if test="${empty dob}"> 미등록 </c:if>
                </div>
              	<div id="myinfo">
              	<i class="xi-call"></i> 전화번호 : <c:if test="${not empty phone_number}"> ${ phone_number } </c:if> <c:if test="${empty phone_number}"> 미등록 </c:if>
              	</div>
              </form>
              <a href="myinfo-edit" >내 정보 수정</a>
            <div class="sign-up-option">
                <a href="/">home</a>
            </div>    
        </div>
        </div>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />