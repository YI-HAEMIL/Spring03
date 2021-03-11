<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring BoardDetail **</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/spring03Style.css">
</head>
<body>
<h2>** Spring MyBatis BoardDetail **</h2>
<table>
	<tr height="40"><td bgcolor="yellow">Seq</td>
		<td>${Apple.seq}</td></tr>
	<tr height="40"><td bgcolor="yellow">Id</td>
		<td>${Apple.id}</td></tr>
	<tr height="40"><td bgcolor="yellow">Title</td>
		<td>${Apple.title}</td></tr>	
	<tr height="40"><td bgcolor="yellow" >Content</td>
		<td><textarea rows="10" cols="40" readonly="readonly">${Apple.content}</textarea></td>
	</tr>
	<tr height="40"><td bgcolor="yellow">Regdate</td>
		<td>${Apple.regdate}</td></tr>
	<tr height="40"><td bgcolor="yellow">Count</td>
		<td>${Apple.cnt}</td></tr>
</table>

<c:if test="${message!=null}">
<hr>
=> ${message}
</c:if>
<hr>
<c:if test="${loginID!=null}">
	<a href="binsertf">새글등록</a>&nbsp;
	<a href="rinsertf?root=${Apple.root}&step=${Apple.step}&indent=${Apple.indent}">답글등록</a>&nbsp;
</c:if>
<c:if test="${Apple.id==loginID}">
	<a href="bdetail?seq=${Apple.seq}&jcode=U">글수정</a>&nbsp;
	<a href="bdelete?seq=${Apple.seq}&root=${Apple.root}">글삭제</a>&nbsp;
						<!-- 삭제시에 원글 or 댓글 구분을 위해 root 추가 -->
	<a href="mlogout">Logout</a>&nbsp;
</c:if>
<br><hr>
<a href="mlist">mList</a>&nbsp;
<a href="blist">bList</a>&nbsp;
<a href="home">Home</a>&nbsp;
</body>
</html>