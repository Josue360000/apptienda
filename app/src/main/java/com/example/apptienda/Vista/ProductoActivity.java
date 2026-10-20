package com.example.apptienda.Vista;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.Controlador.ProductoControlador;
import com.example.apptienda.Modelo.Producto;
import com.example.apptienda.R;

import java.util.List;

public class ProductoActivity extends AppCompatActivity {

    private EditText etNombre, etPrecio, etStock, etBarcode, etMinStock;
    private RecyclerView recycler;
    private ProductoAdapter adapter;
    private ProductoControlador controlador;
    private int idEditando = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        controlador = new ProductoControlador(this);

        etNombre = findViewById(R.id.etNombre);
        etPrecio = findViewById(R.id.etPrecio);
        etStock = findViewById(R.id.etStock);
        etBarcode = findViewById(R.id.etBarcode);
        etMinStock = findViewById(R.id.etMinStock);
        recycler = findViewById(R.id.recyclerProductos);
        Button btnGuardar = findViewById(R.id.btnGuardarProducto);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductoAdapter(this::seleccionarParaEditar, this::confirmarEliminar);
        recycler.setAdapter(adapter);

        btnGuardar.setOnClickListener(v -> guardarProducto());

        cargarLista();
    }

    private void guardarProducto() {
        String nombre = etNombre.getText().toString().trim();
        String precioStr = etPrecio.getText().toString().trim();
        String stockStr = etStock.getText().toString().trim();
        String barcode = etBarcode.getText().toString().trim();
        String minStr = etMinStock.getText().toString().trim();

        if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(precioStr)
                || TextUtils.isEmpty(stockStr) || TextUtils.isEmpty(minStr)) {
            Toast.makeText(this, "Complete los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        double precio;
        int stock;
        int minStock;
        try {
            precio = Double.parseDouble(precioStr);
            stock = Integer.parseInt(stockStr);
            minStock = Integer.parseInt(minStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valores numericos invalidos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (idEditando == -1) {
            long r = controlador.crearProducto(nombre, precio, stock, barcode, minStock);
            if (r > 0) {
                Toast.makeText(this, "Producto creado", Toast.LENGTH_SHORT).show();
                limpiar();
                cargarLista();
            } else {
                Toast.makeText(this, "Error al crear", Toast.LENGTH_SHORT).show();
            }
        } else {
            int r = controlador.actualizarProducto(idEditando, nombre, precio, stock, barcode, minStock);
            if (r > 0) {
                Toast.makeText(this, "Producto actualizado", Toast.LENGTH_SHORT).show();
                limpiar();
                cargarLista();
            } else {
                Toast.makeText(this, "Error al actualizar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void seleccionarParaEditar(Producto p) {
        idEditando = p.getId();
        etNombre.setText(p.getName());
        etPrecio.setText(String.valueOf(p.getPrice()));
        etStock.setText(String.valueOf(p.getStock()));
        etBarcode.setText(p.getBarcode());
        etMinStock.setText(String.valueOf(p.getMinStock()));
        Toast.makeText(this, "Editando producto", Toast.LENGTH_SHORT).show();
    }

    private void confirmarEliminar(Producto p) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar producto")
                .setMessage("Desea eliminar " + p.getName() + "?")
                .setPositiveButton("Si", (d, w) -> {
                    controlador.eliminarProducto(p.getId());
                    cargarLista();
                    Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void limpiar() {
        idEditando = -1;
        etNombre.setText("");
        etPrecio.setText("");
        etStock.setText("");
        etBarcode.setText("");
        etMinStock.setText("");
    }

    private void cargarLista() {
        List<Producto> lista = controlador.listarProductos();
        adapter.setLista(lista);
    }
}