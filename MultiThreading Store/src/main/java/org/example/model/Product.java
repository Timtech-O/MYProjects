package org.example.model;

public class Product {

    private int id;

    private String name;

    private double price;

    private int unit;
    String category;
    String productStatus;

    public Product( String name, double price, int unit,String category){

        this.name = name;
        this.price = price;
        this.unit = unit;
        this.category = category;
    }
   public Product(String name, String unit, String category) {
        this.name = name;
        this.unit = Integer.parseInt(unit);
        this.category = category;
    }
    public Product(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory(String category){return category;}

    public void setCategory(String category){this.category = category;}

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", unit=" + unit +
                ", category =" + category +
                '}';
    }
}

