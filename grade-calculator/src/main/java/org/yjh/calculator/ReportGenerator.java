package org.yjh.calculator;

import org.yjh.domain.Major;
import org.yjh.domain.Score;
import org.yjh.domain.Student;
import org.yjh.domain.Subject;
import org.yjh.policy.GradePolicy;
import org.yjh.policy.finder.GradePolicyFinder;

import java.util.List;

public class ReportGenerator {

    private final GradePolicyFinder gradePolicyFinder = new GradePolicyFinder();

    public String generate(List<Score> scores, Subject subject) {
        StringBuilder report = new StringBuilder();

        report.append("-".repeat(30)).append("\n");
        report.append("   ").append(subject.getName()).append(" 수강생 학점\n");
        report.append("이름 | 학번 | 중점과목 | 점수\n");
        report.append("-".repeat(30)).append("\n");

        for (Score score : scores) {
            Student student = score.getStudent();
            Major major = student.getMajor();

            GradePolicy gradePolicy = gradePolicyFinder.findGradePolicyFrom(score);
            String grade = gradePolicy.getGrade(score);

            report.append(String.format("%s | %d | %s | %s |\n", student.getName(), student.getId(), major.getRequiredSubject().getName(), grade));
            report.append("-".repeat(30)).append("\n");
        }
        report.append("\n");
        return report.toString();
    }
}
