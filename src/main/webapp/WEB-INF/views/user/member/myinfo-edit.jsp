<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
    request.setAttribute("pageTitle", "사용자 정보 페이지");
    request.setAttribute("bodyClass", "member");
%>
<jsp:include page="../inc/header.jsp" />
<!-- #content 영역 시작 -->
<div class="login-container">
    <h2 id="myinfo-title">나의 정보</h2>
    <div class="myinfo-container">
        <form id="myinfo-form" action="/member/update" method="post">
            <!-- 이름 (수정 불가능, 읽기 전용) -->
            <div id="myinfo">
                <i class="xi-user-o"></i> 이름 : ${uname}
            </div>
            
            <!-- 아이디 (수정 불가능, 읽기 전용) -->
            <div id="myinfo">
                <i class="xi-user-o"></i> 아이디 : ${id}
            </div>
            
            <!-- 이메일 (수정 가능) -->
            <div id="myinfo">
                <i class="xi-mail-o"></i> 이메일 : ${ email }
            </div>
            
            <!-- 생년월일 (수정 가능) -->
            <div id="myinfo">
                <i class="xi-calendar"></i> 생년월일 : 
                <input type="text" name="dob" value="${not empty dob ? dob : ''}" placeholder="yyyyMMdd" />
            </div>
            
            <!-- 전화번호 (수정 가능) -->
            <div id="myinfo">
                <i class="xi-call"></i> 전화번호 : 
                <input type="text" name="phone_number" value="${not empty phone_number ? phone_number : ''}" placeholder="000-0000-0000" />
            </div>
            
            <!-- 수정 완료 버튼 -->
            <button type="submit">수정 완료</button>
        </form>
        
        <!-- 홈으로 돌아가기 -->
        <div class="sign-up-option">
            <a href="/">home</a>
        </div>    
    </div>
</div>
<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />
