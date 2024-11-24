package org.yjh.university;

import org.yjh.calculator.GradeCalculator;
import org.yjh.domain.Score;
import org.yjh.domain.Student;
import org.yjh.domain.Subject;

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

    /**
     * 임의로 국어 -> 수학순으로 계산
     */
    public String getGradeReport(List<Score> scores) {

        String koreanGradeReport = gradeCalculator.getReportByCalculating(scores, Subject.KOREAN);
        String mathGradeReport = gradeCalculator.getReportByCalculating(scores, Subject.MATH);
        String danceGradeReport = gradeCalculator.getReportByCalculating(scores, Subject.DANCE);

        return koreanGradeReport + mathGradeReport + danceGradeReport;
    }
}
