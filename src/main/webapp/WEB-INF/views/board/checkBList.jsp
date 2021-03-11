<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring Check BoardList **</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/spring03Style.css">
</head>
<body>
<h2>** Spring MyBatis Check BoardList **</h2>
<c:if test="${message!=null}">
=> ${message}
</c:if>
<hr>
<div id="searchBar">
<form action="bcheck" method="get">
	<b>Level : </b>
	<input type="checkbox" name="check" value="admin">관리자&nbsp;
	<input type="checkbox" name="check" value="dlwlrma">이지금&nbsp;
	<input type="checkbox" name="check" value="apple">Apple&nbsp;
	<input type="checkbox" name="check" value="banana">Banana&nbsp;&nbsp;
	<input type="submit" value="검색">
	<input type="reset" value="취소">
</form>
</div>
<br>
<table style="font-family:AppleSDGothicNeoM00; width:700px;">
	<tr align="center" height="30" bgcolor="aqua">
	<td>Seq</td><td>Title</td><td>ID</td><td>Regdate</td><td>Count</td>
	</tr>
  <c:forEach var="row" items="${Banana}">
	<tr>
	<td>${row.seq}</td>
	<td style="text-align:left;">
	<!-- 답글등록 후 indent 값에 따른 들여쓰기 -->
	<c:if test="${row.indent>0}">
		<c:forEach begin="1" end="${row.indent}">
			<span>&nbsp;&nbsp;</span>
		</c:forEach>
		<span style="color:orange">re..</span>
	</c:if>
	<a href="bdetail?seq=${row.seq}">${row.title}</a>
	</td>
	<td>${row.id}</td><td>${row.regdate}</td><td>${row.cnt}</td>
	</tr>
  </c:forEach>
</table>
<hr>
<c:if test="${loginID!=null}">
	<a href="binsertf">[새글등록]</a>&nbsp;
</c:if>
<a href="home">[Home]</a>
</body>
</html>