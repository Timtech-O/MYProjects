package org.example.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NumberOfItemsQueue {
    private PriorityQueue<Customer> numberOfItems;

    public NumberOfItemsQueue() {
        numberOfItems = new PriorityQueue<Customer>(Comparator.comparing(Customer::getCarte).reversed());
    }

    public void add(Customer customer) {
        numberOfItems.add(customer);
    }

    public Customer getNext() {
        return  numberOfItems.poll();
    }
}
