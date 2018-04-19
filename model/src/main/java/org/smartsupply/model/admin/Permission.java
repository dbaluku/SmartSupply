package org.smartsupply.model.admin;

import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.RecordStatus;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "permission")
public class Permission extends BaseData implements Comparable<Permission> {

    private static final long serialVersionUID = 6123746187610798753L;
    private String name;
    private String description;

    public Permission() {
    }

    public Permission(String id, String name, String description) {
        this.setId(id);
        this.name = name;
        this.description = description;
    }

    public Permission(String id, RecordStatus recordStatus, String name, String description) {
        this(id, name, description);
        this.setRecordStatus(recordStatus);
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Permission other = (Permission) obj;

        if (this.getName() == null) {
            if (other.getName() == null)
                return false;
        } else if (!this.getName().equalsIgnoreCase(other.getName()))
            return false;

        return true;
    }

    @Override
    public int compareTo(Permission o) {

        return getObjectNameFromPermisionName(this).compareToIgnoreCase(
                getObjectNameFromPermisionName(o));
    }

    // @return the name of Object that the permission is associated with
    public static String getObjectNameFromPermisionName(Permission p) {
        String subString = "";
        if (p.getName().indexOf('_') != -1)
            subString = p.getName().substring(p.getName().indexOf('_') + 1);
        else
            return p.getName();

        if (subString.indexOf('_') != -1)
            return subString.substring(subString.indexOf('_') + 1);
        else
            return subString;
    }

    public static final Comparator<Permission> NAME_ORDER = new Comparator<Permission>() {
        @Override
        public int compare(Permission p1, Permission p2) {
            return p1.getName().compareTo(p2.getName());
        }
    };

    @Transient
    public String getDisplayName() {
        return name == null ? "" : name.toLowerCase().replace("perm_", "").replace("_", " ");
    }
}
