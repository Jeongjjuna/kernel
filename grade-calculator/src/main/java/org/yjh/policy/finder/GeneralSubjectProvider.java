package org.yjh.policy.finder;

import org.yjh.policy.GeneralSubjectPolicy;
import org.yjh.policy.GradePolicy;
import org.yjh.university.Score;
import org.yjh.university.Subject;

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
