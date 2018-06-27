package mydomain.model;

import javax.jdo.annotations.PersistenceCapable;
import java.util.HashSet;
import java.util.Set;

@PersistenceCapable
public class Inventory
{
    String name = null;
    Set<Product> products = new HashSet<>();

    public Inventory(String name)
    {
        this.name = name;
    }

    public Set<Product> getProducts() {return products;}
}