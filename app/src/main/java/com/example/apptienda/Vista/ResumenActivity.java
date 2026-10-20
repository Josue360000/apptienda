package com.example.apptienda.Vista;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.Controlador.ResumenControlador;
import com.example.apptienda.Modelo.Producto;
import com.example.apptienda.Modelo.ResumenInventario;
import com.example.apptienda.R;

import java.util.ArrayList;
import java.util.List;

public class ResumenActivity extends AppCompatActivity {

    private TextView tvValorTotal;
    private TextView tvAlerta;
    private RecyclerView recyclerTop5, recyclerAlertas;
    private ProductoAdapter adapterTop5, adapterAlertas;
    private ResumenControlador controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        controlador = new ResumenControlador(this);

        tvValorTotal = findViewById(R.id.tvValorTotal);
        tvAlerta = findViewById(R.id.tvAlerta);
        recyclerTop5 = findViewById(R.id.recyclerTop5);
        recyclerAlertas = findViewById(R.id.recyclerAlertas);

        recyclerTop5.setLayoutManager(new LinearLayoutManager(this));
        recyclerAlertas.setLayoutManager(new LinearLayoutManager(this));

        adapterTop5 = new ProductoAdapter(null, null);
        adapterAlertas = new ProductoAdapter(null, null);
        recyclerTop5.setAdapter(adapterTop5);
        recyclerAlertas.setAdapter(adapterAlertas);

        cargarResumen();
    }

    private void cargarResumen() {
        ResumenInventario r = controlador.obtenerResumen();
        tvValorTotal.setText("Valor total del inventario: $" + String.format("%.2f", r.getValorTotal()));
        adapterTop5.setLista(r.getTop5());
        adapterAlertas.setLista(r.getAlertas());
        if (r.getAlertas() == null || r.getAlertas().isEmpty()) {
            tvAlerta.setText("Sin alertas de stock");
        } else {
            tvAlerta.setText("Alerta: " + r.getAlertas().size() + " producto(s) con stock minimo");
        }
    }
}