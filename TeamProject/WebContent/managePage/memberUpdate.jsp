<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "kr.co.EZHOME.domain.User" %>
<!DOCTYPE html>
<!-- 회원가입 페이지 -->
<html>
<head>
<meta charset="UTF-8">
<title>이젠, 집에서 | 회원수정</title>
<link href="/TeamProject/css/styles.css?test1=5" rel="stylesheet" />
    <title>회원수정 화면</title>
    
    <style>
        #wrap{
            width:600px;

            margin-left:auto; 
            margin-right:auto;

            /*text-align:center;*/
        }
        
        table{
           /* border:3px solid #fd7e14 */
        }
        
        td{
            border:1px solid #fd7e14
        }
        
        #title{
            background-color:#fd7e14
        }
        
    </style>
    <script type="text/javascript" src="/TeamProject/js/libs/jquery-3.6.0.min.js"></script>
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> -->
    <script type="text/javascript" src="/TeamProject/js/member.js?test=71"></script>
    <!-- 도로명 주소 검색시 사용하는 daum api -->
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
<%
	User bean=(User)request.getAttribute("bean");
	String[] arr=(String[])request.getAttribute("arr");
%>
<jsp:include page="/ui/nav.jsp"></jsp:include>
<!-- 왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬된다.  -->
    <div align="center">
        <br><br>
        <b><font size="6" color="gray">회원수정</font></b>
        <br><br><br>
    </div>
<div id="wrap">
	<form action="/TeamProject/memberUpdate.do" method="post" name="frm">
		<table>
			<tr>
			<td colspan="2"><hr> </td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="20" maxlength="16" value=<%=bean.getName() %>>*</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<%=bean.getUserid() %>
					<input type="hidden" name="userid" size="20" value=<%=bean.getUserid() %>>
				</td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" id="pwd" name="pwd" size="20" maxlength="16">*</td>
				<td><input type="hidden" name="passwordValid" size="20"></td>
			</tr>
			<tr>
				<td/>
				<td>
					<p class="txt_guidePassword" >
						<span class="passwordGuide1"></span> <br>
						<span class="passwordGuide2"></span>
						<input type="hidden" name="passwordCheckValid" size="20">
					</p>
				</td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" id="pwd_check" name="pwd_check" size="20" maxlength="16">*</td>
			</tr>
			<tr>
				<td/>
				<td>
					<p class="txt_guidePasswordCheck" >
						<span class="passwordCheckGuide"></span>
						<input type="hidden" name="passwordCheckValid" size="20">
					</p>
				</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>
					<input type="text" id="birth" name="birth" value=<%=bean.getBirth() %> maxlength="8">
				</td>
			</tr>
			<tr>
				<td/>
				<td>
					<p class="txt_guideBirth" >
						<span class="birthGuide"></span>
						<input type="hidden" name="birthValid" size="20">
					</p>
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20" value=<%=bean.getEmail() %>></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td>
					<input type='tel' class="phone" name='phone' value=<%=bean.getPhone() %> maxlength="13"/>
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" id="sample4_postcode" name="addr1" value="<%=arr[0] %>" readonly>
					<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="sample4_roadAddress" name="roadAddr" value="<%=arr[1] %>" size="60" readonly><br>
					<input type="hidden" id="sample4_jibunAddress" name="jibunAddr" placeholder="지번주소"  size="60">
					<span id="guide" style="color:#999;display:none"></span> 
					<input type="text" id="sample4_detailAddress" name="addr3" value="<%=arr[2] %>"  size="60"><br>
					<input type="hidden" id="sample4_extraAddress" name="cham" placeholder="참고항목"  size="60">
					<input type="hidden" id="sample4_engAddress" name="engAddr" placeholder="영문주소"  size="60" >
				</td>
			</tr>
			<tr>
				
			</tr>
			<tr>
				<td colspan="2"><hr></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="hidden" name="admin" value="0">
					<input type="submit" value="수정">
					<input type="reset" value="취소" onclick="history.back()">
				</td>
			</tr>
		
		</table>
	</form>
</div>

<jsp:include page="/ui/footer.jsp"></jsp:include>
</body>
</html>