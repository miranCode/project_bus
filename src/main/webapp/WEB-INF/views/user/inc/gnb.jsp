<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- #gng 영역 시작 -->
<nav id="gnb">
    <div>
    	<div>
             <div class="utill">
				<ul>
                	<c:if test="${empty uname}">
                    	<li><a href="/member/login">LOGIN</a></li>
                        <li><a href="/member/join">JOIN</a></li>
                    </c:if>
                    <c:if test="${not empty uname}">
                    	<li><a href="/member/logout">LOGOUT</a></li>
                    	<li><a href="/member/myinfo">나의 정보</a>
                    </c:if>
               </ul>
             </div>
       </div>
        <ul class="menu">
        	<li><a href="/">home</a></li>
            <li><a href="/busArrival">버스노선조회</a></li>
            <li><a href="/busArrivalByStation">정류장조회</a></li>
            <li><a href="/qna/write">버스 불편사항 접수하기</a></li>
            <c:if test="${not empty uname}">
	            <li>
	                <a href="/member/list">나의 불편사항 접수 현황</a>
	            </li>
            </c:if>
        </ul>
    </div>
</nav>
<!-- #gng 영역 끝 -->