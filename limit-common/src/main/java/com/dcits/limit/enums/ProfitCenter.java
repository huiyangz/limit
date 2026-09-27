package com.dcits.limit.enums;

/** 利润中心 */
public enum ProfitCenter {
    /** 利润中心 */
    VALUE_99("99");

    private String value;

    private ProfitCenter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProfitCenter byValue(String value) {
        for (ProfitCenter item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}