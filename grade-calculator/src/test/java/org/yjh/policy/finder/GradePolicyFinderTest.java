package org.yjh.policy.finder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yjh.domain.Major;
import org.yjh.domain.Score;
import org.yjh.domain.Student;
import org.yjh.domain.Subject;
import org.yjh.policy.GeneralSubjectPolicy;
import org.yjh.policy.GradePolicy;
import org.yjh.policy.RequiredSubjectPolicy;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@DisplayName("GradePolicyFinder 단위테스트")
class GradePolicyFinderTest {

    private final GradePolicyFinder gradePolicyFinder = new GradePolicyFinder();

    @DisplayName("일반 과목 정책을 찾을 수 있다.")
    @Test
    void findGeneralSubjectPolicy() {
        // given
        Major major = new Major("국어국문학과", Subject.KOREAN);
        Student student = new Student(111111, "홍길동", major);
        Score score = new Score(10, student, Subject.MATH);

        // when
        GradePolicy result = gradePolicyFinder.findGradePolicyFrom(score);

        // then
        assertInstanceOf(GeneralSubjectPolicy.class, result);
    }

    @DisplayName("전공 과목 정책을 찾을 수 있다.")
    @Test
    void findRequiredSubjectPolicy() {
        // given
        Major major = new Major("국어국문학과", Subject.KOREAN);
        Student student = new Student(111111, "홍길동", major);
        Score score = new Score(10, student, Subject.KOREAN);

        // when
        GradePolicy result = gradePolicyFinder.findGradePolicyFrom(score);

        // then
        assertInstanceOf(RequiredSubjectPolicy.class, result);
    }
}