package org.yjh.calculator;

import org.yjh.domain.Score;
import org.yjh.domain.Subject;
import org.yjh.policy.GradePolicy;
import org.yjh.policy.finder.GradePolicyFinder;

import java.util.List;

public class GradeCalculator {
    private final GradePolicyFinder gradePolicyFinder = new GradePolicyFinder();
    private final ReportGenerator reportGenerator = new ReportGenerator();

    public String getReportByCalculating(List<Score> scores, Subject subject) {
        List<Score> targetScores = findAllBySubject(scores, subject);

        List<GradeInfo> gradeInfos = createGradeInfo(targetScores);

        return reportGenerator.generate(gradeInfos, subject);
    }

    private List<Score> findAllBySubject(List<Score> scores, Subject subject) {
        return scores.stream()
                .filter(score -> score.isScoreAbout(subject))
                .toList();
    }

    private List<GradeInfo> createGradeInfo(List<Score> targetScores) {
        return targetScores.stream()
                .map(this::createFrom)
                .toList();
    }

    private GradeInfo createFrom(Score score) {
        GradePolicy gradePolicy = gradePolicyFinder.findGradePolicyFrom(score);
        String grade = gradePolicy.getGrade(score);
        return GradeInfo.of(score, grade);
    }
}
