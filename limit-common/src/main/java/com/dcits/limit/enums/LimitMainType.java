package com.dcits.limit.enums;

/** 限额大类 */
public enum LimitMainType {
    /** 银行卡交易取现限额 */
    VALUE_1("1"),
    /** pos消费 */
    VALUE_10("10"),
    /** amt限额 */
    VALUE_11("11"),
    /** 快捷支付限额 */
    VALUE_12("12"),
    /** 汇总非柜面渠道限额 */
    VALUE_13("13"),
    /** 银行卡交易刷卡消费额 */
    VALUE_2("2"),
    /** 银行卡交易无卡消费限额 */
    VALUE_3("3"),
    /** II类户支付限额（消费、缴费、向非绑定账户） */
    VALUE_4("4"),
    /** 非柜面非同名转账限额 */
    VALUE_5("5"),
    /** 银行卡转账限额 */
    VALUE_6("6"),
    /** 手机银行限额 */
    VALUE_7("7"),
    /** 网上银行限额 */
    VALUE_8("8"),
    /** 网关限额 */
    VALUE_9("9"),
    /** 所有限额大类 */
    ALL("ALL");

    private String value;

    private LimitMainType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LimitMainType byValue(String value) {
        for (LimitMainType item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}