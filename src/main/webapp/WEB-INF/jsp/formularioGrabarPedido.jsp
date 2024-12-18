<%--
  Created by IntelliJ IDEA.
  User: dani
  Date: 13/12/24
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formulario Grabar Pedido</title>
</head>
<body>
<form method="post" action="GrabarPedido">
  Cantidad Total <input type="text" name="total">
  <br>
  Fecha <input type="date" name="fecha">
  <br>
  ID Cliente <input type="number" name="idCliente">
  <br>
  ID Comercial<input type="number" name="idComercial">
  <br>
  <input type="submit" value="Grabar Pedido">
</form>
</body>
</html>
