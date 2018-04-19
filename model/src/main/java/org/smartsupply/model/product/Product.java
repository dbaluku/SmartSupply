package org.smartsupply.model.product;

import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.ProductType;
import org.smartsupply.model.enums.QuantityType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseData implements Serializable {
    private static final long serialVersionUID = -2636551734031985042L;

    private String name;
    private ProductType productType;
    private float unitprice;
    private QuantityType quantityType;
  //  private Category category;


    public Product() {
    }

    public Product(String id) {
        super(id);
    }



    public Product(String id, String name, ProductType productType, float unitprice) {
        super(id);
        this.name = name;
        this.productType = productType;
        this.unitprice = unitprice;
    }

    public Product(String id, String name, ProductType productType, float unitprice, QuantityType quantityType) {
        super(id);
        this.name = name;
        this.productType = productType;
        this.unitprice = unitprice;
        this.quantityType = quantityType;
    }

    public Product(String name, ProductType productType, float unitprice) {
        this.name = name;
        this.productType = productType;
        this.unitprice = unitprice;
    }


    @Column(name = "product_name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "product_type", nullable = false)
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Column(name = "unit_price", nullable = false)
    public float getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(float unitprice) {
        this.unitprice = unitprice;
    }

    @Column(name = "quantity_type", nullable = false)
    public QuantityType getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(QuantityType quantityType) {
        this.quantityType = quantityType;
    }

//    @ManyToOne()
//    @JoinColumn(name = "category_id", nullable = true)
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

}
