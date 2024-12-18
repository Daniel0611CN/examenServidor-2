package com.example.examenjsp.dao;

import com.example.examenjsp.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl extends AbstractDAOImpl implements ClienteDAO {


    @Override
    public void create(Cliente cliente) {

    }

    @Override
    public List<Cliente> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Cliente> listaCliente = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM ventas.cliente");
            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setCategoria(rs.getInt("categoria"));
                listaCliente.add(cliente);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }

        return listaCliente;
    }

    @Override
    public void update(Cliente cliente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE ventas.cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, categoria = ?  WHERE id = ?");
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getApellido1());
            ps.setString(idx++, cliente.getApellido2());
            ps.setString(idx++, cliente.getCiudad());
            ps.setInt(idx++, cliente.getCategoria());
            ps.setInt(idx, cliente.getId());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de socio con 0 registros actualizados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public void delete(int id) {

    }
}
