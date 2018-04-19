package org.smartsupply.model.search;

import org.smartsupply.model.enums.RecordStatus;

public class BaseSearchParams {
    private String ids;
    private Integer pageNo = 1;
    private RecordStatus recordStatus;

    public BaseSearchParams() {

    }

    public BaseSearchParams(String ids) {
        this.ids = ids;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public void initializePageNumber() {
        this.pageNo = (pageNo == null || pageNo <= 0) ? 1 : pageNo;
    }

    public static void copy(BaseSearchParams dest, BaseSearchParams source) {
        dest.ids = source.ids;
        dest.pageNo = source.pageNo;
        dest.recordStatus = source.recordStatus;
    }

    public <T extends BaseSearchParams> void copyTo(T dest) {
        copy(dest, this);
    }

    public boolean hasRecordStatus() {
        return recordStatus != null;
    }
}
