<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
// 페이지 제목과 body 클래스 설정
request.setAttribute("pageTitle", "불편 신고 접수");
request.setAttribute("bodyClass", "list");
%>

<jsp:include page="../inc/header.jsp" />

<link rel="stylesheet" type="text/css"
	href="/resources/css/admin/list.css" />
<script type="text/javascript" src="/resources/js/admin/list.js"></script>

<a href="http://localhost:8080/qna/list">
	<h2>불편 신고 접수 현황 (관리자 용)</h2>
</a>




<div class="page-container">
	<!-- 왼쪽: Form Container -->
	<div class="form-container">
		<form action="/qna/list" method="get">
			<label for="rteNm">노선별 접수 상황 : </label> <select id="rteNm"
				name="rteNm" required>
				<option value="">선택하세요</option>
				<c:forEach var="bus" items="${busnumList}">
					<option value="${bus.rteNm}"
						${bus.rteNm == rteNm ? 'selected' : ''}>${bus.rteNm}</option>
				</c:forEach>
			</select>
			<button type="submit" style="display: none;">확인</button>
		</form>
		<p class="result-text" id="resultText"></p>
		<c:if test="${not empty count}">
			<p class="result-text">'${rteNm}'번 불편신고 접수량: ${count}</p>
		</c:if>
	</div>

	<!-- 오른쪽: Count Container -->
	<div class="count-container">

		<span>미처리 게시글 수:</span>
		<button class="count-circle"
			onclick="location.href='/qna/list?status=미처리'">${unprocessedCount}</button>


		<span>처리중 게시글 수:</span>
		<button class="count-circle"
			onclick="location.href='/qna/list?status=처리중'">${processedCount}</button>



		<span>완료 게시글 수:</span>
		<button class="count-circle"
			onclick="location.href='/qna/list?status=완료'">${completedCount}</button>


	</div>
</div>

<table border="1" cellpadding="10">
	<thead>
		<tr>
		<tr>
			<th>
				<!-- 처리상태 클릭 시 정렬 기준과 방향을 바꿔서 정렬 --> <a
				href="?sort=bno&order=${order == 'asc' ? 'desc' : 'asc'}"> 번호 <c:if
						test="${order == 'asc'}"></c:if> <c:if test="${order == 'desc'}"></c:if>
			</a>
			</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>
				<!-- 처리상태 클릭 시 정렬 기준과 방향을 바꿔서 정렬 --> <a
				href="?sort=status&order=${order == 'asc' ? 'desc' : 'asc'}">
					처리상태 <c:if test="${order == 'asc'}">↑</c:if> <c:if
						test="${order == 'desc'}">↓</c:if>
			</a>
			</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${boardList}">
			<tr id="board-${board.bno}">
				<!-- 각 게시글에 id 추가 -->
				<td>${board.bno}</td>
				<td><a href="/qna/view/${board.bno}">${board.title}</a></td>
				<td>${board.name}</td>
				<td><c:choose>
						<c:when test="${not empty board.regdate}">
							<fmt:formatDate value="${board.regdate}"
								pattern="yyyy-MM-dd HH:mm" />
						</c:when>
						<c:otherwise>
                            N/A
                        </c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${board.status == '처리중'}">
							<span style="color: blue;">처리중</span>
						</c:when>
						<c:when test="${board.status == '완료'}">
							<span style="color: green;">완료</span>
						</c:when>
						<c:otherwise>
							<span style="color: red;">미처리</span>
						</c:otherwise>
					</c:choose></td>
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
	<c:forEach begin="${pageDTO.startPage}" end="${pageDTO.endPage}"
		var="page">
		<c:choose>
			<c:when test="${page == pageDTO.page}">
				<span class="current-page">${page}</span>
			</c:when>
			<c:otherwise>

				<a href="?page=${page}&sort=${param.sort}&order=${param.order}">${page}</a>

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
