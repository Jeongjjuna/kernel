package org.yjh.domain;

public class Score {
    private final int value;
    private final Student student;
    private final Subject subject;

    public Score(int value, Student student, Subject subject) {
        this.value = value;
        this.student = student;
        this.subject = subject;
    }

    public boolean isScoreRequiredSubject() {
        return student.isRequiredSubject(subject);
    }

    public boolean isScoreAbout(Subject subject) {
        return this.subject.equals(subject);
    }

    public int getValue() {
        return value;
    }

    public Student getStudent() {
        return student;
    }
}
