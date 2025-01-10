<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% 
    request.setAttribute("pageTitle", "게시판 목록");
    request.setAttribute("bodyClass", "board");
%>

<jsp:include page="../inc/header.jsp" />

<!-- #content 영역 시작 -->
<h1>게시판 목록</h1>
<table border="1" cellpadding="10">
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>등록일</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="board" items="${boardList}">
            <tr>
                <td>${board.bno}</td>
                <td><a href="/qna/view/${board.bno}">${board.title}</a></td>
                <td>${board.name}</td>
     <td>
    <c:choose>
        <c:when test="${not empty board.regdate}">
            <fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm" />
        </c:when>
        <c:otherwise>
            N/A <!-- 날짜가 없을 때 기본값 출력 -->
        </c:otherwise>
    </c:choose>
</td>
             </tr>
        </c:forEach>
    </tbody>
</table>
<!-- #content 영역 끝 -->

<jsp:include page="../inc/footer.jsp" />
