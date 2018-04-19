package org.smartsupply.model.product;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.smartsupply.model.BaseData;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "category")
public class Category extends BaseData implements Serializable {

    private static final long serialVersionUID = -2636551734031885042L;

    private String name;
    private String Description;
    private List<Product> products;

    public Category() {
    }

    public Category(String id) {
        super(id);
    }

    public Category(String id, String name, String description) {
        super(id);
        this.name = name;
        Description = description;
    }

    public Category(String id, String name, String description, List<Product> products) {
        this(id,name,description);
        this.products = products;
    }

    @Column(name = "category_name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "category_description", nullable = false)
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
