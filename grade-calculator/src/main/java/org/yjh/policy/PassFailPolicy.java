package org.yjh.policy;

import org.yjh.domain.Score;

public class PassFailPolicy implements GradePolicy {

    @Override
    public String getGrade(Score score) {
        int value = score.getValue();

        if (value >= 70) {
            return value + ":P";
        }
        return value + ":F";
    }
}
