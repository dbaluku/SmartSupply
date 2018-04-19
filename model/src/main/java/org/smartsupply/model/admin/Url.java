package org.smartsupply.model.admin;

import com.jarcommons.StringUtil;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.RecordStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

// stores url's on which the system is served-up
@Entity
@Table(name = "url")
public class Url extends BaseData {

    private String url;
    private String userTypesStr;

    public Url() {

    }

    public Url(String id, RecordStatus recordStatus, String urlString, List<UserType> userTypes) {
        this.setId(id);
        this.setRecordStatus(recordStatus);
        this.url = urlString;
        this.setUserTypes(userTypes);
    }

    public Url(String urlString) {
        this.url = urlString;
    }

    @Column(name = "url", nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "user_types_str", nullable = false)
    public String getUserTypesStr() {
        return userTypesStr;
    }

    public void setUserTypesStr(String userTypesStr) {
        this.userTypesStr = userTypesStr;
    }

    @Transient
    public List<UserType> getUserTypes() {
        if (StringUtil.isBlankOrEmpty(userTypesStr)) {
            return new ArrayList<>();
        }
        String[] types = userTypesStr.split(",");
        List<UserType> userTypes = new ArrayList<>();
        for (String t : types) {
            try {
                if (!UserType.getOrdinals().contains(t)) {
                    continue;
                }
                UserType userType = UserType.get(Integer.parseInt(t));
                if (!userTypes.contains(userType)) {
                    userTypes.add(userType);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userTypes;
    }

    @Transient
    public void setUserTypes(List<UserType> userTypes) {
        String types = "";
        userTypes = userTypes == null ? new ArrayList<>() : userTypes;
        for (UserType t : userTypes) {
            types += types == "" ? t.getOrdinal() : "," + t.getOrdinal();
        }
        this.userTypesStr = types;
    }

    @Transient
    public String getUserTypeString() {
        String userTypeString = "";
        for (UserType userType : this.getUserTypes()) {
            userTypeString += userTypeString == "" ? userType.getName() : ", " + userType.getName();
        }
        return userTypeString;
    }

    public static void copy(Url dest, Url source) {
        dest.setUrl(source.url);
        dest.userTypesStr = source.userTypesStr;
        BaseData.copy(dest, source);
    }

    @Override
    public <T extends BaseData> void copyFrom(T source) {
        copy(this, (Url) source);
    }
}
