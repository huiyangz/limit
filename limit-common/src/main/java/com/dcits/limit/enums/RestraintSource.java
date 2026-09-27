package com.dcits.limit.enums;

/** 限制来源 */
public enum RestraintSource {
    /** 账户限制 */
    VALUE_0("0"),
    /** 凭证挂失 */
    VALUE_1("1"),
    /** 司法冻结 */
    VALUE_2("2"),
    /** 周期性扣划 */
    VALUE_3("3"),
    /** 黑名单 */
    VALUE_4("4");

    private String value;

    private RestraintSource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RestraintSource byValue(String value) {
        for (RestraintSource item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}