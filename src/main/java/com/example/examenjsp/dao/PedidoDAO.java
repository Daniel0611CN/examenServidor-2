package com.example.examenjsp.dao;

import com.example.examenjsp.model.Pedido;

import java.util.List;

public interface PedidoDAO {

    public void create(Pedido pedido);
    public List<Pedido> getAll();
    public void update(Pedido pedido);
    public void delete(int id);

}
