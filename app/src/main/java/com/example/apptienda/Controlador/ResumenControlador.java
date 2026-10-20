package com.example.apptienda.Controlador;

import android.content.Context;

import com.example.apptienda.BaseDatos.ProductoDAO;
import com.example.apptienda.Modelo.Producto;
import com.example.apptienda.Modelo.ResumenInventario;

import java.util.List;

public class ResumenControlador {

    private ProductoDAO productoDAO;

    public ResumenControlador(Context context) {
        productoDAO = new ProductoDAO(context);
    }

    public ResumenInventario obtenerResumen() {
        double total = productoDAO.valorTotalInventario();
        List<Producto> top5 = productoDAO.top5PorValor();
        List<Producto> alertas = productoDAO.productosBajoStock();
        return new ResumenInventario(total, top5, alertas);
    }
}