package org.smartsupply.model.admin;

import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.RecordStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// stores userTypes allowed to access the system on a specific URL
@Entity
@Table(name = "url_usertype")
public class UrlUserType extends BaseData {

    private Url url;
    private UserType userType;

    public UrlUserType() {

    }

    public UrlUserType(String id) {
        this.setId(id);
    }

    public UrlUserType(Url url, UserType userType) {
        this.url = url;
        this.userType = userType;
    }

    public UrlUserType(String id, RecordStatus recordStatus, Url url, UserType userType) {
        this(url, userType);
        this.setId(id);
        this.setRecordStatus(recordStatus);
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "url_id", nullable = false)
    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type", nullable = false)
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
