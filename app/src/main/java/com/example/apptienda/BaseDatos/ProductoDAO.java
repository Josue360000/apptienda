package com.example.apptienda.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptienda.Modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Conexion conexion;

    public ProductoDAO(Context context) {
        conexion = new Conexion(context);
    }

    public long insertar(Producto p) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", p.getName());
        cv.put("price", p.getPrice());
        cv.put("stock", p.getStock());
        cv.put("barcode", p.getBarcode());
        cv.put("min_stock", p.getMinStock());
        long id = db.insert(Conexion.TABLA_PRODUCTS, null, cv);
        db.close();
        return id;
    }

    public int actualizar(Producto p) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", p.getName());
        cv.put("price", p.getPrice());
        cv.put("stock", p.getStock());
        cv.put("barcode", p.getBarcode());
        cv.put("min_stock", p.getMinStock());
        int r = db.update(Conexion.TABLA_PRODUCTS, cv, "id=?",
                new String[]{String.valueOf(p.getId())});
        db.close();
        return r;
    }

    public int eliminar(int id) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        int r = db.delete(Conexion.TABLA_PRODUCTS, "id=?",
                new String[]{String.valueOf(id)});
        db.close();
        return r;
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, name, price, stock, barcode, min_stock FROM products ORDER BY name", null);
        while (c.moveToNext()) {
            Producto p = new Producto();
            p.setId(c.getInt(0));
            p.setName(c.getString(1));
            p.setPrice(c.getDouble(2));
            p.setStock(c.getInt(3));
            p.setBarcode(c.getString(4));
            p.setMinStock(c.getInt(5));
            lista.add(p);
        }
        c.close();
        db.close();
        return lista;
    }

    public Producto buscarPorId(int id) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, name, price, stock, barcode, min_stock FROM products WHERE id=?",
                new String[]{String.valueOf(id)});
        Producto p = null;
        if (c.moveToFirst()) {
            p = new Producto();
            p.setId(c.getInt(0));
            p.setName(c.getString(1));
            p.setPrice(c.getDouble(2));
            p.setStock(c.getInt(3));
            p.setBarcode(c.getString(4));
            p.setMinStock(c.getInt(5));
        }
        c.close();
        db.close();
        return p;
    }

    public Producto buscarPorBarcode(String barcode) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, name, price, stock, barcode, min_stock FROM products WHERE barcode=?",
                new String[]{barcode});
        Producto p = null;
        if (c.moveToFirst()) {
            p = new Producto();
            p.setId(c.getInt(0));
            p.setName(c.getString(1));
            p.setPrice(c.getDouble(2));
            p.setStock(c.getInt(3));
            p.setBarcode(c.getString(4));
            p.setMinStock(c.getInt(5));
        }
        c.close();
        db.close();
        return p;
    }

    public void actualizarStock(int id, int nuevoStock) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("stock", nuevoStock);
        db.update(Conexion.TABLA_PRODUCTS, cv, "id=?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public double valorTotalInventario() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        double total = 0;
        Cursor c = db.rawQuery("SELECT SUM(price*stock) FROM products", null);
        if (c.moveToFirst()) {
            total = c.getDouble(0);
        }
        c.close();
        db.close();
        return total;
    }

    public List<Producto> top5PorValor() {
        List<Producto> lista = new ArrayList<>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT id, name, price, stock, barcode, min_stock FROM products ORDER BY (price*stock) DESC LIMIT 5", null);
        while (c.moveToNext()) {
            Producto p = new Producto();
            p.setId(c.getInt(0));
            p.setName(c.getString(1));
            p.setPrice(c.getDouble(2));
            p.setStock(c.getInt(3));
            p.setBarcode(c.getString(4));
            p.setMinStock(c.getInt(5));
            lista.add(p);
        }
        c.close();
        db.close();
        return lista;
    }

    public List<Producto> productosBajoStock() {
        List<Producto> lista = new ArrayList<>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT id, name, price, stock, barcode, min_stock FROM products WHERE stock <= min_stock", null);
        while (c.moveToNext()) {
            Producto p = new Producto();
            p.setId(c.getInt(0));
            p.setName(c.getString(1));
            p.setPrice(c.getDouble(2));
            p.setStock(c.getInt(3));
            p.setBarcode(c.getString(4));
            p.setMinStock(c.getInt(5));
            lista.add(p);
        }
        c.close();
        db.close();
        return lista;
    }
}