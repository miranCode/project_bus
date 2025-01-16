<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
    request.setAttribute("pageTitle", "사용자 불편사항 접수");
    request.setAttribute("bodyClass", "write");
%>
<jsp:include page="../inc/header.jsp" />

<!-- 스타일 시트 연결 -->
<link rel="stylesheet" type="text/css" href="/resources/css/user/view.css"/>
<script type="text/javascript" src="/resources/js/user/write.js"></script>

<!-- #content 영역 시작 -->

<form name="writeFrm" method="post" action="${pageContext.request.contextPath}/qna/write">
    <fieldset>
        <h1>불편 신고 작성</h1>

        <!-- 버스 노선 선택 -->
        <dl>
            <dt><label for="rteNm">버스 노선</label></dt>
            <dd class="input-box">
                <input type="text" name="rteNm" id="rteNm" value="${ board.rteNm }" readonly/>
            </dd>
        </dl>

        <!-- 세션에서 가져온 이름 (수정 불가능) -->
        <dl>
            <dt><label for="name">이름</label></dt>
            <dd class="input-box">
                <input type="text" name="name" id="nameInput" value="${board.name}" readonly />
            </dd>
        </dl>

        <!-- 제목 -->
        <dl>
            <dt><label for="title">제목</label></dt>
            <dd class="input-box">
                <input type="text" name="title" id="titleInput" value="${ board.title }" readonly/>
            </dd>
        </dl>

        <!-- 내용 -->
        <dl>
            <dt>내용</dt>
            <dd class="input-box">
                <textarea id="contentTextarea" name="content" readonly>${ board.content }</textarea>
            </dd>
        </dl>

        <!-- 세션에서 가져온 이메일 (수정 불가능) -->
        <dl>
            <dt><label for="email">이메일</label></dt>
            <dd class="input-box">
                <input type="email" name="email" id="emailInput" value="${sessionScope.email}" readonly />
            </dd>
        </dl>

        <!-- 버튼 영역 -->
        <div class="btn-area">
            <div class="btn-group">
                <button type="button" class="line" onclick="history.back();">이전페이지</button>
            </div>
        </div>
    </fieldset>
</form>

<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />
