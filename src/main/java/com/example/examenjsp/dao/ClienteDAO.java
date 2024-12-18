package com.example.examenjsp.dao;

import com.example.examenjsp.model.Cliente;
import com.example.examenjsp.model.Comercial;
import com.example.examenjsp.model.Pedido;

import java.util.List;

public interface ClienteDAO {

    public void create(Cliente cliente);
    public List<Cliente> getAll();
    public void update(Cliente cliente);
    public void delete(int id);

}
