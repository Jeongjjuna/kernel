package org.yjh.policy.finder;

import org.yjh.domain.Score;
import org.yjh.policy.GradePolicy;
import org.yjh.policy.RequiredSubjectPolicy;

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
