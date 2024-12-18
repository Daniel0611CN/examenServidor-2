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
import java.util.Optional;

@WebServlet(name = "GrabarPedido", value = "/GrabarPedido")
public class GrabarPedido extends HttpServlet {

    private PedidoDAO pedidoDAO = new PedidoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioGrabarPedido.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        Optional<Pedido> OptionalPedido = UtilServlet.validaGrabar(request);

        if (OptionalPedido.isPresent()) {

            Pedido pedido = OptionalPedido.get();
            this.pedidoDAO.create(pedido);

            List<Pedido> listado = this.pedidoDAO.getAll();
            request.setAttribute("listado", listado);
            request.setAttribute("newPedidoID", pedido.getId());

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listarPedidos.jsp");
        } else {
            request.setAttribute("error", "Error de validación!");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioGrabarPedido.jsp");
        }


        dispatcher.forward(request, response);
    }
}
