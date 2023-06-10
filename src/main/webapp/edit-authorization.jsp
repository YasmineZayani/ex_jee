<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enseignant Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: blue">
        <div>
            <a href="https://www.xadmin.net" class="navbar-brand">Enseignant Management Application</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="${pageContext.request.contextPath}/user-list" class="nav-link">Enseignants</a></li>
            <li><a href="${pageContext.request.contextPath}/edit-authorization" class="nav-link">Authorisation</a></li>
        </ul>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/editAuthorization" method="post">
                <fieldset class="form-group">
                    <label for="userId">Select User:</label>
                    <select name="userId" id="userId" class="form-control">
                        <% 
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprj?useSSL=false", "root", "");
                            java.sql.Statement st = con.createStatement();
                            String query = "SELECT nom, prenom FROM USERS";
                            ResultSet rs = st.executeQuery(query);

                            while (rs.next()) {
                                String nom = rs.getString("nom");
                                String prenom = rs.getString("prenom");

                        %>
                                <option><%= nom %> <%= prenom %></option>
                        <% 
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        %>
                    </select>
                </fieldset>
                
                <fieldset class="form-group">
                    <label>Date actuelle:</label>
                    <%-- Récupérer la date actuelle --%>
                    <% Date currentDate = new Date();
                       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                       String formattedDate = sdf.format(currentDate);
                    %>
                    <input type="text" class="form-control" name="currentDate" value="<%= formattedDate %>" readonly>
                </fieldset>

             <fieldset class="form-group">
                    <label>Nombre de semaines restantes:</label>
                    <%-- Calcul du nombre de semaines restantes dans l'année courante --%>
                    <% Calendar calendar = Calendar.getInstance();
                       calendar.setTime(currentDate);
                       int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
                       int totalWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR);
                       int remainingWeeks = totalWeeks - currentWeek + 1;
                    %>
                    <input type="text" class="form-control" name="remainingWeeks" value="<%= remainingWeeks %>" readonly>
                </fieldset>

                <button type="submit" class="btn btn-success">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
