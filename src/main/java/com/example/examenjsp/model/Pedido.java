package com.example.examenjsp.model;

import java.util.Objects;

public class Pedido {

    private int id;
    private double total;
    private String fecha;
    private int id_cliente;
    private int id_comercial;

    public Pedido() {
    }

    public Pedido(int id, double total, String fecha, int id_cliente, int id_comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_comercial = id_comercial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_comercial() {
        return id_comercial;
    }

    public void setId_comercial(int id_comercial) {
        this.id_comercial = id_comercial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id == pedido.id && Double.compare(total, pedido.total) == 0 && id_cliente == pedido.id_cliente && id_comercial == pedido.id_comercial && Objects.equals(fecha, pedido.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, total, fecha, id_cliente, id_comercial);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", total=" + total +
                ", fecha=" + fecha +
                ", id_cliente=" + id_cliente +
                ", id_comercial=" + id_comercial +
                '}';
    }
}
