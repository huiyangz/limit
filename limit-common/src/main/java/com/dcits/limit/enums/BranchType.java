package com.dcits.limit.enums;

/** 机构类型 */
public enum BranchType {
    /** 本行 */
    I("I"),
    /** 他行 */
    O("O");

    private String value;

    private BranchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BranchType byValue(String value) {
        for (BranchType item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}