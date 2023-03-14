package org.example.model;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Queue {
    private PriorityQueue<Customer> fifoQueue;


    public Queue() {
        // Priority queue based on FIFO
        fifoQueue = new PriorityQueue<Customer>(Comparator.comparing(Customer::getTimeOfEntry));
    }

    public void add(Customer customer) {

        fifoQueue.add(customer);
    }

    public Customer getNext() {

        return fifoQueue.poll();
    }
}