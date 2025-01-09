function check(form){
						 let id = document.getElementById("id").value.trim();
						 let pw = document.getElementById("pw").value.trim();
						 if(id == ""){
							 alert("아이디를 입력하시오.");
							 return false;
						 }
						 if (id.indexOf(" ") !== -1) {	// 아이디에 공백이 포함되면 경고
						        alert("아이디에 공백을 포함할 수 없습니다.");
						        return false;
						 }
						 if(pw == ""){
							 alert("비밀번호를 입력하시오.");
							 return false;
						 }
						 if (pw.indexOf(" ") !== -1) {  // 비밀번호에 공백이 포함되면 경고
						        alert("비밀번호에 공백을 포함할 수 없습니다.");
						        return false;
						 }
						 
						 return true;
					}