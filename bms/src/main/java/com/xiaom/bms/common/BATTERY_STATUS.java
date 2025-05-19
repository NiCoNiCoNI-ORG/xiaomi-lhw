package com.xiaom.bms.common;

public enum BATTERY_STATUS {
    STATUS_1((byte) 1),
    STATUS_2((byte) 2),
    STATUS_3((byte) 3);

    private final byte value;

    BATTERY_STATUS(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}