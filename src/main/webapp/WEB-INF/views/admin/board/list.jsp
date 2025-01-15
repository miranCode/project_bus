<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<% 
    // 페이지 제목과 body 클래스 설정
    request.setAttribute("pageTitle", "불편 신고 접수");
    request.setAttribute("bodyClass", "list");
%>

<jsp:include page="../inc/header.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/admin/list.css"/>
<script type="text/javascript" src="/resources/js/admin/list.js"></script>

<h2>불편 신고 접수 현황 (관리자 용)</h2>

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
        <c:forEach var="board" items="${boardList}">
            <tr id="board-${board.bno}"> <!-- 각 게시글에 id 추가 -->
                <td>${board.bno}</td>
                <td><a href="/qna/view/${board.bno}">${board.title}</a></td>
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
    </tbody>
</table>

<!-- 페이징 영역 -->
<div class="pagination">
    <!-- Previous Page Link -->
    <c:if test="${pageDTO.page > 1}">
        <a href="?page=${pageDTO.page - 1}" class="prev">이전</a>
    </c:if>
    <c:if test="${pageDTO.page <= 1}">
        <span class="disabled prev">이전</span>
    </c:if>

    <!-- Page Number Links -->
    <c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}" var="page">
        <c:choose>
            <c:when test="${page == pageDTO.page}">
                <span class="current-page">${page}</span>
            </c:when>
            <c:otherwise>
                <a href="?page=${page}">${page}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <!-- Next Page Link -->
    <c:if test="${pageDTO.page < pageDTO.totalPages}">
        <a href="?page=${pageDTO.page + 1}" class="next">다음</a>
    </c:if>
    <c:if test="${pageDTO.page >= pageDTO.totalPages}">
        <span class="disabled next">다음</span>
    </c:if>
</div>






<jsp:include page="../inc/footer.jsp" />
