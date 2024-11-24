package org.yjh.policy.finder;

import org.yjh.domain.Score;
import org.yjh.policy.GradePolicy;

import java.util.List;

public class GradePolicyFinder {
    private static final List<GradePolicyProvidable> GRADE_POLICY_PROVIDERS = List.of(
            new RequiredSubjectProvider(),
            new GeneralSubjectProvider(),
            new PassFailPolicyProvider()
    );

    /**
     * 해당 Score의 과목이, 전공 과목일 경우 -> 전공 과목 정책
     * 해당 Score의 과목이, 전공 과목이 아닐 경우 -> 일반 과목 정책
     */
    public GradePolicy findGradePolicyFrom(Score score) {
        return GRADE_POLICY_PROVIDERS.stream()
                .filter(provider -> provider.supports(score))
                .findFirst()
                .map(provider -> provider.provide(score))
                .orElseThrow(() -> new IllegalArgumentException("학점 정책을 찾을 수 없습니다."));
    }
}
