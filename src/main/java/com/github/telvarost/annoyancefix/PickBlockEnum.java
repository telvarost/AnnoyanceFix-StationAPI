package com.github.telvarost.annoyancefix;

public enum PickBlockEnum {
    NO_CHECK_META("Do Not Check Meta-Data"),
    CHECK_META("Check Meta-Data"),
    CHECK_MORE_EXACT("Check More Exact");

    final String stringValue;

    PickBlockEnum() {
        this.stringValue = "Do Not Check Meta-Data";
    }

    PickBlockEnum(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}