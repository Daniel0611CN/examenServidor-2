<%--
  Created by IntelliJ IDEA.
  User: dani
  Date: 13/12/24
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario Editar Cliente</title>
</head>
<body>
<form method="post" action="EditarCliente">
    <input type="hidden" name="idCliente" value="<%= request.getParameter("id") %>">
    Nombre<input type="text" name="nombre" value="<%= request.getParameter("nombre") %>">
    <br>
    Apellido1<input type="text" name="apellido1" value="<%= request.getParameter("apellido1") %>">
    <br>
    Apellido2<input type="text" name="apellido2" value="<%= request.getParameter("apellido2") %>">
    <br>
    Ciudad<input type="text" name="ciudad" value="<%= request.getParameter("ciudad") %>">
    <br>
    Categoria<input type="number" name="categoria" value="<%= request.getParameter("categoria") %>">
    <br>
    <input type="submit" value="Editar Cliente">
</form>
</body>
</html>
