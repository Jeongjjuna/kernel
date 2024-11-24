package org.yjh.calculator;

import org.yjh.domain.Score;
import org.yjh.domain.Subject;
import org.yjh.policy.GradePolicy;
import org.yjh.policy.finder.GradePolicyFinder;

import java.util.ArrayList;
import java.util.List;

public class GradeCalculator {

    private final GradePolicyFinder gradePolicyFinder = new GradePolicyFinder();
    private final ReportGenerator reportGenerator = new ReportGenerator();

    public String getReportByCalculating(List<Score> scores, Subject subject) {
        List<Score> targetScores = findAllBySubject(scores, subject);

        List<GradeInfo> gradeInfos = new ArrayList<>();
        for (Score score : targetScores) {
            GradePolicy gradePolicy = gradePolicyFinder.findGradePolicyFrom(score);
            String grade = gradePolicy.getGrade(score);

            gradeInfos.add(GradeInfo.of(score, grade));
        }

        return reportGenerator.generate(gradeInfos, subject);
    }

    private List<Score> findAllBySubject(List<Score> scores, Subject subject) {
        return scores.stream()
                .filter(scoreInfo -> scoreInfo.isSame(subject))
                .toList();
    }
}
