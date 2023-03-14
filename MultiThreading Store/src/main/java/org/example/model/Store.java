package org.example.model;

import java.util.ArrayList;

import java.util.List;

public class Store {

    private double storeBalance;
    public String name;
    public List<Product> productList;
   public List<Cashier> cashierList;
    public Cashier cashier;

    private Manager manager;




    public Store(double storeBalance, String name) {
        this.storeBalance = storeBalance;
        this.name = name;
        this.productList = new ArrayList<>();
        this.cashierList = new ArrayList<>();
    }
    public Store(){

    }

    public Product getProduct(String name) {
        for (Product product : productList) {
            if (product.getName().equals(name))
                return product;
        }
        return null;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(Product product) {
        this.productList.add(product);
    }

    public List<Cashier> getCashierList() {
        return cashierList;
    }

    public void setCashierList(Cashier cashier) {
        this.cashierList.add(cashier);
    }

    public double getStoreBalance() {
        return storeBalance;
    }

    public void setStoreBalance(double storeBalance) {
        this.storeBalance = storeBalance;
    }



}