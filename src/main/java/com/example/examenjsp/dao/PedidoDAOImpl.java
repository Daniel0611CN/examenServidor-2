package com.example.examenjsp.dao;

import com.example.examenjsp.model.Cliente;
import com.example.examenjsp.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl extends AbstractDAOImpl implements PedidoDAO {


    @Override
    public void create(Pedido pedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            //1 alternativas comentadas:
            //Ver también, AbstractDAOImpl.executeInsert ...
            //Columna fabricante.codigo es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente.
            ps = conn.prepareStatement("INSERT INTO ventas.pedido (id, total, fecha, id_cliente, id_comercial) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setInt(idx++, pedido.getId());
            ps.setDouble(idx++, pedido.getTotal());
            ps.setDate(idx++, Date.valueOf(pedido.getFecha()));
            ps.setInt(idx++, pedido.getId_cliente());
            ps.setInt(idx, pedido.getId_comercial());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de socio con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                pedido.setId(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException  e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public List<Pedido> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Pedido> listaPedido = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM ventas.pedido");
            while (rs.next()) {
                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setFecha(String.valueOf(rs.getDate("fecha")));
                pedido.setId_cliente(rs.getInt("id_cliente"));
                pedido.setId_comercial(rs.getInt("id_comercial"));
                listaPedido.add(pedido);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }

        return listaPedido;
    }

    @Override
    public void update(Pedido pedido) {

    }

    @Override
    public void delete(int id) {

    }
}
