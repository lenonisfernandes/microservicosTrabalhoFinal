<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AppNotas</title>
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css" rel="stylesheet"> -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>

	<c:import url="/WEB-INF/jsp/menu.jsp" />

	<c:if test="${not empty professor }">

		<div class="container mt-3">
			<h2>AppNotas: Sistema de Cadastramento de Notas</h2>
			<h3>${professor.nome }</h3>

		</div>
	</c:if>
	
		<c:if test="${not empty aluno }">

		<div class="container mt-3">
			<h2>AppNotas: Sistema de Boletim</h2>
			<h3>${aluno.nome }</h3>

		</div>
	</c:if>

</body>
</html>