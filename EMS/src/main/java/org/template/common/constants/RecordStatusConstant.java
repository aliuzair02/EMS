package org.template.common.constants;

public enum RecordStatusConstant {
    ACTIVE(1),
    ;

    private final Integer value;

    private RecordStatusConstant(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
