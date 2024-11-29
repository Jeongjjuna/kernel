package org.yjh.policy.finder;

import org.yjh.policy.GradePolicy;
import org.yjh.university.Score;

public interface GradePolicyProvidable {

    boolean supports(Score score);

    GradePolicy provide(Score score);
}
