package org.smartsupply.model.customer;

import org.smartsupply.model.BaseData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "customer")
public class Customer extends BaseData implements Serializable {
    private static final long serialVersionUID = -2636551734031085042L;


    private String name, email;
    private String phoneNo;
    private String Address;
    private String CustomerNo;

    public Customer() {
    }

    public Customer(String name, String email, String phoneNo, String address, String customerNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        Address = address;
        CustomerNo = customerNo;
    }

    public Customer(String id) {
        super(id);
    }

    public Customer(String id, String name, String email, String phoneNo, String address, String customerNo) {
        super(id);
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        Address = address;
        CustomerNo = customerNo;
    }

    @Column(name = "customer_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", nullable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "phoneno", nullable = true)
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "adress", nullable = true)
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Column(name = "customer_no", nullable = true)
    public String getCustomerNo() {
        return CustomerNo;
    }

    public void setCustomerNo(String customerNo) {
        CustomerNo = customerNo;
    }

}
