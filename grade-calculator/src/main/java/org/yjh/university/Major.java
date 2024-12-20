package org.yjh.university;

public class Major {
    private final String name;
    private final Subject requiredSubject;

    public Major(String name, Subject requiredSubject) {
        this.name = name;
        this.requiredSubject = requiredSubject;
    }

    public boolean isRequiredSubject(Subject subject) {
        return requiredSubject.equals(subject);
    }

    public Subject getRequiredSubject() {
        return requiredSubject;
    }
}
