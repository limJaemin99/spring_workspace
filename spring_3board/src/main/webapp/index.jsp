<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
<body>
<header>
<h3>메인 Main</h3>
<hr>
</header>
<nav>
<!-- "user" 라는 이름의 애트리뷰트 가져오기 -->
<c:if test="${user !=null }">
	<h4><c:out value="${user.name }"/> 님 환영합니다.</h4>
	<p><a href="logout">로그아웃</a></p>
</c:if>
<c:if test="${user ==null }">
	<p><a href="login">로그인</a></p>
	<p><a href="member/join">회원가입</a></p>
</c:if>
	<p><a href="community/list">커뮤니티</a></p>
</nav>

<hr>
<h3>Community Controller Test</h3>
<a href="community/list">Community list</a>
<a href="community/read">Community read</a>
<a href="community/write">Community write</a>

<hr>
<!--  -->
<h3>MVC Controller Test</h3>
<a href="sample/list">sample list</a>
<a href="sample/read">sample read</a>
<a href="sample/write">sample write</a>

</body>
</html>