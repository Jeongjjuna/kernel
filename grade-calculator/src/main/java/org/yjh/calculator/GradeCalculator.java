package org.yjh.calculator;

import org.yjh.domain.Score;
import org.yjh.domain.Subject;

import java.util.List;

public class GradeCalculator {

    private final ReportGenerator reportGenerator = new ReportGenerator();

    public String getReportByCalculating(List<Score> scores, Subject subject) {
        List<Score> targetScores = findAllBySubject(scores, subject);
        return reportGenerator.generate(targetScores, subject);
    }

    private List<Score> findAllBySubject(List<Score> scores, Subject subject) {
        return scores.stream()
                .filter(scoreInfo -> scoreInfo.isSame(subject))
                .toList();
    }
}
