package org.smartsupply.model.cia;

import org.smartsupply.model.admin.User;

import java.util.Date;

//@Entity
//@Table(name = "login")
public class Login {
    private String ipAddress;
    private User user;
    private Date dateIn;
    private Date dateOut;

    public Login(){
        super();
    }

    public Login(String ipAddress, User user){
        this.ipAddress = ipAddress;
        this.user = user;
        this.dateIn = new Date();
    }
}
