<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MemberList Cri**</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/spring03Style.css">
<script src="resources/myLib/jquery-3.2.1.min.js"></script>
<script>
	$(function(){
		$('#searchType').change(function(){
			if($(this).val() == 'n'){
				$('#keyword').val('');
			}
		});

		$('#searchBtn').on("click", function(){
			self.location="cmpage"
			      + "${pageMaker.makeQuery(1)}"
			      + "&searchType="
			      + $('#searchType').val()
			      + "&keyword="
			      + $('#keyword').val();
		// => ?currPage=7&rowPerPage=10&searchType=tc&keyword=java
		}); //click

	}); //ready
</script>
</head>
<body>
<h2>** Spring MyBatis MemberList PagingCri**</h2>
<c:if test="${message!=null}">
<hr>
=> ${message}
</c:if>
<div id="searchBar">
	<select name="searchType" id="searchType">
		<option value="n" <c:out value="${pageMaker.cri.searchType==null ? 'selected' : ''}" />>
		---</option>
		<option value="i" <c:out value="${pageMaker.cri.searchType=='i' ? 'selected' : ''}" />>
		ID</option>
		<option value="m" <c:out value="${pageMaker.cri.searchType=='m' ? 'selected' : ''}" />>
		Name</option>
		<option value="r" <c:out value="${pageMaker.cri.searchType=='r' ? 'selected' : ''}" />>
		Recommend ID</option>
		<option value="b" <c:out value="${pageMaker.cri.searchType=='b' ? 'selected' : ''}" />>
		Birthday</option>
		<option value="im" <c:out value="${pageMaker.cri.searchType=='im' ? 'selected' : ''}" />>
		ID/Name</option>
		<option value="imr" <c:out value="${pageMaker.cri.searchType=='imr' ? 'selected' : ''}" />>
		ID/Name/RID</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}">
	<button id="searchBtn">Search</button>
</div>
<hr>
<table border="1" >
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
	<td><img src="${row.uploadfile}" width="70" height="70">        </td>
	</tr>
  </c:forEach>
</table>
<hr>
	<div  align="center">
	 	<!-- 1) First << , Prev < : enabled 여부 -->
	 	<c:if test="${pageMaker.prev && pageMaker.sPageNo > 1}">
	 		<a href="cmpage${pageMaker.searchQuery(1)}">First</a> &nbsp;
	 		<a href="cmpage${pageMaker.searchQuery(pageMaker.sPageNo-1)}">Prev</a>&nbsp;&nbsp;
	 	</c:if>
		
		<!-- 2) sPage~ePage까지 displayPageNo 값 만큼 출력 -->
		<c:forEach var="i" begin="${pageMaker.sPageNo}" end ="${pageMaker.ePageNo}">
			<c:if test="${i==pageMaker.cri.currPage}">
				<font size="5" color="Orange">${i}&nbsp;</font>
			</c:if>
			<c:if test="${i!=pageMaker.cri.currPage}">
				<a href="cmpage${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
			</c:if>
		</c:forEach>
		
		<!-- 3) Next > , Last >> : enabled 여부 -->
		<c:if test="${pageMaker.next && pageMaker.ePageNo > 0}">
	 		&nbsp;&nbsp;<a href="cmpage${pageMaker.searchQuery(pageMaker.ePageNo+1)}">Next</a>
	 		&nbsp;<a href="cmpage${pageMaker.searchQuery(pageMaker.lastPageNo)}">Last</a> 
	 	</c:if>
	</div>
<hr>
<a href="home">[Home]</a>
</body>
</html>