package com.example.apptienda.Vista;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptienda.Controlador.MovimientoControlador;
import com.example.apptienda.Controlador.ProductoControlador;
import com.example.apptienda.Modelo.Producto;
import com.example.apptienda.R;

import java.util.ArrayList;
import java.util.List;

public class MovimientoActivity extends AppCompatActivity {

    private Spinner spProducto, spTipo;
    private EditText etCantidad;
    private MovimientoControlador movimientoControlador;
    private ProductoControlador productoControlador;
    private List<Producto> productos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        movimientoControlador = new MovimientoControlador(this);
        productoControlador = new ProductoControlador(this);

        spProducto = findViewById(R.id.spProducto);
        spTipo = findViewById(R.id.spTipo);
        etCantidad = findViewById(R.id.etCantidad);
        Button btnRegistrar = findViewById(R.id.btnRegistrarMovimiento);

        cargarProductos();

        ArrayAdapter<String> adapterTipo = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"ENTRADA", "SALIDA"});
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipo.setAdapter(adapterTipo);

        btnRegistrar.setOnClickListener(v -> registrar());
    }

    private void cargarProductos() {
        productos = productoControlador.listarProductos();
        List<String> nombres = new ArrayList<>();
        for (Producto p : productos) {
            nombres.add(p.getId() + " - " + p.getName() + " (stock " + p.getStock() + ")");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, nombres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProducto.setAdapter(adapter);
    }

    private void registrar() {
        if (productos == null || productos.isEmpty()) {
            Toast.makeText(this, "No hay productos registrados", Toast.LENGTH_SHORT).show();
            return;
        }
        int pos = spProducto.getSelectedItemPosition();
        Producto p = productos.get(pos);
        String cantStr = etCantidad.getText().toString().trim();
        if (cantStr.isEmpty()) {
            Toast.makeText(this, "Ingrese la cantidad", Toast.LENGTH_SHORT).show();
            return;
        }
        int qty;
        try {
            qty = Integer.parseInt(cantStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Cantidad invalida", Toast.LENGTH_SHORT).show();
            return;
        }
        if (qty <= 0) {
            Toast.makeText(this, "La cantidad debe ser mayor a cero", Toast.LENGTH_SHORT).show();
            return;
        }
        String tipo = spTipo.getSelectedItem().toString();
        String mensaje = movimientoControlador.registrarMovimiento(p.getId(), qty, tipo);
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
        etCantidad.setText("");
        cargarProductos();
    }
}