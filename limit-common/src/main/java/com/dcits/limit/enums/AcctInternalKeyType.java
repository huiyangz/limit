package com.dcits.limit.enums;

/** 账户内部键类型 */
public enum AcctInternalKeyType {
    /** 对客账户内部键值 */
    CK("CK"),
    /** 账户内部键值 */
    IK("IK");

    private String value;

    private AcctInternalKeyType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static AcctInternalKeyType byValue(String value) {
        for (AcctInternalKeyType item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}