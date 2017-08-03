<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${userKey.name}의상세정보</h1>
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>성별</th>
			<th>주소</th>
		</tr>
		<tr>
			<td>${userKey.userid}</td>
			<td>${userKey.name}</td>
			<td>${userKey.gender}</td>
			<td>${userKey.city}</td>
		</tr>
	</table>

</body>
</html>