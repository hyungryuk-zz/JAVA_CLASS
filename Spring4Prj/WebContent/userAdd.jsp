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
	<div>
		<h1>사용자 정보 등록</h1>
		<form method="post" action="addUser.do">
			<table>
				<tr>
					<td>아이디 :</td>
					<td><input type="text" name="userid"></td>
				</tr>
				<tr>
					<td>이름 :</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>성별 :</td>
					<td><c:forEach var="gen" items="${dataKey.gender}">
							<input type="radio" name="gender" value="${gen}">${gen}
					</c:forEach></td>
				</tr>
				<tr>
					<td>주소 :</td>
					<td><select name="city">
							<c:forEach var="cityVal" items="${dataKey.city}">
								<option value="${cityVal}">${cityVal}</option>

							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td colspan="2" rowspan="2"><input type="submit" value="등록">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>