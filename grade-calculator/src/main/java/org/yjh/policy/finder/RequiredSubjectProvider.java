package org.yjh.policy.finder;

import org.yjh.policy.GradePolicy;
import org.yjh.policy.RequiredSubjectPolicy;
import org.yjh.university.Score;

public class RequiredSubjectProvider implements GradePolicyProvidable {

    @Override
    public boolean supports(Score score) {
        return score.isScoreRequiredSubject();
    }

    @Override
    public GradePolicy provide(Score score) {
        return new RequiredSubjectPolicy();
    }
}
