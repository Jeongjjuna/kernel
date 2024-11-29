package org.yjh.university;

import org.yjh.calculator.GradeCalculator;

import java.util.ArrayList;
import java.util.List;

public class University {
    private static final University INSTANCE = new University();

    private final GradeCalculator gradeCalculator = new GradeCalculator();
    private final List<Student> students = new ArrayList<>();

    private University() {
    }

    public static University getInstance() {
        return INSTANCE;
    }

    public void register(List<Student> students) {
        this.students.addAll(students);
    }

    public String getGradeReportAll(List<Score> scores) {
        StringBuilder report = new StringBuilder();

        for (Subject subject : Subject.values()) {
            report.append(getGradeReport(scores, subject));
        }
        return report.toString();
    }

    public String getGradeReport(List<Score> scores, Subject subject) {
        return gradeCalculator.getReportByCalculating(scores, subject);
    }
}
