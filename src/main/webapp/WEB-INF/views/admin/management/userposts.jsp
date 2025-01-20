<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% 
    // 페이지 제목과 body 클래스 설정
    request.setAttribute("pageTitle", "사용자 게시글 목록");
    request.setAttribute("bodyClass", "user-posts");
%>

<jsp:include page="../inc/header.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/admin/management.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<h1>${user.name}님의 게시글 목록</h1>

<table border="1">
    <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성일</th>
            <th>상세보기</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="post" items="${posts}">
            <tr>
                <td>${post.bno}</td>
                <td>${post.title}</td>
                <td><fmt:formatDate value="${post.regdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/users/${user.userId}/posts/${post.bno}">
                        상세보기
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/admin/users">회원 목록으로 돌아가기</a>

<jsp:include page="../inc/footer.jsp" />
