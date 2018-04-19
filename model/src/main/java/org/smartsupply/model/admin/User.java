package org.smartsupply.model.admin;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.Gender;
import org.smartsupply.model.util.MyStringUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static com.jarcommons.StringUtil.isNotBlankOrEmpty;
import static org.smartsupply.model.admin.UserType.MANAGER;
import static org.smartsupply.model.admin.UserType.ACCOUNTANT;

@Entity
@Table(name = "smartsupply_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseData implements Serializable {

    private static final long serialVersionUID = -2636551734081685042L;

    private String firstName;
    private String lastName;
    private Gender gender;
    private UserType userType;
    private String email;

    private List<JobTitle> jobTitles;
    private Branch branch;
    private List<Branch> headBranches;

    private String username;
    private String password;
    private String salt;
    private Set<Role> roles;


    private String clearTextPassword;
    private String currentPassword;
    private String profilePic;


    public User(String id, String firstName, String lastName, Gender gender) {
        setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public User(String id, String firstName, String lastName, Gender gender, String email) {
        setId(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public boolean hasNewPassword() {
        return MyStringUtil.isNotBlankOrEmpty(clearTextPassword);
    }

    public User() {

    }

    public User(String id) {
        this.setId(id);
    }

    public User(UserType userType) {
        this.setUserType(userType);
    }


    @Transient
    public String getRoleString() {

        String roleString = "";
        if (this.getRoles() != null)
            if (this.getRoles().size() > 0) {
                for (Role role : this.getRoles()) {
                    if (roleString.equals(""))
                        roleString = role.getName() + ", ";
                    else
                        roleString += role.getName() + ", ";
                }
                // remove the ", " at the end of the string
                roleString = roleString.substring(0, roleString.length() - 2);
            }
        return roleString;
    }

    @Transient
    public String getJobTitleString() {
        String jobTitleString = "";
        if (this.getJobTitles() != null)
            if (this.getJobTitles().size() > 0) {
                for (JobTitle jobTitle : this.getJobTitles()) {
                    if (jobTitleString.equals(""))
                        jobTitleString = jobTitle.getName() + ", ";
                    else
                        jobTitleString += jobTitle.getName() + ", ";
                }
                // remove the ", " at the end of the string
                jobTitleString = jobTitleString.substring(0,
                        jobTitleString.length() - 2);
            }
        return jobTitleString;
    }

    public User(String id, String username, String password, String salt) {
        super.setId(id);
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    @Column(name = "first_name", nullable = false, unique = true)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Transient
    public String getFullName() {
        String name = "";
        if (!StringUtils.isEmpty(this.getFirstName()) && !StringUtils.isBlank(this.getFirstName()))
            name = this.getFirstName().trim();

        if (!StringUtils.isEmpty(this.getLastName()) && !StringUtils.isBlank(this.getLastName()))
            name += StringUtils.isEmpty(name) ? this.getLastName() : " " + this.getLastName().trim();

        return name.trim();
    }



    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender", nullable = true)
    public Gender getGender() {
        return gender;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type", nullable = true)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @ManyToMany
    @JoinTable(name = "user_jobtitles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "jobtitle_id"))
    public List<JobTitle> getJobTitles() {
        return jobTitles;
    }

    public void setJobTitles(List<JobTitle> jobTitles) {
        this.jobTitles = jobTitles;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    @ManyToOne
    @JoinColumn(name = "org_unit_id", nullable = true)
    public Branch getBranch() {
        return branch;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "role_users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public synchronized Set<Role> getRoles() {
        synchronized (this) {
            return roles;
        }
    }

    public synchronized void setRoles(Set<Role> roles) {
        synchronized (this) {
            this.roles = roles;
        }
    }

    public synchronized void addRole(Role role) {
        synchronized (this) {
            if (roles == null) {
                roles = new HashSet<>();
            }

            if (!this.roles.contains(role)) {
                roles.add(role);
            }
        }
    }

    public synchronized void removeRole(Role role) {
        synchronized (this) {
            if (roles != null) {
                for (Role r : roles) {
                    if (r.getName().equals(role.getName())) {
                        roles.remove(role);
                        break;
                    }
                }
            }
        }
    }

    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Transient
    public String getClearTextPassword() {
        return clearTextPassword;
    }

    public void setClearTextPassword(String clearTextPassword) {
        this.clearTextPassword = clearTextPassword;
    }

    public List<Permission> findPermissions() {
        List<Permission> permissions = null;
        if (roles != null && roles.size() > 0) {
            permissions = new ArrayList<>();
            for (Role role : roles) {
                if (role.getPermissions() != null
                        && role.getPermissions().size() > 0) {
                    for (Permission perm : role.getPermissions()) {
                        permissions.add(perm);
                    }
                }
            }
        }
        return permissions;
    }

    @Transient
    public boolean hasAdministrativePrivileges() {
        if (roles != null) {
            for (Role role : roles) {
                if (role.isDefaultAdminRole()) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void copy(User dest, User source) {
        dest.firstName = source.firstName;
        dest.lastName = source.lastName;
        dest.gender = source.gender;

        dest.userType = source.userType;
        dest.jobTitles = source.jobTitles;

        //don't allow username and roles for administrator to be edited
        if (!source.isDefaultAdministrator()) {
            dest.username = source.username;
            dest.roles = source.roles;
        }

        dest.clearTextPassword = source.clearTextPassword;
        dest.branch = source.branch;
    }

    @Transient
    private boolean isDefaultAdministrator() {
        return isNotBlankOrEmpty(username) && username.equalsIgnoreCase("administrator");
    }


    @Override
    public <T extends BaseData> void copyFrom(T source) {
        copy(this, (User) source);
    }

    public static final Comparator<User> NAME_COMPARATOR = new Comparator<User>() {
        @Override
        public int compare(User t, User t2) {
            return t.getFullName().compareTo(t2.getFullName());
        }
    };

    @OneToMany(mappedBy = "head")
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Branch> getHeadBranches() {
        return headBranches;
    }

    public void setHeadBranches(List<Branch> headBranches) {
        this.headBranches = headBranches;
    }

    @OneToMany(mappedBy = "asstHead")


    public boolean loadsTreeView() {
        return hasAdministrativePrivileges() || (userType != null && Arrays.asList(MANAGER, ACCOUNTANT).contains(userType));
    }

    @Column(name = "profile_pic", nullable = true)
    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @Column(name = "email_address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
