package com.xiaom.bms.common;

public enum BATTERY_NAME {
    TERNARY_LITHIUM("三元电池"),
    IRON_PHOSPHATE("铁锂电池");

    private final String value;

    BATTERY_NAME(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}