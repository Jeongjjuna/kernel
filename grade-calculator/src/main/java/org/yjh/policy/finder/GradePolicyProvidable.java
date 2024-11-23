package org.yjh.policy.finder;

import org.yjh.domain.Score;
import org.yjh.policy.GradePolicy;

public interface GradePolicyProvidable {

    boolean supports(Score score);

    GradePolicy provide(Score score);
}
