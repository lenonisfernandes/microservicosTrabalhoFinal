<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/">APPNotas</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="/home">Home</a></li>
			<c:if test="${not empty professor}">
				<li><a href="/nota/visualizar">Vizualizar Notas</a></li>
				<li><a href="/nota/cadastrar">Cadastrar Notas</a></li>
			</c:if>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${not empty professor}">
				<li><a href="/logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout, ${professor }</a></li>
			</c:if>
			<c:if test="${not empty aluno}">
				<li><a href="/logout"><span
						class="glyphicon glyphicon-log-out"></span> Logout, ${aluno }</a></li>
			</c:if>

			<c:if test="${empty professor}">
				<c:if test="${empty aluno}">
					<li><a href="/login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</c:if>
			</c:if>
		</ul>
	</div>
</nav>