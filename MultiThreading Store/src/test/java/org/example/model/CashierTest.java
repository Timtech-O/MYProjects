package org.example.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CashierTest {
    //Store store = new Store(100000, "Topu");


    private Store store;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;
    private Product product13;
    private Cashier cashier;
    private  Product product;

    @Before
    public void setUp() throws IOException {
        Cashier cashier = new Cashier("Tope", "AD214", store);
        Product product = new Product("Yam", 2000, 5, "Food");
        Customer customer = new Customer("Tolu", 200000, LocalDateTime.of(2023, 2, 17, 13, 0));
        store = new Store(100000, "Topu");
        ProductExcel proo = new ProductExcel(store);
        proo.Excel("/Users/decagon/Documents/MyStoree - Copy/src/main/resources/Product Excel File/Product.xlsx");
        product1 = store.getProductList().get(0);
        product2 = store.getProductList().get(1);
        product3 = store.getProductList().get(2);
        product4 = store.getProductList().get(3);
        product5 = store.getProductList().get(4);
        product13 = store.getProductList().get(12);
    }





    @Test
    void getName() {
        Store store = new Store(100000, "Topu");
        Cashier cashier = new Cashier("Tope", "AD214", store);
        assertTrue(cashier.getName().equals("Tope"));
        assertFalse(cashier.getName().equals("Topi"));
    }

    @Test
    void getStaffID() {

        assertTrue(cashier.getStaffID().equals("AD214"));
        assertFalse(cashier.getStaffID().equals("AD213"));
    }

    @Test
    void update() {

        cashier.updateProductQuantity(product, 5);

        assertEquals(0, store.getProductList().size());
        assertNotEquals(1, store.getProductList().size());
    }


        @Test
        public void testAddCustomer() {
            Cashier cashier = new Cashier();
            Customer customer1 = new Customer("Tope", 20000000, LocalDateTime.of(2023, 2, 18, 10, 0));
            Customer customer2 = new Customer("Tunji", 2200000, LocalDateTime.of(2023, 2, 17, 10, 5));
            cashier.addCustomer(customer1);
            cashier.addCustomer(customer2);
            assertEquals(cashier.getFifoQueue().size(), 2);
        }

    @Test
    public void testServeCustomers() throws IOException {
        store = new Store(100000, "Topu");
        //Queue<Customer> fifoQueue = new LinkedList<>();

        ProductExcel proo = new ProductExcel(store);
        proo.Excel("/Users/decagon/Documents/MyStoree - Copy/src/main/resources/Product Excel File/Product.xlsx");
        Cashier cashier = new Cashier();

        Product product1 = store.getProductList().get(0);
        Product product2 = store.getProductList().get(1);
        Product product3 = store.getProductList().get(2);

        store.setProductList(product1 );
        store.setProductList(product2);
        Customer customer1 = new Customer("Tope", 20000000, LocalDateTime.of(2023, 2, 17, 10, 8));
        customer1.addToCarte(product1 , 2);
        customer1.addToCarte(product2, 7);
        customer1.addToCarte(product3, 7);
        Customer customer2 = new Customer("Tunji", 2200000, LocalDateTime.of(2023, 2, 17, 10, 5));
        Customer customer3 = new Customer("Tunji", 2200000, LocalDateTime.of(2023, 2, 17, 10, 5));
        customer2.addToCarte(product3, 1);
        cashier.addCustomer(customer1);
        cashier.addCustomer(customer2);
        cashier.addCustomer(customer3);
        cashier.serveCustomerssFifo();
        assertEquals(cashier.getNumberOfItems().size(), 3);
    }

    @Test
    public void testServeCustomersOrder() throws IOException {
        store = new Store(100000, "Topu");
        ProductExcel proo = new ProductExcel(store);
        proo.Excel("/Users/decagon/Documents/MyStoree - Copy/src/main/resources/Product Excel File/Product.xlsx");
        Cashier cashier = new Cashier();
        //Store store = new Store(100000, "Topu");
        Product product1 = store.getProductList().get(0);
        Product product2 = store.getProductList().get(1);
        Product product3 = store.getProductList().get(2);
        Product product4 = store.getProductList().get(3);
        Product product5 = store.getProductList().get(4);
        Product product13 = store.getProductList().get(12);
        store.setProductList(product1);
        store.setProductList(product3);
        Customer customer1 = new Customer("Tope", 20000000, LocalDateTime.of(2023, 2, 18, 10, 0));
        customer1.addToCarte(product4, 2);
        customer1.addToCarte(product5 , 2);
        customer1.addToCarte(product13, 2);
        customer1.addToCarte(product2, 2);
        customer1.addToCarte(product3, 2);
        Customer customer2 = new Customer("Tunji", 2200000, LocalDateTime.of(2023, 2, 17, 10, 5));
        customer2.addToCarte(product5, 1);
        customer2.addToCarte(product2, 2);
        customer2.addToCarte(product13, 2);
        Customer customer3 = new Customer("Timo", 2400000, LocalDateTime.of(2023, 2, 17, 12, 0));
        customer3.addToCarte(product1, 1);
        customer3.addToCarte(product5, 2);

        cashier.addCustomer(customer1);
        cashier.addCustomer(customer2);
        cashier.addCustomer(customer3);
        cashier.serveCustomers();

        // Check the order in which customers were attended by the cashier
        List<Customer> servedCustomers = cashier.getAttendedCustomers();
        if (servedCustomers != null) {
            assertEquals(customer1, servedCustomers.get(0));
            assertEquals(customer2, servedCustomers.get(1));
            assertEquals(customer3, servedCustomers.get(2));
        }
    }
}