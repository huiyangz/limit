package com.dcits.limit.enums;

/** 机构层级代码 */
public enum HierarchyCode {
    /** 虚拟机构层级 */
    NEGATIVE_1("-1"),
    /** 总行 */
    VALUE_0("0"),
    /** 分行 */
    VALUE_1("1"),
    /** 支行 */
    VALUE_2("2");

    private String value;

    private HierarchyCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static HierarchyCode byValue(String value) {
        for (HierarchyCode item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}