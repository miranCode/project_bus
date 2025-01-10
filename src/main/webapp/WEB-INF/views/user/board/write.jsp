<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
    request.setAttribute("pageTitle", "사용자 불편사항 접수");
    request.setAttribute("bodyClass", "board");
%>
<jsp:include page="../inc/header.jsp" />

<!-- #content 영역 시작 -->
<form name="writeFrm" method="post" action="${pageContext.request.contextPath}/qna/write">
    <fieldset>
        <dl>
            <dt><label for="name">이름</label></dt>
            <dd class="input-box">
                <input type="text" name="name" id="name" required>
            </dd>
        </dl>
        <dl>
            <dt><label for="title">제목</label></dt>
            <dd class="input-box">
                <input type="text" name="title" id="title" required>
            </dd>
        </dl>
        <dl>
            <dt>내용</dt>
            <dd class="input-box">
                <textarea id="content" name="content" required></textarea>
            </dd>
        </dl>
        <!-- 이메일 입력 필드 추가 -->
        <dl>
            <dt><label for="email">이메일</label></dt>
            <dd class="input-box">
                <input type="email" name="email" id="email" required>
            </dd>
        </dl>
        <div class="btn-area">
            <button type="reset">초기화</button>
            <a href="/" class="line">뒤로가기</a>
            <button type="submit">작성</button>
        </div>
    </fieldset>
</form>

<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />
