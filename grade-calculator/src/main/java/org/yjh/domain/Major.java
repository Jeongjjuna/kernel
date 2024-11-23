package org.yjh.domain;

public class Major {
    private final String name;
    private final Subject requiredSubject;

    public Major(String name, Subject requiredSubject) {
        this.name = name;
        this.requiredSubject = requiredSubject;
    }
}
