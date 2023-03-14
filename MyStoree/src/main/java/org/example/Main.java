package org.example;

import org.example.model.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Store store = new Store(10,"store");
        ProductExcel proo = new ProductExcel(store);
        proo.Excel(MyFilePath.FILE_PATH);



        for (Product product : store.getProductList()) {
            System.out.println(product);
        }
        System.out.println("Hello world!");
        Store store1 = new Store(100000, "Fish");
        Manager manager = new Manager("Tolu", store);
        Product Milo = new Product("Milo", 5000, 30, "Food");
        Product goldenMorn = new Product("Golden Morn", 2000, 30,"Food");
        Product cornFlakes = new Product ("CornFlakes", 2500, 30,  "Food");



        manager.addProductToStore(Milo);
        manager.addProductToStore(goldenMorn);
        manager.addProductToStore(cornFlakes);

        Cashier cashier = new Cashier("Tolu", "DA714", store);
        manager.hireCashier(cashier);
        Cashier cashier1 = new Cashier("Tola", "AA201", store);
        manager.hireCashier(cashier1);


        System.out.println("--------------------------------------------");
        //cashier.updateProductQuantity(rice, 5);
        System.out.println( store.getProductList().size());


        Customer Tope = new Customer ("Tope", 20000000);
        Customer Tunji = new Customer ("Tunji", 2200000);
        Customer Timo = new Customer ("Timo", 2400000);



        Tope.addToCart(Milo, 30);
        Tope.addToCart(goldenMorn, 30);
        System.out.println();
        System.out.println(Tope.getCart().size());
        System.out.println("--------------------------------------------------------------------");
        Tunji.addToCart(cornFlakes, 10);
        Tunji.addToCart(goldenMorn, 8);
        System.out.println(Tunji.getCart().size());
        System.out.println("--------------------------------------------------------------------");



        Timo.addToCart(Milo, 10);
        Timo.addToCart(cornFlakes, 10);
        System.out.println("----------------------------------------------------------------------");
        System.out.println(Timo.getCart().size());



        cashier.checkOut(Tope);
        cashier1.checkOut(Tunji);
        cashier1.checkOut(Timo);
        System.out.println("--------------------------------------------------------------------");

        System.out.println();
        System.out.println(manager.getBalance());
        System.out.println(store.getCashierList().size());
        manager.layoffCashier(cashier1);
        System.out.println(store.getCashierList().size());
        System.out.println("--------------------------------------------------------------------");
    }
}