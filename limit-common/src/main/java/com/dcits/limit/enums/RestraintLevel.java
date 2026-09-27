package com.dcits.limit.enums;

/** 限制级别 */
public enum RestraintLevel {
    /** 账户级别 */
    ACCT("ACCT"),
    /** 卡片级别 */
    CARD("CARD"),
    /** 客户级别 */
    CLIENT("CLIENT"),
    /** 账户属性限制 */
    NATURE("NATURE");

    private String value;

    private RestraintLevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RestraintLevel byValue(String value) {
        for (RestraintLevel item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}