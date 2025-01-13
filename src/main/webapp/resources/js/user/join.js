document.getElementById("name").addEventListener("blur", function() {
  const name = this.value;
  const nameRegex = /^[A-Za-z가-힣]+$/;  // 한글과 영어만 허용
  if (!nameRegex.test(name)) {
    document.getElementById("name-error").style.display = 'inline';
  } else {
    document.getElementById("name-error").style.display = 'none';
  }
});

document.getElementById("id").addEventListener("blur", function() {
  const email = this.value;
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;  // 이메일 형식12
  if (!emailRegex.test(email)) {
    document.getElementById("email-error").style.display = 'inline';
  } else {
    document.getElementById("email-error").style.display = 'none';
  }
});

document.getElementById("pass").addEventListener("blur", function() {
  const password = this.value;
  const passwordRegex = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[^\s]{8,}$/;
  if (!passwordRegex.test(password)) {
    document.getElementById("password-error").style.display = 'inline';
  } else {
    document.getElementById("password-error").style.display = 'none';
  }
});

document.getElementById("confirm-pass").addEventListener("blur", function() {
  const confirmPassword = this.value;
  const password = document.getElementById("pass").value;
  if (password !== confirmPassword) {
    document.getElementById("confirm-pass-error").style.display = 'inline';
  } else {
    document.getElementById("confirm-pass-error").style.display = 'none';
  }
});

document.getElementById("dob").addEventListener("blur", function() {
  const dob = this.value;
  const dobRegex = /^\d{8}$/;
  if (!dobRegex.test(dob)) {
    document.getElementById("dob-error").style.display = 'inline';
  } else {
    document.getElementById("dob-error").style.display = 'none';
  }
});

let timer; // 타이머 변수
let timeLeft = 300; // 타이머를 5분(300초)로 설정

document.getElementById("auth-btn").addEventListener("click", function () {
    const email = document.getElementById("id").value;
    
    // 이메일 중복 확인 후 인증 전송
    fetch("/member/checkDuplicateEmail", {  // 경로 수정
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `email=${encodeURIComponent(email)}`
    }).then(response => response.text())
    .then(data => {
        if (data === "available") {  // "success" 대신 "available"
            // 인증번호 발송 요청
            fetch("/member/sendVerification", {  // 경로 수정
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `email=${encodeURIComponent(email)}`
            }).then(response => response.text())
            .then(data => {
                if (data === "success") {
                    alert("인증번호가 이메일로 발송되었습니다.");
                    
                    // 버튼 텍스트 변경 및 타이머 시작
                    document.getElementById("auth-btn").textContent = "인증 재전송";
                    startTimer(); // 타이머 시작
                    
                    const emailCodeDiv = document.getElementById("email-code");
                    emailCodeDiv.style.display = "flex";
                } else {
                    alert("이메일 전송에 실패했습니다.");
                }
            });
        } else if (data === "duplicate") {
            alert("이미 존재하는 이메일입니다.");
            document.getElementById("email-code").style.display = "none";
        }
    });
});
function startTimer() {
    // 타이머 5분 설정
    timer = setInterval(function () {
        if (timeLeft <= 0) {
            clearInterval(timer);
            document.getElementById("email-code").style.display = "none"; // 인증번호 입력란 숨기기
            document.getElementById("verification-error").style.display = "none"; // 기존 오류 메시지 숨기기
            document.getElementById("verification-success").style.display = "none"; // 기존 성공 메시지 숨기기
            document.getElementById("verification-timeout").style.display = "block"; // 인증 시간 만료 메시지 보이기
            document.getElementById("auth-btn").textContent = "인증 재전송"; // 버튼 텍스트 변경
        } else {
            let minutes = Math.floor(timeLeft / 60);
            let seconds = timeLeft % 60;
            document.getElementById("timer").textContent = `${minutes}:${seconds < 10 ? '0' + seconds : seconds}`;
            timeLeft--;
        }
    }, 1000); // 1초마다 타이머 갱신
}

document.getElementById("auth-btn2").addEventListener("click", function () {
	 const code = document.getElementById("verification-code").value;
	    fetch("/verifyCode", {
	        method: "POST",
	        headers: {
	            "Content-Type": "application/x-www-form-urlencoded"
	        },
	        body: `code=${encodeURIComponent(code)}`
	    }).then(response => response.text())
	    .then(data => {
	        if (data === "success") { //인증 성공시
	            document.getElementById("verification-success").style.display = "block";
	            document.getElementById("email-code").style.display = "none"; // 인증코드 입력칸 숨기기

	            // 이메일 입력 비활성화
	            const emailInput = document.getElementById("id");
	            emailInput.readOnly = true;

	            // 인증 버튼 비활성화
	            document.getElementById("auth-btn").disabled = true;

	        } else {
	            document.getElementById("verification-error").style.display = "block";
	        }
	    });
});

document.getElementById("signup-form").addEventListener("submit", function(event) {
  event.preventDefault();

  const nameValid = document.getElementById("name-error").style.display === 'none';
  const emailValid = document.getElementById("email-error").style.display === 'none';
  const passwordValid = document.getElementById("password-error").style.display === 'none';
  const confirmPasswordValid = document.getElementById("confirm-pass-error").style.display === 'none';
  const dobValid = document.getElementById("dob-error").style.display === 'none';
  const verificationValid = document.getElementById("verification-success").style.display === 'block';

  if (nameValid && emailValid && passwordValid && confirmPasswordValid && dobValid && verificationValid) {
      // 서버에 회원가입 데이터 전송 (DB 저장)
      const formData = new FormData(this);
      fetch("/member/join", {
          method: "POST",
          body: new URLSearchParams(formData)
      })
      .then(response => response.text())
      .then(data => {
          if (data.includes("success")) {
              alert("회원가입이 완료되었습니다!");
              window.location.href = "/member/login";  // 로그인 페이지로 이동
          } else {
              alert("회원가입에 실패했습니다. 다시 시도해주세요.");
          }
      })
      .catch(error => {
          console.error("오류 발생:", error);
          alert("서버 오류가 발생했습니다.");
      });
  } else {
      alert("모든 필드를 정확히 입력해주세요.");
  }
});
