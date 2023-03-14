package org.example;

import org.example.model.Product;
import org.example.model.ProductExcel;
import org.example.model.Store;
import org.example.model.Customer;
import org.example.model.Cashier;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
//package org.example;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Store store = new Store(10, "store");
        Cashier cashier = new Cashier("Timo", "A2121", store);
        ProductExcel proo = new ProductExcel(store);
        proo.Excel("/Users/decagon/Documents/MyStoree - Copy/src/main/resources/Product Excel File/Product.xlsx");
        // create products
        Product product1 = store.getProductList().get(0);
        Product product2 = store.getProductList().get(1);
        Product product3 = store.getProductList().get(2);
        Product product4 = store.getProductList().get(4);
        Product product5 = store.getProductList().get(5);
        Product product6 = store.getProductList().get(6);
        Product product7 = store.getProductList().get(7);

        // create customers
        Customer c1 = new Customer("Alice", 100000.0, LocalDateTime.now(), cashier);
        Customer c2 = new Customer("Bob", 500000.0, LocalDateTime.now(), cashier);
        Customer c3 = new Customer("Charlie", 200000.0, LocalDateTime.now(), cashier);
        Customer c4 = new Customer("Dave", 1500000.0, LocalDateTime.now(), cashier);
        Customer c5 = new Customer("Tem", 2500000.0, LocalDateTime.now(), cashier);

        // add items to customer carts
        c1.addToCarte(product1, 5);
        c1.addToCarte(product2, 2);
        c2.addToCarte(product3, 3);
        c3.addToCarte(product4, 10);
        c4.addToCarte(product1, 1);
        c4.addToCarte(product2, 1);
        c4.addToCarte(product3, 1);
        c5.addToCarte(product3, 1);
        c5.addToCarte(product3, 1);
        c5.addToCarte(product3, 1);
        c5.addToCarte(product3, 1);
        c5.addToCarte(product3, 1);

        // create list of customers
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);

        Queue<Customer> customerQueue = new LinkedList<>(); // create a new blocking queue for customers
        customerQueue.addAll(customers); // add the existing list of customers to the queue


        Thread cashierThread = new Thread();
        cashierThread.start();
        List<Thread> customerThreads = new ArrayList<>();
        for (Customer customer : customers) {
            Thread customerThread = new Thread(customer);
            customerThreads.add(customerThread);
            customerThread.start();
            customerThread.join();
        }


        for (Customer customer : customers) {
            Thread customerThread = new Thread(() -> {
                while (!customer.getCart().isEmpty()) { // while there are items in the cart
                    int randomIndex = (int) (Math.random() * customer.getCartee().size()); // get a random index
                    Map.Entry<Product, Integer> entry = (Map.Entry<Product, Integer>) ((Object[]) customer.getCartee().entrySet().toArray())[randomIndex];

                    // get a random item from the cart
                    Product product = entry.getKey();
                    int quantity = entry.getValue();
                    double totalCost = product.getPrice() * quantity;
                    if (customer.getWalletBalance() >= totalCost) { // if the customer can afford it
                        customer.addToCarte(product, quantity);
                        System.out.println(customer.getName() + " bought " + quantity + " " + product.getName() + "(s) for $" + totalCost);
                    } else {
                        System.out.println(customer.getName() + " cannot afford to buy " + quantity + " " + product.getName() + "(s) for $" + totalCost);
                        break; // exit the loop if the customer cannot afford the item
                    }
                    try {
                        Thread.sleep(1000); // wait for 1 second between purchases
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            customerThreads.add(customerThread);
            customerThread.start();
        }

// wait for all customer threads to finish
        for (Thread customerThread : customerThreads) {
            try {
                customerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

        // create cashier
        //Cashier cashier = new Cashier("John", 0.0);

        // process customers using threads
        /*ExecutorService executorService = Executors.newFixedThreadPool(customers.size());
        for (Customer customer : customers) {
            executorService.execute(() -> customer.processCustomer(cashier));
        }
        executorService.shutdown();*/
    }


