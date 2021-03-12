<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_BoardList Cri**</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/spring03Style.css">
</head>
<body>
<h2>** Spring MyBatis BoardList PagingCri **</h2>
<c:if test="${message!=null}">
=> ${message}
</c:if>
<hr>
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
<!-- Paging Criteria 추가 
	 1) First << , Prev < : enabled 여부
	 2) sPage~ePage까지 displayPageNo 값 만큼 출력
	 3) Next > , Last >> : enabled 여부
-->
	<div  align="center">
	 	<!-- 1) First << , Prev < : enabled 여부 -->
	 	<c:if test="${pageMaker.prev && pageMaker.sPageNo > 1}">
	 		<a href="cbpage${pageMaker.makeQuery(1)}">First</a> &nbsp;
	 		<a href="cbpage${pageMaker.makeQuery(pageMaker.sPageNo-1)}">Prev</a>&nbsp;&nbsp;
	 	</c:if>
		<!-- 2) sPage~ePage까지 displayPageNo 값 만큼 출력 -->
		<c:forEach var="i" begin="${pageMaker.sPageNo}" end ="${pageMaker.ePageNo}">
			<c:if test="${i==pageMaker.cri.currPage}">
				<font size="5" color="Orange">${i}&nbsp;</font>
			</c:if>
			<c:if test="${i!=pageMaker.cri.currPage}">
				<a href="cbpage${pageMaker.makeQuery(i)}">${i}</a>&nbsp;
			</c:if>
		</c:forEach>
		<!-- 삼항식과 비교 
		<c:out value="${i==pageMaker.cri.currPage? 'class=active' : ''}"/>
		-->
		<!-- 3) Next > , Last >> : enabled 여부 -->
		<c:if test="${pageMaker.next && pageMaker.ePageNo > 0}">
	 		&nbsp;&nbsp;<a href="cbpage${pageMaker.makeQuery(pageMaker.ePageNo+1)}">Next</a>
	 		&nbsp;<a href="cbpage${pageMaker.makeQuery(pageMaker.lastPageNo)}">Last</a> 
	 	</c:if>
	</div>
<hr>
<c:if test="${loginID!=null}">
	<a href="binsertf">[새글등록]</a>&nbsp;
</c:if>
<a href="home">[Home]</a>
</body>
</html>