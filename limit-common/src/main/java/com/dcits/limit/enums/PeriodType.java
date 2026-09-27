package com.dcits.limit.enums;

/** 期限类型 */
public enum PeriodType {
    /** 日 */
    D("D"),
    /** 半年 */
    H("H"),
    /** 月 */
    M("M"),
    /** 季 */
    Q("Q"),
    /** 周 */
    W("W"),
    /** 年 */
    Y("Y");

    private String value;

    private PeriodType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PeriodType byValue(String value) {
        for (PeriodType item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}