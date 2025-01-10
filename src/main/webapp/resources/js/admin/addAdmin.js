function validCheck(form){
						 let id = document.getElementById("id").value.trim();
						 let pw = document.getElementById("pw").value.trim();
						 let idRegex = /^[a-zA-Z0-9]{5,15}$/;
						 let pwRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+={}\[\]:;"'<>,.?/\\|]).{8,20}$/;
						 if(id == ""){
							 alert("아이디를 입력하시오.");
							 return false;
						 }
						 if (!idRegex.test(id)) {
					         alert("아이디는 영문 대소문자와 숫자만 포함할 수 있으며, 최소 5자 부터 최대 15자까지 가능합니다.");
					         return false;
					     }
						 if(id.length > 15){
							 alert("아이디의 길이는 15자를 초과할 수 없습니다.")
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
						 if (!pwRegex.test(pw)) {
					         alert("비밀번호는 영문 대소문자, 숫자, 특수문자를 포함해야 하며, 8~20자 사이여야 합니다.");
					         return false;
					     }
						 if(pw.length > 20){
							 alert("비밀번호의 길이는 20자를 초과할 수 없습니다.")
							 return false;
						 }
						 if (pw.indexOf(" ") !== -1) {  // 비밀번호에 공백이 포함되면 경고
					         alert("비밀번호에 공백을 포함할 수 없습니다.");
					         return false;
						 }
						 
						 return true;
					}