package org.smartsupply.model.search;



import org.smartsupply.model.admin.Branch;
import org.smartsupply.model.admin.Role;
import org.smartsupply.model.admin.UserType;
import org.smartsupply.model.enums.Gender;

import java.util.List;

public class UserSearchParams extends BaseSearchParams {
    private String nameOrUserName;
    private Branch branch = null;
    private Gender gender = null;
    private Role role = null;
    private UserType userType = null;
    private List<UserType> userTypes;
    private String email;

    public UserSearchParams() {

    }

    public UserSearchParams(String ids, boolean isIdString) {
        this.nameOrUserName = isIdString ? null : ids;
        this.setIds(isIdString ? ids : null);
    }

    public UserSearchParams(Branch branch) { this.branch = branch; }

    public UserSearchParams(List<UserType> userTypes) {
        this.userTypes = userTypes;
    }

    public UserSearchParams(UserType userType) {
        this.userType = userType;
    }

    public String getNameOrUserName() {
        return nameOrUserName;
    }

    public void setNameOrUserName(String nameOrUserName) {
        this.nameOrUserName = nameOrUserName;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    public List<UserType> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(List<UserType> userTypes) {
        this.userTypes = userTypes;
    }

    public boolean hasUserTypes() {
        return userTypes != null && userTypes.size() > 0;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
