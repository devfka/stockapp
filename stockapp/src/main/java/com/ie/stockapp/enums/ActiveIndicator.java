package com.ie.stockapp.enums;

public enum ActiveIndicator {
    ACTIVE("Y"),
    INACTIVE("N");

    private final String status;

    ActiveIndicator(final String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}

