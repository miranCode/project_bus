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
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;  // 이메일 형식
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

document.getElementById("auth-btn").addEventListener("click", function () {
    const email = document.getElementById("id").value;
    fetch("/sendVerification", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `email=${encodeURIComponent(email)}`
    }).then(response => response.text())
    .then(data => {
        if (data === "success") {
            alert("인증번호가 이메일로 발송되었습니다.");
            const emailCodeDiv = document.getElementById("email-code");
            emailCodeDiv.style.display = "flex";
        } else {
            alert("이메일 전송에 실패했습니다.");
        }
    });
});

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
        if (data === "success") {
            document.getElementById("verification-success").style.display = "block";
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
  const phoneValid = document.getElementById("phone-error").style.display === 'none';
  const verificationValid = document.getElementById("verification-success").style.display === 'block';

  if (nameValid && emailValid && passwordValid && confirmPasswordValid && dobValid && phoneValid && verificationValid) {
    alert("회원가입이 완료되었습니다!");
    window.location.href = "login.html";
  } else {
    alert("모든 필드를 정확히 입력해주세요.");
  }
});
