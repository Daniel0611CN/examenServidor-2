package com.example.examenjsp.servlet;

import com.example.examenjsp.dao.ClienteDAO;
import com.example.examenjsp.dao.ClienteDAOImpl;
import com.example.examenjsp.dao.PedidoDAO;
import com.example.examenjsp.dao.PedidoDAOImpl;
import com.example.examenjsp.model.Cliente;
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

@WebServlet(name = "EditarCliente", value = "/EditarCliente")
public class EditarCliente extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarCliente.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        Optional<Cliente> OptionalCliente = UtilServlet.validaEditar(request);

        if (OptionalCliente.isPresent()) {

            Cliente cliente = OptionalCliente.get();
            this.clienteDAO.update(cliente);

            List<Cliente> listado = this.clienteDAO.getAll();
            request.setAttribute("listado", listado);
            request.setAttribute("newClienteID", cliente.getId());

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listarPedidos.jsp");
        } else {
            request.setAttribute("error", "Error de validación!");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioEditarCliente.jsp");
        }


        dispatcher.forward(request, response);
    }
}
