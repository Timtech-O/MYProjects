package org.example.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    Store store = new Store(100000, "Fish");

    @Test
    void tohirecashier() {
        Cashier cashier = new Cashier("Tope", "AD124", store  );
        Manager manager = new Manager("Tolu", store);
        manager.hireCashier(cashier);
        for(Cashier cashier1: manager.store.getCashierList()){
            assertTrue( cashier1.getName().equals("Tope"));
            assertFalse( cashier1.getName().equals("Topa"));
        }


    }







    @Test
    void layoffCashier() {
        Manager manager = new Manager("Tolu", store);
        Cashier cashier1 = new Cashier("Tope", "AD124", store  );
        Cashier cashier2 = new Cashier("Tolu", "AD129", store  );


        manager.hireCashier(cashier2);
        manager.hireCashier(cashier1);
        manager.layoffCashier(cashier1);
        for(Cashier cashier: manager.store.getCashierList()){
            assertTrue( cashier.getStaffID().equals("AD124"));
        }

    }

    @Test
    @DisplayName("I checked for store balance")
    void checkforstorebalance() {
        Store store = new Store(100000, "Fish");
        Manager manager = new Manager("Tolu", store);
        assertEquals(100000, manager.getBalance());
        assertNotEquals(200000, manager.getBalance());
    }

    @Test

    void toaddproduct(){
        Product product = new Product("Yam", 5000, 6, "Food");
        Manager manager = new Manager("Tolu", store);
        manager.addProductToStore(product);
        for(Product product1: manager.getProductList()){
            assertTrue( product1.getName().equals("Yam"));
            assertFalse( product1.getName().equals("Yams"));

        }
    }
    @Test
    void ifproductisadded(){
        Product product = new Product("Yam", 5000, 6, "Food");
        Product product1 = new Product("Yam", 5000, 6, "Food");
        Product product2 = new Product("Yam", 5000, 6, "Food");
        Product product3 = new Product("Yam", 5000, 6, "Food");

        Manager manager = new Manager("Tolu", store);
        manager.addProductToStore(product);
        manager.addProductToStore(product1);
        manager.addProductToStore(product2);
        manager.addProductToStore(product3);
        assertEquals( 4, store.getProductList().size());




    }
}