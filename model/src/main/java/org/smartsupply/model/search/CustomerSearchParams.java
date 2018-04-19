package org.smartsupply.model.search;

import org.smartsupply.model.admin.Branch;

public class CustomerSearchParams extends BaseSearchParams{
    private Branch branch;
    private String phoneNo;

    public CustomerSearchParams() {
    }

    public CustomerSearchParams(String phoneNo, Branch branch) {
        this.phoneNo = phoneNo;
        this.branch = branch;
    }

    public CustomerSearchParams(Branch branch) {
        this.branch = branch;
    }


    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
