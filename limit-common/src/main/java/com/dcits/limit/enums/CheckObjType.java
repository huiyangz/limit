package com.dcits.limit.enums;

/** 检查对象类型代码 */
public enum CheckObjType {
    /** 账户级别 */
    ACCT("ACCT"),
    /** 卡片级别 */
    CARD("CARD"),
    /** 卡组 */
    CDGROUP("CDGROUP"),
    /** 客户级别 */
    CUST("CUST");

    private String value;

    private CheckObjType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static CheckObjType byValue(String value) {
        for (CheckObjType item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}