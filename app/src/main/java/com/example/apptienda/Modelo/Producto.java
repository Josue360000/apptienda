package com.example.apptienda.Modelo;

public class Producto {

    private int id;
    private String name;
    private double price;
    private int stock;
    private String barcode;
    private int minStock;

    public Producto() {
    }

    public Producto(int id, String name, double price, int stock, String barcode, int minStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.barcode = barcode;
        this.minStock = minStock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public int getMinStock() { return minStock; }
    public void setMinStock(int minStock) { this.minStock = minStock; }

    public double getValorTotal() {
        return price * stock;
    }

    public boolean bajoStock() {
        return stock <= minStock;
    }
}