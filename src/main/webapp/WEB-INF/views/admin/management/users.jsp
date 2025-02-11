<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% 
    // 페이지 제목과 body 클래스 설정
    request.setAttribute("pageTitle", "회원 관리(관리자용)");
    request.setAttribute("bodyClass", "users");
%>

<jsp:include page="../inc/header.jsp" />

<link rel="stylesheet" type="text/css" href="/resources/css/admin/management.css"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<h1>회원 목록</h1>
    <table border="1">
        <thead>
            <tr>
                <th>사용자 ID</th>
                <th>사용자 이름</th>
                <th>이메일</th>
                <th>게시글 수</th>
                <th>가입 날짜</th>
                <th>활성 상태</th>
                <th>정지/해제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/users/${user.userId}/posts">${user.postCount}</a>
                    </td>
                    <td>${user.created_at }</td>
                    <td>
                    	<c:choose>
                    		<c:when test="${user.isActive}">
                    			<span class="txt-green">활성</span>
                    		</c:when>
                    		<c:otherwise>
                    			<span class="txt-red">정지</span>
                    		</c:otherwise>
                    	</c:choose>
                    
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${user.isActive}">
                                <button class="btn btn-bagic small" onclick="banUser('${user.email}')">정지</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-bagic line small"  onclick="unbanUser('${user.email}')">정지 해제</button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <script>
    function banUser(email) {
        var encodedEmail = encodeURIComponent(email);  // 이메일을 URL로 안전하게 인코딩
        $.post("${pageContext.request.contextPath}/admin/users/" + encodedEmail + "/ban", function(response) {
            alert("사용자가 정지되었습니다.");
            location.reload();
        }).fail(function(xhr, status, error) {
            alert("정지 요청 실패: " + error);
        });
    }

    function unbanUser(email) {
        var encodedEmail = encodeURIComponent(email);  // 이메일을 URL로 안전하게 인코딩
        $.post("${pageContext.request.contextPath}/admin/users/" + encodedEmail + "/unban", function(response) {
            alert("사용자 정지가 해제되었습니다.");
            location.reload();
        }).fail(function(xhr, status, error) {
            alert("정지 해제 요청 실패: " + error);
        });
    }
</script>

<jsp:include page="../inc/footer.jsp" />