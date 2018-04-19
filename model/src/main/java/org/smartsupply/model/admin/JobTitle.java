package org.smartsupply.model.admin;

import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.RecordStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "job_title")
public class JobTitle extends BaseData {

    private static final long serialVersionUID = 5507121566714139358L;

    private String name;
    private String description;

    public JobTitle() {

    }

    public JobTitle(String id) {
        super(id);
    }

    public JobTitle(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public JobTitle(String id, RecordStatus recordStatus, String name, String description) {
        this(name, description);
        this.setId(id);
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

    public static void copy(JobTitle dest, JobTitle source) {
        dest.name = source.name;
        dest.description = source.description;
        BaseData.copy(dest, source);
    }

    @Override
    public <T extends BaseData> void copyFrom(T source) {
        copy(this, (JobTitle) source);
    }
}
