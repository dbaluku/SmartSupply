package org.smartsupply.model.enums;

public enum RecordStatus {
    ACTIVE(0),
    DELETED(1);

    RecordStatus(Integer ordinal){
        this.ordinal= ordinal;
    }
    private Integer ordinal;

    public Integer getOrdinal() {
        return ordinal;
    }

    public static RecordStatus getByOrdinal(int ordinal) {
        for(RecordStatus recordStatus: RecordStatus.values()){
            if(recordStatus.getOrdinal().equals(ordinal))
                return recordStatus;
        }
        return null;
    }
}
