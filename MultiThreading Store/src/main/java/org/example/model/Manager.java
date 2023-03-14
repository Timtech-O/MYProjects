package org.example.model;

import org.example.Interface.IManager;

import java.util.List;

public class Manager extends Staff implements IManager {






    public Store store;
    public Cashier cashier;



    public Manager(String name, Long id, String sex   ) {

        super(name, id, sex);



    }
    public Manager(String name, Store store ) {

        super(name);
        this.store = store;


    }
    public Manager(){

    }


    @Override
    public void hireCashier(Cashier cashier){
        store.setCashierList(cashier);
        System.out.println("You have hired " + cashier.getName());
        System.out.println();
    }

    @Override
    public void layoffCashier(Cashier cashier) {
        List<Cashier> cashiers = store.getCashierList();
        for(Cashier c : cashiers){
            if(c.getName().equals((c.getName()))){
                cashiers.remove(c);
                System.out.println("You fired " + cashier.getName() + " " + " with Staff ID: " +cashier.getStaffID());
            } else{
                System.err.println(cashier.getName() + " is not a member of staff.");
            }
        }

    }

    public void addProductToStore(Product product){
        store.setProductList(product);
        System.out.println(product.getUnit()+ " units of "+product.getName()+ " added to store");
    }
    public double getBalance(){
        return store.getStoreBalance();
    }
    public List<Product> getProductList(){
        return store.getProductList();
    }


}





