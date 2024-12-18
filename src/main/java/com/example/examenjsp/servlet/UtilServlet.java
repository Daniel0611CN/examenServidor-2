package com.example.examenjsp.servlet;

import com.example.examenjsp.model.Cliente;
import com.example.examenjsp.model.Pedido;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class UtilServlet {

    public static Optional<Pedido> validaGrabar(HttpServletRequest request) {
        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        int id = -1;
        double total = -1;
        Date fecha = null;
        int idCliente = -1;
        int idComercial = -1;

        try {

            if (Double.parseDouble(request.getParameter("total"))>0) {
                total = Double.parseDouble(request.getParameter("total"));
            }
            //UTILIZO LOS CONTRACTS DE LA CLASE Objects PARA LA VALIDACIÓN
            //             v---- LANZA NullPointerException SI EL PARÁMETRO ES NULL
            //CONTRACT nonBlank..
            //UTILIZO isBlank SOBRE EL PARÁMETRO DE TIPO String PARA CHEQUEAR QUE NO ES UN PARÁMETRO VACÍO "" NI CADENA TODO BLANCOS "    "
            //          |                                EN EL CASO DE QUE SEA BLANCO LO RECIBIDO, LANZO UNA EXCEPCIÓN PARA INVALIDAR EL PROCESO DE VALIDACIÓN
            //          -------------------------v                      v---------------------------------------|
            String fechaStr = request.getParameter("fecha");

            if (fechaStr == null) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            if (request.getParameter("fecha").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            LocalDate ld1 = LocalDate.now();
            int dia = ld1.getDayOfMonth();
            int mes = ld1.getMonthValue();
            int anio = ld1.getYear();

            Date actual = new Date(anio-1900, mes-1, dia);

            try {
                fecha = sdf.parse(fechaStr);
                if (actual.before(fecha)) {
                    Exception e;
                }
            } catch (Exception e) {
                throw new RuntimeException("Fecha no válida.");
            }

            idCliente = Integer.parseInt(request.getParameter("idCliente"));
            idComercial = Integer.parseInt(request.getParameter("idComercial"));

            return Optional.of(new Pedido(0, total, fechaStr, idCliente, idComercial));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();
    }

    public static Optional<Cliente> validaEditar(HttpServletRequest request) {
        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        int id = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = null;
        String apellido1 = null;
        String apellido2 = null;
        String ciudad = null;
        int categoria = -1;


        try {
            Objects.requireNonNull(request.getParameter("nombre"));
            if (request.getParameter("nombre").isBlank())  throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");

            Objects.requireNonNull(request.getParameter("apellido1"));
            if (request.getParameter("apellido1").isBlank())  throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            apellido1 = request.getParameter("apellido1");

            Objects.requireNonNull(request.getParameter("apellido2"));
            if (request.getParameter("apellido2").isBlank())  throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            apellido2 = request.getParameter("apellido2");

            Objects.requireNonNull(request.getParameter("ciudad"));
            if (request.getParameter("ciudad").isBlank())  throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            ciudad = request.getParameter("ciudad");

            categoria = Integer.parseInt(request.getParameter("categoria"));

            if (categoria >= 1 && categoria <= 1000) {
                return Optional.of(new Cliente(id, nombre, apellido1, apellido2, ciudad, categoria));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();
    }

}
