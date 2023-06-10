<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Enseignant Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">			
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand">
					Enseignant Management Application </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/user-list"
					class="nav-link">Enseignants</a></li>
				<li><a href="<%=request.getContextPath()%>/edit-authorization.jsp"
					class="nav-link">Authorisation</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit User
            		</c:if>
						<c:if test="${user == null}">
            			Add New User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nom</label> <input type="text"
						value="<c:out value='${user.nom}' />" class="form-control"
						name="nom" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Prenom</label> <input type="text"
						value="<c:out value='${user.prenom}' />" class="form-control"
						name="prenom" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Adresse</label> <input type="text"
						value="<c:out value='${user.adresse}' />" class="form-control"
						name="adresse" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Num Téléphone</label> <input type="text"
						value="<c:out value='${user.numtel}' />" class="form-control"
						name="numtel">
				</fieldset>

				<fieldset class="form-group">
					<label>Domaine d'expertise</label> <input type="text"
						value="<c:out value='${user.domaine}' />" class="form-control"
						name="domaine">
				</fieldset>

				<fieldset class="form-group">
					<label>Import cv</label> <input type="file"
						value="<c:out value='${user.cv}' />" class="form-control"
						name="cv">
				</fieldset>
                <img src="images/hom.jpg" alt="Description de l'image" class="img-fluid">

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>