package com.example.apptienda.Modelo;

public class Movimiento {

    private int id;
    private int productId;
    private int qty;
    private String type;
    private String date;

    public Movimiento() {
    }

    public Movimiento(int id, int productId, int qty, String type, String date) {
        this.id = id;
        this.productId = productId;
        this.qty = qty;
        this.type = type;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}