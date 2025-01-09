<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% 
	request.setAttribute("pageTitle", "사용자 불편사항 접수");
    request.setAttribute("bodyClass", "board");
%>
<jsp:include page="../inc/header.jsp" />
				<script type="text/javascript" src="/resources/js/select.js"></script>
            	<!-- #content 영역 시작 -->
            	
				<p class="page-tit">Q&A</p>
	<div id="content">        
	    <div class="">
	        <form name="writeFrm" method="post" enctype="multipart/form-data" action="/qna/write">
	            <fieldset>
	                <dl>
	                    <dt><label for="">이름</label></dt>
	                    <dd class="input-box">
	                    	<input type="text" name="name" id="name" required>
	                    </dd>
	                </dl>
	                <dl>
	                    <dt><label for="">이메일</label></dt>
	                    <dd class="input-box">
	                    	<input type="text" name="email" id="email" required>
	                    </dd>
	                </dl>
	                <dl>
	                    <dt><label for="">비밀번호</label></dt>
	                    <dd class="input-box">
	                    	<input type="password" name="pass" id="pass" required>
	                    </dd>
	                </dl>
	                <dl>
	                    <dt><label for="">제목</label></dt>
	                    <dd class="input-box">
	                    	<input type="text" name="title" id="title" required>
	                    </dd>
	                </dl>
	                <dl>
	                    <dt>내용</dt>
	                    <dd class="input-box">
	                    	<textarea id="content" name="content" required></textarea>
	                    </dd>
	                </dl>
	                <div class="btn-area three">
	                    <button type="reset" class="red-line">Reset</button>
	                    <a href="/qna/List" class="line">List</a>
	                    <button type="submit">Write</button>
	                </div>
	            </fieldset>
	        </form>
	    </div>
	</div>
				<!-- #content 영역 끝 -->
<jsp:include page="../inc/footer.jsp" />