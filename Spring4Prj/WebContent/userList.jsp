<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 리스트</h1>
	<table>
		<tr>
			<th>유저 리스트</th>
		</tr>
		<c:forEach var="user" items="${userListKey}">
			<tr>
				<td><a href=getUserInfo.do?id=${user.userid}>${user.name}</a><a href="removeUser.do/${user.userid}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>