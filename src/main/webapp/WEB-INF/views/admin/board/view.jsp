<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "관리자 불편사항 접수 상세보기");
    request.setAttribute("bodyClass", "board");
%>
<jsp:include page="../inc/header.jsp" />
				<script type="text/javascript" src="/resources/js/select.js"></script>
            	<!-- #content 영역 시작 -->
            
<% 
    request.setAttribute("bodyClass", "view"); 
%>


<h1>게시글 상세</h1>
<table>
    <tr>
        <td>번호</td>
        <td>${board.bno}</td>
    </tr>
    <tr>
        <td>제목</td>
        <td>${board.title}</td>
    </tr>
    <tr>
        <td>작성자</td>
        <td>${board.name}</td>
    </tr>
    <tr>
        <td>등록일</td>
        <td><fmt:formatDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm" /></td>
    </tr>
    <tr>
        <td>내용</td>
        <td>${board.content}</td>
    </tr>
</table>

    
<jsp:include page="../inc/footer.jsp" />
				<!-- #content 영역 끝 -->
