<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- #gng 영역 시작 -->
<nav id="gnb">
    <div>
        <ul>
        	<li>
                <a href="/admin/"><i class="xi-home"></i><span>main</span></a>
            </li>
            <li>
                <a href="/bus/busLine"><i class="xi-bus"></i><span>BusAPI</span></a>
                <ul>
                	<li><a href="/bus/busLine"><i class=""></i><span>busLine</span></a></li>
                	<li><a href="/bus/busUse"><i class=""></i><span>busUse</span></a></li>
                	<li><a href="/bus/busUseTime"><i class=""></i><span>busUseTime</span></a></li>
                </ul>
            </li>
            <li>
                <a href="/admin/"><i class="xi-users"></i><span>회원관리</span></a>
            </li>
            <li>
                <a href="/qna/list"><i class="xi-bars"></i><span>불편사항신고</span></a>
            </li>
            <li>
                <a href="/admin/manageAccount"><i class="xi-cog"></i><span>관리자</span></a>
            </li>
        </ul>
    </div>
</nav>
<!-- #gng 영역 끝 -->