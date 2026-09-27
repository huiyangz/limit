package com.dcits.limit.enums;

/** 源模块 */
public enum SourceModule {
    /** 所有 */
    ALL("ALL"),
    /** 贷款 */
    CL("CL"),
    /** 公共业务 */
    CM("CM"),
    /** 总账 */
    GL("GL"),
    /** 内部户 */
    IA("IA"),
    /** 存款 */
    RB("RB");

    private String value;

    private SourceModule(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SourceModule byValue(String value) {
        for (SourceModule item : values()) {
            if (item.getValue().equals(value)) {
                return item;
            }
        }
        return null;
    }
}