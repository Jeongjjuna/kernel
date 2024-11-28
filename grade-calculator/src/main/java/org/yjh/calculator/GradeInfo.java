package org.yjh.calculator;

import org.yjh.domain.Score;
import org.yjh.domain.Student;

public record GradeInfo(
        String studentName,
        int studentId,
        String requiredSubject,
        String grade
) {
    public static GradeInfo of(Score score, String grade) {
        final Student student = score.getStudent();
        return new GradeInfo(
                student.getName(),
                student.getId(),
                student.getMajor().getRequiredSubject().getName(),
                grade
        );
    }
}
