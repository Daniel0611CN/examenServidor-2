package com.example.examenjsp.model;

import java.util.Objects;

public class Comercial {

    private int id;
    private String nombreComercial;
    private String apellido1Comercial;
    private String apellido2Comercial;
    private float comision;

    public Comercial() {
    }

    public Comercial(int id, String nombre, String apellido1, String apellido2, float comision) {
        this.id = id;
        this.nombreComercial = nombre;
        this.apellido1Comercial = apellido1;
        this.apellido2Comercial = apellido2;
        this.comision = comision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getApellido1Comercial() {
        return apellido1Comercial;
    }

    public void setApellido1Comercial(String apellido1Comercial) {
        this.apellido1Comercial = apellido1Comercial;
    }

    public String getApellido2Comercial() {
        return apellido2Comercial;
    }

    public void setApellido2Comercial(String apellido2Comercial) {
        this.apellido2Comercial = apellido2Comercial;
    }

    public float getComision() {
        return comision;
    }

    public void setComision(float comision) {
        this.comision = comision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comercial comercial = (Comercial) o;
        return id == comercial.id && Float.compare(comision, comercial.comision) == 0 && Objects.equals(nombreComercial, comercial.nombreComercial) && Objects.equals(apellido1Comercial, comercial.apellido1Comercial) && Objects.equals(apellido2Comercial, comercial.apellido2Comercial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreComercial, apellido1Comercial, apellido2Comercial, comision);
    }

    @Override
    public String toString() {
        return "Comercial{" +
                "id=" + id +
                ", nombre='" + nombreComercial + '\'' +
                ", apellido1='" + apellido1Comercial + '\'' +
                ", apellido2='" + apellido2Comercial + '\'' +
                ", comision=" + comision +
                '}';
    }
}
