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
		<h2>Visualizar Notas</h2>

		<form action="/nota/visualizar" method="post">
			<div class="mb-3 mt-3">
				<c:if test="${not empty turmas}">
					<label>Turmas:</label>
					<select class="form-control" name="idLecionamento">
						<c:forEach var="lecionamento" items="${turmas}">
							<option value="${lecionamento.id }">${lecionamento.turma.codigo }
								- ${lecionamento.disciplina.nome }</option>
						</c:forEach>
					</select>
				</c:if>

				<c:if test="${empty turmas }">
					<c:set var="botao" value="disabled" />
					<label>O professor não leciona nenhuma turma.</label>
				</c:if>
			</div>

			<button ${botao} type="submit" class="btn btn-primary">Mostrar</button>

		</form>

		<c:if test="${not empty lecionamento}">
			<h2>Notas da Turma ${lecionamento.turma.codigo} -
				${lecionamento.disciplina.nome}</h2>

			<table class="table table-striped">
				<thead>
					<tr>
						<th>Aluno</th>
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
					<c:if test="${not empty table }">
					${table }
				</c:if>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>

		</c:if>


	</div>


</body>
</html>