package com.dcits.limit.enums;

/** 记录状态 */
public enum RecordStatus {
    /** 生效 */
    A("A"),
    /** 失效 */
    E("E");

    private String value;

    private RecordStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RecordStatus byValue(String value) {
        for (RecordStatus item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}