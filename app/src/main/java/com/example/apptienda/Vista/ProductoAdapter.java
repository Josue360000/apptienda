package com.example.apptienda.Vista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptienda.Modelo.Producto;
import com.example.apptienda.R;

import java.util.ArrayList;
import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.VH> {

    public interface OnItemClick {
        void onClick(Producto p);
    }

    private List<Producto> lista = new ArrayList<>();
    private OnItemClick onEditar;
    private OnItemClick onEliminar;

    public ProductoAdapter(OnItemClick onEditar, OnItemClick onEliminar) {
        this.onEditar = onEditar;
        this.onEliminar = onEliminar;
    }

    public void setLista(List<Producto> nueva) {
        this.lista = nueva != null ? nueva : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Producto p = lista.get(position);
        h.tvNombre.setText(p.getName());
        h.tvDetalle.setText("Precio: $" + p.getPrice() + " | Stock: " + p.getStock()
                + " | Codigo: " + (p.getBarcode() == null ? "-" : p.getBarcode())
                + " | Valor: $" + String.format("%.2f", p.getValorTotal()));
        if (onEditar != null) {
            h.itemView.setOnClickListener(v -> onEditar.onClick(p));
        }
        if (onEliminar != null) {
            h.itemView.setOnLongClickListener(v -> {
                onEliminar.onClick(p);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDetalle;
        VH(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvItemNombre);
            tvDetalle = itemView.findViewById(R.id.tvItemDetalle);
        }
    }
}