package org.yjh.domain;

public enum Subject {
    KOREAN("국어"),
    MATH("수학"),
    DANCE("댄스");

    private final String name;

    Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
