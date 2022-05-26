<%@page import="kr.co.EZHOME.dto.UserDTO"%>
<%@page import="kr.co.EZHOME.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
	<br><br><br><br><br>
<!-- 정보  -->	
	<%
	String id=(String)session.getAttribute("id");
	UserDAO udao=UserDAO.getInstance();
	UserDTO udto=udao.getMember(id);
	
%>
	<form action="Modify.do" method="post" >
		<div id="wrap" align="center">	
		<h3>회원 정보 수정</h3><br>
				
		<table>
			<tr>
				<td>이름</td>
				<td><%=udto.getName() %><br></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><%=udto.getUserid() %></td>
				<td><input type="hidden" name="userid" value=<%=udto.getUserid()%>></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pwd" size="20"></td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" size="20"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" size="20"></td>
			</tr>
			<!-- 
			<tr>
				<td>주소</td>
				<td><input type="text" name="address" size="40"></td>
			</tr>
			-->
			<tr>
				<td>등급</td>
				<td>
					<input type="radio" name="admin" value="0" checked="checked">일반회원
					<input type="radio" name="admin" value="1">관리자
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="확인" onclick="index.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="취소">
				</td>
			</tr>
			
		</table>
		</div>
	</form>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>