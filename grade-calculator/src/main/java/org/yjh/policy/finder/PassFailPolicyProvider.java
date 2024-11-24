package org.yjh.policy.finder;

import org.yjh.domain.Score;
import org.yjh.domain.Subject;
import org.yjh.policy.GradePolicy;
import org.yjh.policy.PassFailPolicy;

public class PassFailPolicyProvider implements GradePolicyProvidable {

    @Override
    public boolean supports(Score score) {
        return score.isSame(Subject.DANCE);
    }

    @Override
    public GradePolicy provide(Score score) {
        return new PassFailPolicy();
    }
}
