package com.project1.ticketing.domain.concert.models;

public enum SeatStatus {
    AVAILABLE, RESERVED;

    public boolean toBoolean() {
        switch (this) {
            case AVAILABLE:
                return true;
            case RESERVED:
                return false;
            default:
                throw new IllegalArgumentException("Unsupported Enum value");
        }
    }
}
