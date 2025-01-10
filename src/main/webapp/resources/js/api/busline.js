$(document).ready(function(){
	$('#busLineApi').click(function(){
		 $.ajax({
			 url:'/bus/saveBusLine',
			 type:'GET',
			 success: function(data){
				 console.log("서버에서 받은 데이터: ", data);
			 },
			 error: function(xhr, status, error) {
				// 상태 코드와 응답 본문 출력
                console.log("상태 코드:", xhr.status);          // HTTP 상태 코드 (예: 404, 500 등)
                console.log("상태 텍스트:", xhr.statusText);    // 상태 텍스트 (예: Not Found, Internal Server Error 등)
                console.log("서버 응답 본문:", xhr.responseText); // 서버에서 반환한 응답 본문
                console.log("에러 발생:", error);               // 구체적인 에러 메시지
	         }
		 });
	});
})