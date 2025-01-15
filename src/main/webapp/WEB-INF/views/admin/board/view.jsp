<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    request.setAttribute("pageTitle", "관리자 불편사항 접수 상세보기");
    request.setAttribute("bodyClass", "board");
%>
<jsp:include page="../inc/header.jsp" />

<script type="text/javascript" src="/resources/js/select.js"></script>

<link rel="stylesheet" type="text/css" href="/resources/css/admin/view.css"/>

<h1>${board.name} 님 건의 사항</h1>
<form method="post" action="/qna/updateStatus/${board.bno}">

<tr>
    <td>노선</td>
    <td>${board.rteNm}</td>
</tr>
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
      
        <tr>
            <td>특이사항</td>
            <td>
                <textarea name="memo" rows="5" cols="50">${board.memo}</textarea>
            </td>
        </tr>
        
        <tr>
         	<td>처리 상태</td>
            <td>
                <select name="status">
                    <option value="미처리" ${board.status == '미처리' ? 'selected' : ''}>미처리</option>
                    <option value="처리중" ${board.status == '처리중' ? 'selected' : ''}>처리중</option>
                    <option value="완료" ${board.status == '완료' ? 'selected' : ''}>완료</option>
                </select>
            </td>
        </tr>
        
        
    </table>
    <button type="submit">수정</button>
</form>

<jsp:include page="../inc/footer.jsp" />
