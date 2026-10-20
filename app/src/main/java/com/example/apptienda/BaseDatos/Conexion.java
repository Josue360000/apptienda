package com.example.apptienda.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "tienda.db";
    private static final int VERSION = 1;

    public static final String TABLA_PRODUCTS = "products";
    public static final String TABLA_MOVEMENTS = "movements";

    public Conexion(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "price REAL NOT NULL, " +
                "stock INTEGER NOT NULL, " +
                "barcode TEXT, " +
                "min_stock INTEGER NOT NULL DEFAULT 5)");

        db.execSQL("CREATE TABLE movements (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "product_id INTEGER NOT NULL, " +
                "qty INTEGER NOT NULL, " +
                "type TEXT NOT NULL, " +
                "date TEXT NOT NULL, " +
                "FOREIGN KEY(product_id) REFERENCES products(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movements");
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }
}