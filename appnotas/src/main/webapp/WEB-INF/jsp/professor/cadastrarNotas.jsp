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
		<h2>Cadastrar Notas</h2>

		<form action="/nota/cadastrar" method="post">
			<div class="mb-3 mt-3">
				<c:if test="${not empty turmas}">
					<label>Turmas:</label>
					<select class="form-control" name="idLecionamento">
						<c:forEach var="lecionamento" items="${turmas}">
							<option value="${lecionamento.id }">${lecionamento.turma.codigo }
								- ${lecionamento.disciplina.nome }</option>
						</c:forEach>
					</select>
					<label>Período:</label>
					<select class="form-control" name="periodo">
						<option value="BIM_1">1º Bimestre</option>
						<option value="BIM_2">2º Bimestre</option>
						<option value="REC_1">1ª Recuperação</option>
						<option value="BIM_3">3º Bimestre</option>
						<option value="BIM_4">4º Bimestre</option>
						<option value="REC_2">2º Recuperação</option>
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

			<form action="/nota/enviar" method="post">
				<div class="mb-3 mt-3">
					<table class="table table-striped">${form }
					</table>
					<input type='hidden' name='idLecionamento'
						value="${lecionamento.id}"> <input type='hidden'
						name='periodo' value="${periodo}">

					<button type="submit" class="btn btn-primary">Enviar</button>
				</div>


			</form>


		</c:if>


	</div>


</body>
</html>