<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.example.examenjsp.model.Comercial" %><%--
  Created by IntelliJ IDEA.
  User: dani
  Date: 13/12/24
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listar Pedidos</title>
</head>
<body>
<div>
  <h1>Listado de Pedidos</h1>
  <%
    // CARGA DEL DRIVER Y CONEXIÓN CON LA BBDD
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/ventas", "root", "secret");

    // UTILIZAR STATEMENT SÓLO EN QUERIES NO PARAMETRIZADAS.
    Statement s = conexion.createStatement();
    ResultSet listado = s.executeQuery("SELECT * FROM ventas.pedido AS pedido JOIN ventas.cliente AS cliente ON pedido.id_cliente = cliente.id JOIN ventas.comercial AS comercial ON pedido.id_comercial = comercial.id");
  %>
  <br>
  <table>
    <thead>
    <tr>
      <th>ID</th>
      <th>Cantidad</th>
      <th>Fecha</th>
      <th>Cliente</th>
      <th>Comercial</th>
    </tr>
    </thead>
    <tbody>
    <%
      while (listado.next()) {
    %>
    <tr>
      <td><%= listado.getString("id") %></td>
      <td><%= listado.getDouble("total") %></td>
      <td><%= listado.getDate("fecha") %></td>
      <td>
        <form method="get" action="EditarCliente">
          <input type="hidden" name="id" value="<%= listado.getString("cliente.id") %>">
          <input type="hidden" name="nombre" value="<%= listado.getString("cliente.nombre") %>">
          <input type="hidden" name="apellido1" value="<%= listado.getString("cliente.apellido1") %>">
          <input type="hidden" name="apellido2" value="<%= listado.getString("cliente.apellido2") %>">
          <input type="hidden" name="ciudad" value="<%= listado.getString("cliente.ciudad") %>">
          <input type="hidden" name="categoria" value="<%= listado.getString("cliente.categoria") %>">
          <input type="submit" value="Editar">
          <%= listado.getString("cliente.nombre") %> <%= listado.getString("cliente.apellido1") %> <%= listado.getString("cliente.apellido2") %>
        </form>
        </td>
      <td><%= listado.getString("comercial.nombre") %> <%= listado.getString("comercial.apellido1") %> <%= listado.getString("comercial.apellido2") %></td>
    </tr>
<%--    <td>--%>
<%--      <div>--%>
<%--        <a href="#">Borrar</a>--%>
<%--      </div>--%>
<%--    </td>--%>
<%--    <td>--%>
<%--      <div>--%>
<%--        <a href="#">Editar</a>--%>
<%--      </div>--%>
<%--    </td>--%>
    <%
      }
      s.close();
      listado.close();
      conexion.close();
    %>
    </tbody>
  </table>
  <div>
    <a href="index.jsp">Volver a Inicio</a>
  </div>
</div>
</body>
</html>
