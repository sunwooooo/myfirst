<%@page import="kr.co.EZHOME.dto.UserDTO"%>
<%@page import="kr.co.EZHOME.dao.UserDAO"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

.scrolltable {
	display: block;
	overflow: auto;
}

.info button{
	overflow: auto;
	background-color: #ffa600;
	width: 195px;
}
.info table {
	align: center;
	display: block;
	overflow: auto;
	width: 1000px;
	height: 300px;
	border: 1px solid #000;
	border-spacing: 0;
	table-layout: fixed;
}

.info th {
	width: 200px;
	height: 35px;
	border: 1px solid #000;
	background: #ffbc40;
	align: center;
}

.info td {
	width: 200px;
	height: 30px;
	border: 1px solid #000;
	align: center;
	
}
</style>
</head>
<body>
	<jsp:include page="/ui/nav.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<%
		UserDAO udao = UserDAO.getInstance();
		Vector<UserDTO> vec = udao.allSelectMember();
	%>

	<div align="center" class="info" >
	<table class="scrolltable">
	<tr class="info">
				<th>이름</th>
				<th>아이디</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>관리</th>
				<th>버튼</th>
			</tr>
		<form action="/TeamProject/memberSearch.do" method="post">
	
			<%
				for (int i = 0; i < vec.size(); i++) {
					UserVO mbean = vec.get(i);
			%>
			<tr>
				<td><%=mbean.getName()%></td>
				<td><%=mbean.getUserid()%></td>
				<td><%=mbean.getEmail()%></td>
				<td><%=mbean.getPhone()%></td>
				<td><button type="submit" name="update" value="<%=mbean.getUserid()%>" formaction="/TeamProject/MemberUpdate.do" method="post">수정</button></td>
				<td><button type="submit" name="delete" value="<%=mbean.getUserid()%>">삭제</button></td>
			</tr>
			<%
				}
			%>
			
		</table>
		<br> <br>
		
			<select name="type">
				<option value="userid">ID</option>
				<option value="name">이름</option>
				<option value="phone">전화번호</option>
			</select> <input type="text" name="key"> <input type="submit"
				value="검색">

		</form>

	</div>

	<br>
	<br>
	<br>
	<jsp:include page="/ui/footer.jsp"></jsp:include>

</body>
</html>