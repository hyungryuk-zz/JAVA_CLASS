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
<h2>Customer List</h2>
   <table>
   <tr>
      <th>SSN</th>
      <th>이름</th>
      <th>주소</th>
   </tr>
   <c:forEach var="customerList" items="${customerList}">
      <tr>
         <td><a href="broker?cmd=oneCustomer&ssn=${customerList.SSN }">${customerList.SSN }</a></td>
         <td>${customerList.name }</td>
         <td>${customerList.addr }</td>
         <td><input type="button" value="삭제" onClick="location.href='broker?cmd=delCustomer&ssn=${customerList.SSN }'"></td>
         <td><input type="button" value="수정" onClick="location.href='broker?cmd=editCustomer&ssn=${customerList.SSN }'"></td>
         
      </tr>
   </c:forEach>
   
   <tr>
   <form method="POST" action="broker?cmd=editCusData">
	   	<td><input type="text" name="ssn" value="${cus.SSN}" readonly="readonly"></td>
	   	<td><input type="text" name = "name" value="${cus.name}"></td>
	   	<td><input type="text" name = "addr" value="${cus.addr}"></td>
	   	<td><input type="submit" value="수정"></td>
   	</form>
   	</tr>
   </table>
</body>
</html>