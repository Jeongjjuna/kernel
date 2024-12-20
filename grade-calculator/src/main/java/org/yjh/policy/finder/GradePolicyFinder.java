package org.yjh.policy.finder;

import org.yjh.exception.GradePolicyNotFoundException;
import org.yjh.policy.GradePolicy;
import org.yjh.university.Score;

import java.util.List;

public class GradePolicyFinder {
    private static final List<GradePolicyProvidable> GRADE_POLICY_PROVIDERS = List.of(
            new RequiredSubjectProvider(),
            new GeneralSubjectProvider(),
            new PassFailPolicyProvider()
    );

    public GradePolicy findGradePolicyFrom(Score score) {
        return GRADE_POLICY_PROVIDERS.stream()
                .filter(provider -> provider.supports(score))
                .findFirst()
                .map(provider -> provider.provide(score))
                .orElseThrow(() -> new GradePolicyNotFoundException("학점 정책을 찾을 수 없습니다."));
    }
}
