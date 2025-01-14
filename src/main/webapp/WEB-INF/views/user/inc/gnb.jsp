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
                    </c:if>
               </ul>
             </div>
       </div>
        <ul>
            <li>
                <a href="/info">/qna/write</a>
            </li>
            <li>
                <a href=""></a>
            </li>
        </ul>
    </div>
</nav>
<!-- #gng 영역 끝 -->