<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
    <% 
        // 세션에서 로그인 정보를 가져옵니다.
        String id = (String) session.getAttribute("id");
        String name = (String) session.getAttribute("name");
        String level = (String) session.getAttribute("level");

        if (id != null) {
    %>
        <h1>Welcome, <%= name %>!</h1>
        <p>Your ID: <%= id %></p>
        <p>Your Level: <%= level %></p>
        <form action="/admin/logout" method="get">
            <button type="submit">Logout</button>
        </form>
    <% 
        } else {
    %>
        <h1>You are not logged in.</h1>
        <a href="/admin/login">Login</a>
    <% 
        }
    %>
</body>
</html>
