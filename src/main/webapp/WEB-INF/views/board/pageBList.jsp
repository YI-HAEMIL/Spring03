<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_BoardList **</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/spring03Style.css">
</head>
<body>
<h2>** Spring MyBatis BoardList Paging **</h2>
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
<!-- Paging 추가 (view1) -->
<!-- 
<div align="center">
	<c:forEach var="i" begin="1" end="${totalPageNo}">  -->
	<!-- currPage: 강조 Link 필요없음 / 아니면 Link 적용 -->
	<!-- <c:if test="${i==currPage}">
			<font size="5" color="orange">${i}&nbsp;</font>
		</c:if>
		<c:if test="${i!=currPage}">
			<a href="bpage?currPage=${i}">${i}&nbsp;</a>
		</c:if>
	</c:forEach>
</div>
-->

<!-- Paging 추가 : view2 
	 기호 사용: < &lt;   > &gt;
-->
	<div  align="center">
	  <c:choose>    
	     <c:when test="${sPageNo>pageNoCount}">
	        <a href="bpage?currPage=1">First</a>&nbsp;
	        <a href="bpage?currPage=${sPageNo-1}">&lt;</a>&nbsp;&nbsp;
	     </c:when>
	     <c:otherwise>
	        <font color="gray">First &lt;&nbsp;&nbsp;</font>
	     </c:otherwise>
	  </c:choose>
	  
	  <c:forEach var="i" begin="${sPageNo}" end="${ePageNo}">
	     <c:if test="${i==currPage}">
	        <font size="5" color="Orange">${i}&nbsp;</font>  
	    </c:if>
	    <c:if test="${i!=currPage}">
	        <a href="bpage?currPage=${i}">${i}&nbsp;</a>  
	    </c:if>
	  </c:forEach>
	  
	  <c:choose>   
	     <c:when test="${ePageNo<totalPageNo}">
	        <a href="bpage?currPage=${ePageNo+1}">&nbsp;&nbsp;&gt;&nbsp;</a>
	        <a href="bpage?currPage=${totalPageNo}">Last</a>
	     </c:when>
	     <c:otherwise>
	        <font color="gray">&nbsp;&nbsp; &gt; Last &nbsp;</font>
	     </c:otherwise>
	  </c:choose>
	</div>
<hr>
<c:if test="${loginID!=null}">
	<a href="binsertf">[새글등록]</a>&nbsp;
</c:if>
<a href="home">[Home]</a>
</body>
</html>