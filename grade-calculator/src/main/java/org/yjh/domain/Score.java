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
}
