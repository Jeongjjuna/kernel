package org.yjh.policy;

import org.yjh.university.Score;

public class RequiredSubjectPolicy implements GradePolicy {

    @Override
    public String getGrade(Score scoreInfo) {
        int score = scoreInfo.getValue();
        if (95 <= score) {
            return score + ":S";
        } else if (90 <= score) {
            return score + ":A";
        } else if (80 <= score) {
            return score + ":B";
        } else if (70 <= score) {
            return score + ":C";
        } else if (60 <= score) {
            return score + ":D";
        } else {
            return score + ":F";
        }
    }
}