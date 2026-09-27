package com.dcits.limit.enums;

/** 限制状态 */
public enum RestraintsStatus {
    /** 已批准 */
    A("A"),
    /** 已终止 */
    E("E"),
    /** 未生效 */
    F("F");

    private String value;

    private RestraintsStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RestraintsStatus byValue(String value) {
        for (RestraintsStatus item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}