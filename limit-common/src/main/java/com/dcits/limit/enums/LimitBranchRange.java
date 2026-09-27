package com.dcits.limit.enums;

/** 限制机构范围 */
public enum LimitBranchRange {
    /** 所有机构 */
    A("A"),
    /** 本机构及下属机构 */
    B("B"),
    /** 当前机构 */
    C("C"),
    /** 开户机构及其上级机构 */
    D("D"),
    /** 总行 */
    F("F");

    private String value;

    private LimitBranchRange(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LimitBranchRange byValue(String value) {
        for (LimitBranchRange item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}