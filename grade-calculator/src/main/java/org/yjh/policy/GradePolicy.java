package org.yjh.policy;

import org.yjh.university.Score;

public interface GradePolicy {
    String getGrade(Score score);
}
