package org.yjh.policy;

import org.yjh.domain.Score;

public interface GradePolicy {
    String getGrade(Score score);
}
