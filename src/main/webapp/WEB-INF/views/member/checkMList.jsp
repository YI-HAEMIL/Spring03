<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring Check MemberList **</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/spring03Style.css">
</head>
<body>
<h2>** Spring MyBatis Check MemberList **</h2>
<c:if test="${message!=null}">
=> ${message}
</c:if>
<hr>
<div id="searchBar">
<form action="mcheck" method="get">
	<b>Level : </b>
	<input type="checkbox" name="check" value="A">관리자&nbsp;
	<input type="checkbox" name="check" value="B">나무&nbsp;
	<input type="checkbox" name="check" value="C">잎새&nbsp;
	<input type="checkbox" name="check" value="D">새싹&nbsp;&nbsp;
	<input type="submit" value="검색">
	<input type="reset" value="취소">
</form>
</div>
<br>
<table border="1">
	<tr align="center" height="30" bgcolor="pink">
	<td>I D</td><td>Password</td><td>Name</td><td>Level</td><td>Birthday</td>
	<td>Point</td><td>Weight</td><td>추천인</td><td>Image</td>
	</tr>
  <c:forEach var="row" items="${Banana}">
	<tr>
	<td><a href="mdetail?id=${row.id}">${row.id}</a></td>
	<td>${row.password}</td><td>${row.name}</td><td>${row.lev}</td>
	<td>${row.birthd}</td><td>${row.point}</td><td>${row.weight}</td>
	<td>${row.rid}</td>
	<td><img src="${row.uploadfile}" width="70" height="70"></td>
	</tr>
  </c:forEach>
</table>
<hr><a href="home">[Home]</a>
</body>
</html>