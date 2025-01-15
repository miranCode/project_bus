<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
    request.setAttribute("pageTitle", "사용자 불편사항 접수");
    request.setAttribute("bodyClass", "write");
%>
<jsp:include page="../inc/header.jsp" />

<!-- 스타일 시트 연결 -->
<link rel="stylesheet" type="text/css" href="/resources/css/user/write.css"/>
<script type="text/javascript" src="/resources/js/user/write.js"></script>
<!-- #content 영역 시작 -->

<form name="writeFrm" method="post" action="${pageContext.request.contextPath}/qna/write">
    <fieldset>
        <h1>불편 신고 작성</h1>
        <dl>
            <dt><label for="rteNm">버스 노선</label></dt>
            <dd class="input-box">
                <select id="rteNm" name="rteNm" required>
                    <option value="">선택하세요</option>
                    <c:forEach var="bus" items="${busnumList}">
                        <option value="${bus.rteNm}">${bus.rteNm}</option>
                    </c:forEach>
                </select>
            </dd>
        </dl>

        <dl>
            <dt><label for="name">이름</label></dt>
            <dd class="input-box">
                <input type="text" name="name" id="nameInput" required>
            </dd>
        </dl>

        <dl>
            <dt><label for="title">제목</label></dt>
            <dd class="input-box">
                <input type="text" name="title" id="titleInput" required>
            </dd>
        </dl>

        <dl>
            <dt>내용</dt>
            <dd class="input-box">
                <textarea id="contentTextarea" name="content" required></textarea>
            </dd>
        </dl>

        <dl>
            <dt><label for="email">이메일</label></dt>
            <dd class="input-box">
                <input type="email" name="email" id="emailInput" required>
            </dd>
        </dl>

        <div class="btn-area">
            <button type="submit" id="submitBtn">건의 하기</button>  
            <div class="btn-group">  <!-- 나란히 배치할 버튼 그룹 -->
                <button type="reset" id="resetBtn">다시 작성</button>
                <button class="line" id="backLink">이전페이지</button>
            </div>
        </div>
    </fieldset>
</form>







<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />
