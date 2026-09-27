package com.dcits.limit.enums;

/** 限额控制项类型 */
public enum CtrlItemType {
    /** 累计金额 */
    A("A"),
    /** 累计金额/笔数 */
    B("B"),
    /** 累计笔数 */
    N("N"),
    /** 单笔金额 */
    O("O");

    private String value;

    private CtrlItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CtrlItemType byValue(String value) {
        for (CtrlItemType item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}