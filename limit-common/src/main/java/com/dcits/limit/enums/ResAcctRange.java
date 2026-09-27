package com.dcits.limit.enums;

/** 限制账户范围 */
public enum ResAcctRange {
    /** 所有（主和子） */
    M("M"),
    /** 单层（主或子） */
    S("S");

    private String value;

    private ResAcctRange(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ResAcctRange byValue(String value) {
        for (ResAcctRange item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}