<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/ui/nav.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%
		
	%>

	<div id="wrap" align="center">

		<h2>관리자 페이지</h2>
		<br>
		<br>
		 <form action="/TeamProject/memberSearch.do" method="post"> 
		<input type="submit" value="회원관리">
		<input type="submit" formaction="/TeamProject/bbsList.do" value="공지사항관리">
		 </form>
	</div>
	<br>
	<br>
	<jsp:include page="/ui/footer.jsp"></jsp:include>

</body>
</html>