package org.yjh.policy.finder;

import org.yjh.domain.Score;
import org.yjh.domain.Subject;
import org.yjh.policy.GeneralSubjectPolicy;
import org.yjh.policy.GradePolicy;

public class GeneralSubjectProvider implements GradePolicyProvidable {

    @Override
    public boolean supports(Score score) {
        return !score.isScoreRequiredSubject() && !score.isScoreAbout(Subject.DANCE);
    }

    @Override
    public GradePolicy provide(Score score) {
        return new GeneralSubjectPolicy();
    }
}
