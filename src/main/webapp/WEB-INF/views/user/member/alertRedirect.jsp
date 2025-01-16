<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alert</title>
    <script type="text/javascript">
        window.onload = function () {
            alert("${alertMessage}");
            window.location.href = '/member/login'; // 로그인 페이지로 리다이렉트
        }
    </script>
</head>
<body>

</body>
</html>