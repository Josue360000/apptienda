package com.example.apptienda.Controlador;

import android.content.Context;

import com.example.apptienda.BaseDatos.ProductoDAO;
import com.example.apptienda.Modelo.Producto;

import java.util.List;

public class ProductoControlador {

    private ProductoDAO dao;

    public ProductoControlador(Context context) {
        dao = new ProductoDAO(context);
    }

    public long crearProducto(String name, double price, int stock, String barcode, int minStock) {
        Producto p = new Producto(0, name, price, stock, barcode, minStock);
        return dao.insertar(p);
    }

    public int actualizarProducto(int id, String name, double price, int stock, String barcode, int minStock) {
        Producto p = new Producto(id, name, price, stock, barcode, minStock);
        return dao.actualizar(p);
    }

    public int eliminarProducto(int id) {
        return dao.eliminar(id);
    }

    public List<Producto> listarProductos() {
        return dao.listar();
    }

    public Producto buscarProducto(int id) {
        return dao.buscarPorId(id);
    }

    public Producto buscarPorBarcode(String barcode) {
        return dao.buscarPorBarcode(barcode);
    }
}