package org.yjh.policy.finder;

import org.yjh.policy.GradePolicy;
import org.yjh.policy.PassFailPolicy;
import org.yjh.university.Score;
import org.yjh.university.Subject;

public class PassFailPolicyProvider implements GradePolicyProvidable {

    @Override
    public boolean supports(Score score) {
        return score.isScoreAbout(Subject.DANCE);
    }

    @Override
    public GradePolicy provide(Score score) {
        return new PassFailPolicy();
    }
}
