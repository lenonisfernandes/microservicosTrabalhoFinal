<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AppNotas</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

</head>
<body>
	<c:import url="/WEB-INF/jsp/menu.jsp" />

	<div class="container mt-3">
		
			<h2>Notas do Aluno ${aluno.nome}</h2>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>Disciplina</th>
						<th>1º Bimestre</th>
						<th>2º Bimestre</th>
						<th>1ª Recuperação</th>
						<th>Média Semestral</th>
						<th>3º Bimestre</th>
						<th>4º Bimestre</th>
						<th>2ª Recuperação</th>
						<th>Média Final</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					${table }
				</tbody>
			</table>



	</div>


</body>
</html>