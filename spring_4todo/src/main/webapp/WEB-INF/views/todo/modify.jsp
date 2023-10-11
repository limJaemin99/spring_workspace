<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet" 
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<nav class="navbar navbar-expand-lg bg-body-tertiary">
				<div class="container-fluid">
					<a class="navbar-brand" href="#">Navbar</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item"><a class="nav-link active"
								aria-current="page" href="#">Home</a></li>
							<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> Dropdown </a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">Action</a></li>
									<li><a class="dropdown-item" href="#">Another action</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="#">Something else
											here</a></li>
								</ul></li>
							<li class="nav-item"><a class="nav-link disabled"
								aria-disabled="true">Disabled</a></li>
						</ul>
						<form class="d-flex" role="search">
							<input class="form-control me-2" type="search"
								placeholder="Search" aria-label="Search">
							<button class="btn btn-outline-success" type="submit">Search</button>
						</form>
					</div>
				</div>
			</nav>
		</div>
		<div class="row content">
			<!-- 	<h1>Hello, Spring~^^</h1>
			<h3>content</h3> -->
			<div class="col">
				<div class="card">
					<div class="card-header">Featured</div>
					<div class="card-body">
						<form action="" method="post">
						<!-- 페이징에서 추가 -->
						<input type="hidden" name="page" value="${pageRequestDTO.page }">
						<input type="hidden" name="size" value="${pageRequestDTO.size }">
						<!--  -->
						<div class="input-group mb-3">
						  <span class="input-group-text" >번호</span>
						  <input type="text" class="form-control" value="<c:out value='${dto.tno }' />" name="tno" readonly>
						</div>
						<div class="input-group mb-3">
						  <span class="input-group-text" >제목</span>
						  <input type="text" class="form-control" value="<c:out value='${dto.title }' />" name="title">
						</div>
						<div class="input-group mb-3">
						  <span class="input-group-text">기한 날짜</span>
						  <input type="date" class="form-control" value="<c:out value='${dto.dueDate }' />" name="dueDate">
						</div>
						<div class="input-group mb-3">
						  <span class="input-group-text">작성자</span>
						  <input type="text" class="form-control" value="<c:out value='${dto.writer }' />" name="writer" readonly>
						</div>
						<div class="form-check">
							<label class="form-check-label">
								finished&nbsp;							
							</label>
							<input class="form-check-input" type="checkbox" name="finished" ${dto.finished?"checked":"" } >
						</div>
						<div class="my-4">
							<div class="float-end">
								<button type="button" class="btn btn-primary">수정</button>
								<button type="button" class="btn btn-danger">삭제</button>
								<button type="button" class="btn btn-secondary">목록</button>
							</div>
						</div>
						</form>
						<script type="text/javascript">
							const serverValidResult = {}
						/* core 태그 사용은 가능하나 자동완성 또는 태그스타일 적용은 안됨 */
						<c:forEach items="${errors}" var="err">
							serverValidResult['${err.field}'] = '${err.defaultMessage}'							
						</c:forEach>
							console.log(serverValidResult)
						</script>
						<script type="text/javascript">
							const formObj = document.forms[1]
							document.querySelector('.btn-danger').addEventListener('click',function(e){
								e.preventDefault()
								e.stopPropagation()
								
								formObj.action='remove'
								formObj.submit()
								
							})
							document.querySelector('.btn-primary').addEventListener('click',function(e){
								e.preventDefault()
								e.stopPropagation()
								
								formObj.action='modify'
								formObj.submit()
								
							})
							document.querySelector('.btn-secondary').addEventListener('click',function(e){
								e.preventDefault()
								e.stopPropagation()
								
								//self.location = "list"
								self.location = "list?${pageRequestDTO.link}"
							})
							
						
						</script>
					</div>	
				</div>
			</div>
		<div class="row footer">
			<!-- <h3>footer</h3> -->
			
			<div class="row fixed-bottom">
				<footer class="py-1 my-1"> <!--  style="z-index:-100" -->
					<p class="text-center text-muted">FOOTER</p>
				</footer>
			</div>
		</div>
	</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
</body>
</html>