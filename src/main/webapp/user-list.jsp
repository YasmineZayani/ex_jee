<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enseignant Management Application</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body style="font-family: cursive; ">

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #f2a079;color: white;" >
        <div>
            <a class="navbar-brand">
                      Teacher Management Application            </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="${pageContext.request.contextPath}/list" class="nav-link">Enseignants</a></li>
            <li><a href="${pageContext.request.contextPath}/edit-authorization.jsp" class="nav-link">Authorisation</a></li>
        </ul>

    </nav>
</header>
<br>

<div class="row">
    <div class="container">
        <h3 class="text-center" style="font-size: 30px;
    color: #628cceb8;">List of Teacher</h3>
        <hr>
        

        <div class="container text-right">
            <a href="${pageContext.request.contextPath}/new" class="btn btn-success">Add New User</a>
        </div><br>
                <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/search" method="GET" >
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="username" style="width: 1023px;">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Adresse</th>
                <th>Num Téléphone</th>
                <th>Domaine D'expertise</th>
                <th>cv</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${listUser}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.nom}</td>
                    <td>${user.prenom}</td>
                    <td>${user.adresse}</td>
                    <td>${user.numtel}</td>
                    <td>${user.domaine}</td>
                    <td>${user.cv}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/edit?id=${user.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/delete?id=${user.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
