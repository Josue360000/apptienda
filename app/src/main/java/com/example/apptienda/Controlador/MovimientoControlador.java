package com.example.apptienda.Controlador;

import android.content.Context;

import com.example.apptienda.BaseDatos.MovimientoDAO;
import com.example.apptienda.BaseDatos.ProductoDAO;
import com.example.apptienda.Modelo.Movimiento;
import com.example.apptienda.Modelo.Producto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MovimientoControlador {

    private MovimientoDAO movimientoDAO;
    private ProductoDAO productoDAO;

    public MovimientoControlador(Context context) {
        movimientoDAO = new MovimientoDAO(context);
        productoDAO = new ProductoDAO(context);
    }

    public String registrarMovimiento(int productId, int qty, String type) {
        Producto p = productoDAO.buscarPorId(productId);
        if (p == null) {
            return "Producto no encontrado";
        }
        int nuevoStock;
        if (type.equals("ENTRADA")) {
            nuevoStock = p.getStock() + qty;
        } else if (type.equals("SALIDA")) {
            if (qty > p.getStock()) {
                return "Stock insuficiente para realizar la salida";
            }
            nuevoStock = p.getStock() - qty;
        } else {
            return "Tipo de movimiento invalido";
        }

        String fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
        Movimiento m = new Movimiento(0, productId, qty, type, fecha);
        movimientoDAO.insertar(m);
        productoDAO.actualizarStock(productId, nuevoStock);

        if (nuevoStock <= p.getMinStock()) {
            return "Movimiento registrado. Alerta: stock minimo alcanzado para " + p.getName();
        }
        return "Movimiento registrado correctamente";
    }

    public List<Movimiento> listarMovimientos() {
        return movimientoDAO.listar();
    }
}