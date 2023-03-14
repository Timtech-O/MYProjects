package org.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer implements Runnable {

    private double walletBalance;
    public String name;
    private ArrayList<Product> cart;
    private LocalDateTime timeOfEntry;
    private static double amount = 0;
    private int itemsBought;
    Cashier cashier;
    Map<Product, Integer> carte;

    public Customer(String name, double walletBalance, LocalDateTime timeOfEntry) {
        this.name = name;
        this.walletBalance = walletBalance;
        this.cart = new ArrayList<>();
        this.timeOfEntry = timeOfEntry;
        this.itemsBought = 0;
        this.cashier = cashier;
        this.carte = new HashMap<>();
    }public Customer(String name, double walletBalance, LocalDateTime timeOfEntry, Cashier cashier) {
        this.name = name;
        this.walletBalance = walletBalance;
        this.cart = new ArrayList<>();
        this.timeOfEntry = timeOfEntry;
        this.itemsBought = 0;
        this.cashier = cashier;
        this.carte = new HashMap<>();
    }

    public void buyItem() {
        this.itemsBought++;
    }

    public int getItemsBought() {
        return itemsBought;
    }

    public String getName() {
        return name;
    }

    public synchronized short makePayment(double amount) {
        this.walletBalance -= amount;
        System.out.println(name + " has been debited " + amount);
        System.out.println(name + " balance is " + walletBalance);

        return 0;
    }

    public double getWalletBalance() {
        System.out.println(name + " balance is " + walletBalance);
        return walletBalance;
    }

    public void addToCart(Product product, int unit) {
        Product selectedProduct = new Product(product.getName(), product.getPrice(), unit, product.category);
        cart.add(selectedProduct);
        System.out.println(name + " added " + product.getName() + " to their cart!");

    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }

    public double getTotal() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        return total;
    }

    public LocalDateTime getTimeOfEntry() {
        return timeOfEntry;
    }

    public ArrayList<Product> getCart() {
        return cart;
    }
    public Map<Product, Integer> getCartee() {

        return carte;
    }
    public void addToCarte(Product product, int quantity) {
        Integer currentQuantity = carte.get(product);
        if (currentQuantity == null) {
            currentQuantity = 0;
        }
        carte.put(product, currentQuantity + quantity);
    }



    public int getCarte() {
        return cart.size();
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " is now shopping...");
            Thread.sleep(5000); // simulate shopping time
            System.out.println(name + " has finished shopping and is now at the checkout.");
            makePayment(getTotal());
            System.out.println(name + " has paid and is leaving the store.");
            cashier.processCustomer(this);
        } catch (InterruptedException e) {
            System.out.println(name + " was interrupted while shopping!");
        }
    }



    @Override
    public String toString() {
        return "Customer[name=" + name + ", walletBalance=" + walletBalance + ", itemsBought=" + getCart().size() + ", timeOfEntry=" + timeOfEntry + "]";
    }


}




