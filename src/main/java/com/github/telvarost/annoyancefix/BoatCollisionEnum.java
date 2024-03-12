package com.github.telvarost.annoyancefix;

public enum BoatCollisionEnum {
    VANILLA("Vanilla"),
    DROP_BOAT("Drop Boat"),
    INVINCIBLE("Invincible");

    final String stringValue;

    BoatCollisionEnum() {
        this.stringValue = "Vanilla";
    }

    BoatCollisionEnum(String stringValue) {
        this.stringValue = stringValue;
    }
}