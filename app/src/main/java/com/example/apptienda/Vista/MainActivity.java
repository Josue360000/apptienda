package com.example.apptienda.Vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptienda.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgUniversidad = findViewById(R.id.imgUniversidad);


        Button btnProductos = findViewById(R.id.btnProductos);
        Button btnMovimientos = findViewById(R.id.btnMovimientos);
        Button btnResumen = findViewById(R.id.btnResumen);

        btnProductos.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ProductoActivity.class)));

        btnMovimientos.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, MovimientoActivity.class)));

        btnResumen.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ResumenActivity.class)));
    }
}