package org.yjh;

import org.yjh.calculator.GradeCalculator;
import org.yjh.domain.Score;
import org.yjh.domain.Student;
import org.yjh.domain.Subject;

import java.util.ArrayList;
import java.util.List;

public class University {
    private final GradeCalculator gradeCalculator;
    private final List<Student> students = new ArrayList<>();

    public University(GradeCalculator gradeCalculator) {
        this.gradeCalculator = gradeCalculator;
    }

    public void register(List<Student> students) {
        this.students.addAll(students);
    }

    /**
     * 임의로 국어 -> 수학순으로 계산
     */
    public String calculateAbout(List<Score> scores) {
        String koreanGradeReport = gradeCalculator.calculateGradeFor(scores, Subject.KOREAN);
        String mathGradeReport = gradeCalculator.calculateGradeFor(scores, Subject.MATH);
        return koreanGradeReport + mathGradeReport;
    }
}
