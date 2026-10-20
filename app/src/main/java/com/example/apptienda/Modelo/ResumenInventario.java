package com.example.apptienda.Modelo;

import java.util.List;

public class ResumenInventario {

    private double valorTotal;
    private List<Producto> top5;
    private List<Producto> alertas;

    public ResumenInventario(double valorTotal, List<Producto> top5, List<Producto> alertas) {
        this.valorTotal = valorTotal;
        this.top5 = top5;
        this.alertas = alertas;
    }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public List<Producto> getTop5() { return top5; }
    public void setTop5(List<Producto> top5) { this.top5 = top5; }

    public List<Producto> getAlertas() { return alertas; }
    public void setAlertas(List<Producto> alertas) { this.alertas = alertas; }
}