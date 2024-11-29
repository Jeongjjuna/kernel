package org.yjh.university;

public class Student {
    private final int id;
    private final String name;
    private final Major major;

    public Student(int id, String name, Major major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    public boolean isRequiredSubject(Subject subject) {
        return major.isRequiredSubject(subject);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Major getMajor() {
        return major;
    }

}
