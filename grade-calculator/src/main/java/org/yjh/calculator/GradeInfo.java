package org.yjh.calculator;

import org.yjh.domain.Score;

public record GradeInfo(
        String studentName,
        int studentId,
        String requiredSubject,
        String grade
) {
    public static GradeInfo of(Score score, String grade) {
        return new GradeInfo(
                score.getStudent().getName(),
                score.getStudent().getId(),
                score.getStudent().getMajor().getRequiredSubject().getName(),
                grade
        );
    }
}
