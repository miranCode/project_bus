<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% 
    // 페이지 제목과 body 클래스 설정
    request.setAttribute("pageTitle", "불편 신고 접수");
    request.setAttribute("bodyClass", "list");
%>

<jsp:include page="../inc/header.jsp" />

<!-- <link rel="stylesheet" type="text/css" href="/resources/css/admin/list.css"/> -->
<script type="text/javascript" src="/resources/js/admin/list.js"></script>

<table border="1" cellpadding="10">
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>등록일</th>
            <th>처리상태</th> 
        </tr>
    </thead>
    <tbody>
        <c:if test="${not empty boardList}">
    <c:forEach var="board" items="${boardList}">
        <tr id="board-${board.bno}">
            <td>${board.bno}</td>
            <td><a href="/member/view/${board.bno}">${board.title}</a></td>
            <td>${board.name}</td>
            <td>
                <c:choose>
                    <c:when test="${not empty board.regdate}">
                        <fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm" />
                    </c:when>
                    <c:otherwise>
                        N/A
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${board.status == '처리중'}">
                        <span style="color: blue;">처리중</span>
                    </c:when>
                    <c:when test="${board.status == '완료'}">
                        <span style="color: green;">완료</span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: red;">미처리</span>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</c:if>

<c:if test="${empty boardList}">
    <tr><td colspan="5">게시글이 없습니다.</td></tr>
</c:if>

</tbody>
</table>

<!-- 페이징 영역 -->







<jsp:include page="../inc/footer.jsp" />
