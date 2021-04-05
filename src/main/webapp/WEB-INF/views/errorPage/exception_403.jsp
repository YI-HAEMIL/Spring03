<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring Default Exception Message **</title>
<link rel="stylesheet" type="text/css" href="resources/jqLib/myStyle.css">
</head>
<body>
<h2>** Spring Security Exception Message (403) **</h2>
<h2>~~ Access Denied !!! ~</h2>
<h2>exception Message => ${exception.message}</h2>
<%-- <h2>exception ToString => ${exception.toString}</h2> --%>
<h2>~~ 당신은 접근 권한이 없습니다.  ~~</h2>
<br><br>
<a href="#" onclick="history.back()">[이전으로 돌아가기]</a>&nbsp;
<a href="home">[HOME]</a>
</body>
</html>