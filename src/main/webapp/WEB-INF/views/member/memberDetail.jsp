<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring MemberDetail **</title>
<link rel="stylesheet" type="text/css" href="resources/myLib/spring03Style.css">
</head>
<body>
<h2>** Spring MyBatis MemberDetail **</h2>
<h2><pre>
=> memberVO 적용

* I     D : ${Apple.id}
* Password: ${Apple.password}
* N a m e : ${Apple.name}
* Level   : ${Apple.lev}
* BirthDay: ${Apple.birthd}
* Point   : ${Apple.point}
* Weight  : ${Apple.weight}
* 추천인   : ${Apple.rid}
* Image  : <img src="${Apple.uploadfile}" width="70" height="70" />       
</pre></h2>
<c:if test="${message!=null}">
<hr>
=> ${message}
</c:if>
<hr>
<c:if test="${Apple.id==loginID}">
	<a href="mdetail?id=${Apple.id}&jcode=U">Updatef</a>&nbsp;
	<a href="mdelete">Delete</a>&nbsp;
	<a href="mlogout">Logout</a>&nbsp;
</c:if>
<br><hr>
<a href="mlist">mList</a>&nbsp;
<a href="home">Home</a>&nbsp;
</body>
</html>