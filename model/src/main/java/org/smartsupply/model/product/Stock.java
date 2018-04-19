package org.smartsupply.model.product;

import org.smartsupply.model.BaseData;
import org.smartsupply.model.admin.Branch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stock")
public class Stock extends BaseData implements Serializable {
    private static final long serialVersionUID = -2636551734031285042L;

    private String name;
    private List<StockProduct> products;


    public Stock() {}

    public Stock(String id) {
        super(id);
    }
    public Stock (String name,boolean isName){
       if(isName)
       {this.name= name;}
       else{
           new Stock(name);
       }

    }

    public Stock(String id, String name) {
        super(id);
        this.name = name;
    }

    public Stock(String name, List<StockProduct> products) {
        this.name = name;
        this.products = products;
    }

    public Stock(String id, String name, List<StockProduct> products) {
        super(id);
        this.name = name;
        this.products = products;
    }

    @Column(name="name",nullable = false, unique = true)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Transient
    public List<StockProduct> getProducts() {
        return products;
    }


    public void setProducts(List<StockProduct> products) {
        this.products = products;
        for (StockProduct t : products) {
            t.setStock(this);
        }
    }
}
