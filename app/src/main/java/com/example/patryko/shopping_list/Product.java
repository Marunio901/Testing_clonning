package com.example.patryko.shopping_list;

/**
 * Created by Patryko on 11/21/2017.
 */

public class Product {

    String name;
    int quantity;
    double price;

    public Product() {
    }

    public Product(String name, int quantity, double price) {

        this.name = name;
        this.quantity = quantity;
       this.price = price;
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
