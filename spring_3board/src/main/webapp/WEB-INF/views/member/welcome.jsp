<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 완료</title>
<style type="text/css">
	body {
		text-align: center;
	}
	button {
		width: 70px;
		height: 35px;
	}
</style>
</head>
<body>
<h1>Welcome!</h1>
<button onclick="location.href='${pageContext.request.contextPath}'">home</button>
<button onclick="location.href='../login'">login</button>
<script type="text/javascript">
	if('${message}'.length !=0) alert('${message}');
</script>
</body>
</html>