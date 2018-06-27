package main;

import mydomain.model.Inventory;
import mydomain.model.Product;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

public class App {

    public static void main(String []args){
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("Tutorial");
        try (PersistenceManager pm = pmf.getPersistenceManager()) {
            Transaction tx = pm.currentTransaction();
            try {
                tx.begin();
                Inventory inv = new Inventory("My Inventory");
                Product product = new Product("Sony Discman", "A standard discman from Sony", 49.99);
                inv.getProducts().add(product);
                persistToDB(pm, tx, inv);
            } finally {
                disconnectFromDbCheckIfConnIsAlive(pm, tx);
            }
        }
    }

    private static void persistToDB(PersistenceManager pm, Transaction tx, Inventory inv) {
        pm.makePersistent(inv);
        tx.commit();
    }

    private static void disconnectFromDbCheckIfConnIsAlive(PersistenceManager pm, Transaction tx) {
        if (tx.isActive()) {
            tx.rollback();
        }
        pm.close();
    }

}
