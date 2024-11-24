package org.yjh.calculator;

import org.yjh.domain.Subject;

import java.util.List;

public class ReportGenerator {
    private static final String DASH_LINE = "----------------------------------------";
    private static final String NEW_LINE = "\n";

    public String generate(List<GradeInfo> infos, Subject subject) {
        StringBuilder report = new StringBuilder();

        report.append(DASH_LINE).append(NEW_LINE);
        report.append(String.format("   %s 수강생 학점", subject.getName())).append(NEW_LINE);
        report.append("이름 | 학번 | 중점과목 | 점수").append(NEW_LINE);
        report.append(DASH_LINE).append(NEW_LINE);

        for (GradeInfo info : infos) {
            report.append(String.format("%s | %d | %s | %s |", info.studentName(), info.studentId(), info.requiredSubject(), info.grade())).append(NEW_LINE);
            report.append(DASH_LINE).append(NEW_LINE);
        }
        report.append(NEW_LINE);

        return report.toString();
    }
}
