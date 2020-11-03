package com.ie.stockapp.enums;

public enum ProcessType {
    FOLLOW("FOLLOW"),
    UNFOLLOW("UNFOLLOW");

    private final String status;

    ProcessType(final String status) {
        this.status = status;
    }

    public String getValue() {
        return status;
    }
}

