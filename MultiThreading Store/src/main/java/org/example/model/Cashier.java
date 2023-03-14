package org.example.model;

import java.time.LocalDateTime;
import java.util.*;

public class Cashier extends Staff /*implements CashierService*/ {

    private String staffID;

    private String name;
    private  Customer customer;
    private Store store;
    private PriorityQueue<Customer> fifoQueue;
    private PriorityQueue<Customer> numberOfItems;
    private List<Customer> attendedCustomers;
    int maxItemsPerCustomer;


    public Cashier() {
        fifoQueue = new PriorityQueue<Customer>(Comparator.comparing(Customer::getTimeOfEntry));
        this.numberOfItems =  new PriorityQueue<Customer>(Comparator.comparing(Customer::getCarte).reversed());
    }

    public PriorityQueue<Customer> getFifoQueue() {
        return fifoQueue;
    }

    public void setFifoQueue(PriorityQueue<Customer> fifoQueue) {
        this.fifoQueue = fifoQueue;

    }

    public PriorityQueue<Customer> getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(PriorityQueue<Customer> numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public void addCustomer(Customer customer) {
        fifoQueue.add(customer);
    }



    public void serveCustomerssFifo() {
        while (!fifoQueue.isEmpty()) {
            Customer customer = fifoQueue.poll();
            try {
                System.out.println("Serving " + customer.toString());
                for (Product item : customer.getCart()) {
                    System.out.println("item: " + item.getName() +   " Unit: " + item.getUnit());
                }
                numberOfItems.offer(customer);
                processCustomer(customer); // call processCustomer() after serving the customer
            } catch (Exception e) {
                System.err.println("Error serving customer: " + customer.getName() + ". Error message: " + e.getMessage());
            }
        }
    }

    public void serveCustomers() {
        List<Customer> customers = new ArrayList<>(fifoQueue);
        Collections.sort(customers, Comparator.comparingInt(Customer::getCarte).reversed());
        for (Customer customer : customers) {
            System.out.println("Serving " + customer.toString());
            for (Product item : customer.getCart()) {
                System.out.println("item: " + item.getName() + " Unit: " + item.getUnit());
            }
            processCustomer(customer); // call processCustomer() after serving the customer
        }
    }







    /*public void attendCustomer()  {
        if (customerQueue.isEmpty()) {
            throw new EmptyQueueException("No customers in queue");
        }

        Customer customer = customerQueue.poll();
        sellProduct(customer);*/
    public void printMostValuableCustomers(int numCustomers) {
        System.out.println("Most valuable customers:");
        int count = 0;
        while (!numberOfItems.isEmpty() && count < numCustomers) {
            try{
            Customer customer = numberOfItems.poll();
            System.out.println(customer.toString() + " spent " + customer.getTotal());
            count++;
            } catch (Exception e) {
                System.err.println("Error printing most valuable customers. Error message: " + e.getMessage());
            }
        }
    }



    public Cashier(String name, Long id,  String sex){
        super(name, id, sex);

    }
    public Cashier(String name, String staffID, Store store) {
        this.name = name;
        this.staffID = staffID;
        this.store = store;
    }

    /*public Cashier(){

    }*/

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void updateProductQuantity(Product product, int unit){
        for(Product storeProduct : store.getProductList()){
            if(storeProduct.getName().equals(product.getName())){
                storeProduct.setUnit(storeProduct.getUnit() - unit);
                break;
            }
        }
    }

    public void checkOut(Customer customer){
        double total = 0.0;

        System.out.println();
        for (Product item : customer.getCart()){
            System.out.println("item: " + item.getName() + " Unit" );
            total += item.getPrice() * item.getUnit();
            updateProductQuantity(item, item.getUnit());
        }
        System.out.println("Cart Total: "+ total);
        if(customer.getWalletBalance() >= total){
            customer.makePayment(total);
            store.setStoreBalance(store.getStoreBalance() + total);

            issueReceipt(customer, customer.getCart(), total);
        }else{
            System.err.println("Insufficient funds");
        }

    }




    private int getTotalItems(Customer customer) {
        int total = 0;
        for (Product product : customer.getCart()) {
            total += 1;
        }
        return total;
    }

    private  void issueReceipt(Customer customer, ArrayList<Product> products, double totalPrice){
        System.out.println();
        System.out.println("RECEIPT");
        System.out.println("Name: " + customer.getName());
        for (Product item : products)
            System.out.println(item.toString());
        System.out.println("Total: " + totalPrice);
        System.out.println("Attended to by: " + getName());
        customer.getCart().clear();
        //customersAttendedTo++;



    }
    public synchronized void processCustomer(Customer customer) {
        double totalCost = customer.getTotal();
        double customerMoney = customer.getWalletBalance();
        if (totalCost > customerMoney) {
            System.out.println("Error: " + customer.getName() + " does not have enough money to pay for their items.");
            return;
        }
        double balance = customerMoney - totalCost;
        System.out.println("Processing customer: " + customer.getName() + ", total items: " + customer.getCart().size() + ", total cost: " + totalCost);
        System.out.println("Receipt:");
        for (Product product : customer.getCart()) {
            System.out.println(product.getName() + "\t\t" + product.getPrice());
        }
        System.out.println("Total:\t\t" + totalCost);
        System.out.println("Customer paid:\t" + customerMoney);
        System.out.println("Balance:\t" + balance);
        System.out.println("Time of entry: " + customer.getTimeOfEntry() + ", time of checkout: " + LocalDateTime.now());
    }

  public List<Customer> getAttendedCustomers() {
          return attendedCustomers;
      }

}

