package com.example.apptienda.BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apptienda.Modelo.Movimiento;

import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO {

    private Conexion conexion;

    public MovimientoDAO(Context context) {
        conexion = new Conexion(context);
    }

    public long insertar(Movimiento m) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("product_id", m.getProductId());
        cv.put("qty", m.getQty());
        cv.put("type", m.getType());
        cv.put("date", m.getDate());
        long id = db.insert(Conexion.TABLA_MOVEMENTS, null, cv);
        db.close();
        return id;
    }

    public List<Movimiento> listar() {
        List<Movimiento> lista = new ArrayList<>();
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT id, product_id, qty, type, date FROM movements ORDER BY id DESC", null);
        while (c.moveToNext()) {
            Movimiento m = new Movimiento();
            m.setId(c.getInt(0));
            m.setProductId(c.getInt(1));
            m.setQty(c.getInt(2));
            m.setType(c.getString(3));
            m.setDate(c.getString(4));
            lista.add(m);
        }
        c.close();
        db.close();
        return lista;
    }
}