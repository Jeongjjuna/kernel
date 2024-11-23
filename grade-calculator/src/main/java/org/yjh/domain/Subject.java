package org.yjh.domain;

public enum Subject {
    KOREAN("국어"),
    MATH("수학");

    private final String name;

    Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
