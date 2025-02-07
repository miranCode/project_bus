<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
request.setAttribute("pageTitle", "사용자 메인");
request.setAttribute("bodyClass", "main");
%>
<jsp:include page="./inc/header.jsp" />
<!-- #content 영역 시작 -->
    <script src="https://unpkg.com/react@17/umd/react.development.js"></script>
    <script src="https://unpkg.com/react-dom@17/umd/react-dom.development.js"></script>
    <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="/resources/css/user/index.css" />
	
	
<script src="/resources/js/user/index.js"></script>


<div class="container">
        <div class="mainbuspost">
            <div class="mainbuspost1">
                <div class="sign-post">
                    <div class="circle">
                        <div class="icon">🚍</div>
                    </div>
                </div>
            </div>
            <div class="bus-stop">
                <div class="bus-sign">🚌</div>
                <div class="bus-stop-roof">
                    <button class="btn-two purple rounded" onclick="window.location.href='/busArrival';">버스노선조회</button>
                    🚍 숭실대
                    <button class="btn-two cyan rounded" onclick="window.location.href='/busArrivalByStation';">정류장조회</button>
                </div>
                <div class="bus-stop-bench">
                    <div class="bench-leg left"></div>
                    <div class="bench-leg middle"></div>
                    <div class="bench-leg right"></div>

                </div>


                <!-- 정류장 바닥 부분 -->
                <div class="bus-stop-footer"></div>



                <div class="ra">
                    <div class="nudeBody">
                        <div class="nudeBodyllll"></div>
                        <div class="nudeBodyll"></div>
                        <div class="nudeBodycenter"></div>
                        <div class="nudeBodyrrrr"></div>
                        <div class="nudeBodyrr"></div>
                        <div class="bodys"></div>
                        <div class="bodyss"></div>
                        <div class="bodysss"></div>

                        <div class="nudeface">
                            <div class="ear left"></div>
                            <div class="ear right"></div>
                            <div class="face">
                                <div class="pade"></div>
                                <div class="eyebrow0">
                                    <div class="eyebrow left"></div>
                                    <div class="eyebrow right"></div>
                                </div>
                                <div class="eye0">
                                    <div class="eye left"></div>
                                    <div class="eye right"></div>
                                </div>
                                <div class="mouth0">
                                    <div class="mouth left"></div>
                                    <div class="mouth right"></div>
                                    <div class="mouth0"></div>
                                    <div class="nose"></div>
                                    <div class="nose1"></div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <script src="/stay/test.js"></script>

            </div>

        </div>

        <div class="mainbus">
            <div class="bus">
                <div class="bus-body">
                    <!-- 창문 -->
                    <div class="window window-1">

                    </div>
                    <div class="window window-2"></div>
                    <div class="window window-3"></div>
                    <div class="window window-4">

                        <div class="ra2">
                            <div class="nudeface">
                                <div class="ear left"></div>
                                <div class="ear right"></div>
                                <div class="face">
                                    <div class="pade"></div>
                                    <div class="eyebrow0">
                                        <div class="eyebrow left"></div>
                                        <div class="eyebrow right"></div>
                                    </div>
                                    <div class="eye0">
                                        <div class="eye left"></div>
                                        <div class="eye right"></div>
                                    </div>
                                    <div class="mouth0">
                                        <div class="mouth left"></div>
                                        <div class="mouth right"></div>
                                        <div class="mouth0"></div>
                                        <div class="nose"></div>
                                        <div class="nose1"></div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 문 -->
                    <div class="door"></div>
                </div>
                <!-- 바퀴 -->
                <div class="wheel wheel-1">
                    <div class="hubcap"></div>
                    <!-- 허브캡 -->
                </div>
                <div class="wheel wheel-2">
                    <div class="hubcap"></div>
                    <!-- 허브캡 -->
                </div>

                <div class="headlight">
                    <div class="light-beam"></div>
                    <!-- 왼쪽으로 퍼지는 불빛 추가 -->
                    <div class="light-beam left"></div>


                </div>
                <div class="tail-light right"></div>
            </div>
        </div>

        <div>

        </div>

        <div class="doro2">
        	   <div class="bodo">
    <div class="fkdls">

    <div class="fkdls1"></div>
    <div class="fkdls2"></div>
    <div class="fkdls3"></div>
    <div class="fkdls4"></div>

    <div class="middle-line"></div> <!-- 중간선 -->
    </div>

 
    <div class="dhlqn">
        <div class="dhlqn1"></div>
        <div class="dhlqn2"></div>
    </div>

  </div>
  
            
         

            </div>
          
        </div>
        

     
      
        
        <div class="tlsghemdtjs"></div> <!-- 중간선 -->


  <img src="/resources/img/1.png" alt="신호등" >
 <div class="wjdwltjs"></div>
 
    
    <jsp:include page="./inc/footer.jsp" />