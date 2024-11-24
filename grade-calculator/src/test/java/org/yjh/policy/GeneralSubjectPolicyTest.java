package org.yjh.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.yjh.domain.Major;
import org.yjh.domain.Score;
import org.yjh.domain.Student;
import org.yjh.domain.Subject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GeneralSubjectPolicy 단위테스트")
class GeneralSubjectPolicyTest {

    private final GeneralSubjectPolicy policy = new GeneralSubjectPolicy();

    @DisplayName("일반 과목 기준 학점을 계산할 수 있다.")
    @CsvSource(value = {
            "100, 100:A",
            "90, 90:A",
            "89, 89:B",
            "80, 80:B",
            "79, 79:C",
            "70, 70:C",
            "69, 69:D",
            "55, 55:D",
            "54, 54:F",
    })
    @ParameterizedTest
    void getGrade(int value, String expected) {
        // given
        Major major = new Major("국어국문학과", Subject.KOREAN);
        Student student = new Student(111111, "홍길동", major);
        Score score = new Score(value, student, Subject.MATH);

        // when
        var result = policy.getGrade(score);

        // then
        assertEquals(expected, result);
    }
}