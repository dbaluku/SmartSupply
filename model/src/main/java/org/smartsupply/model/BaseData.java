package org.smartsupply.model;


import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.smartsupply.model.enums.RecordStatus;

import javax.persistence.*;
import java.io.Serializable;

import static com.jarcommons.StringUtil.isNotBlankOrEmpty;

@MappedSuperclass
public class BaseData implements Serializable {

    private static final long serialVersionUID = 4814189275932180736L;

    private String id = null;
    private RecordStatus recordStatus = RecordStatus.ACTIVE;

    // if true, user is returned to blank form after saving
    private Boolean addAnother = false;

    public BaseData() {

    }

    public BaseData(String id) {
        this.id = id;
    }

    public BaseData(String id, RecordStatus recordStatus) {
        this(id);
        this.recordStatus = recordStatus;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "record_status", nullable = false)
    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public void setEmptyIdNull() {
        if (StringUtils.isEmpty(id) || StringUtils.isBlank(id))
            this.setId(null);
    }

    public Boolean idIsBlankOrEmpty() {
        return (StringUtils.isEmpty(this.getId()) || StringUtils.isBlank(this.getId()));
    }

    public Boolean idIsNOTBlankOrEmpty() {
        return !idIsBlankOrEmpty();
    }

    public Boolean hasNoId() {
        return !hasId();
    }

    public Boolean hasId() {
        return isNotBlankOrEmpty(id);
    }

    @Transient
    public Boolean getAddAnother() {
        return addAnother;
    }

    public boolean addAnother() {
        return addAnother;
    }

    public void setAddAnother(Boolean addAnother) {
        this.addAnother = addAnother;
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
        if (this.getClass() != obj.getClass())
            return false;
        BaseData other = (BaseData) obj;
        if (this.getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!this.getId().equals(other.getId()))
            return false;
        return true;
    }

    public static void copy(BaseData dest, BaseData source) {
        dest.setRecordStatus(source.getRecordStatus());
//        dest.setCreatedBy(source.getCreatedBy());
//        dest.setDeletedBy(source.getDeletedBy());
//        dest.setDateCreated(source.getDateDeleted());
    }

    public <T extends BaseData> void copyFrom(T source) {
        copy(this, source);
    }
}
