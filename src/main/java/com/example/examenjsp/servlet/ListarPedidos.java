package com.example.examenjsp.servlet;

import com.example.examenjsp.dao.PedidoDAO;
import com.example.examenjsp.dao.PedidoDAOImpl;
import com.example.examenjsp.model.Pedido;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarPedidos", value = "/ListarPedidos")
public class ListarPedidos extends HttpServlet {

    private PedidoDAO pedidoDAO = new PedidoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listarPedidos.jsp");

        List<Pedido> listado = this.pedidoDAO.getAll();
        request.setAttribute("listado", listado);

        dispatcher.forward(request, response);
    }
}
