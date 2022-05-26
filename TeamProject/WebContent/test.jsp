<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원가입</title>
<link href="css/styles.css" rel="stylesheet" />
</head>
<body>
<%
String userid = request.getParameter("userid");
String pwd = request.getParameter("pwd");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
String admin = request.getParameter("admin");
%>

<h2>userid==<%=userid %></h2>
<h2>pwd==<%=pwd %></h2>
<h2>email==<%=email %></h2>
<h2>phone==<%=phone %></h2>
<h2>admin==<%=admin %></h2>
</body>
</html>