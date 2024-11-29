package org.yjh.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.yjh.university.Major;
import org.yjh.university.Score;
import org.yjh.university.Student;
import org.yjh.university.Subject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("RequiredSubjectPolicy 단위테스트")
class RequiredSubjectPolicyTest {

    private final RequiredSubjectPolicy policy = new RequiredSubjectPolicy();

    @DisplayName("전공 과목 기준 학점을 계산할 수 있다.")
    @CsvSource(value = {
            "100, 100:S",
            "95, 95:S",
            "94, 94:A",
            "90, 90:A",
            "89, 89:B",
            "80, 80:B",
            "79, 79:C",
            "70, 70:C",
            "69, 69:D",
            "60, 60:D",
            "59, 59:F",
    })
    @ParameterizedTest
    void getGrade(int value, String expected) {
        // given
        Major major = new Major("국어국문학과", Subject.KOREAN);
        Student student = new Student(111111, "홍길동", major);
        Score score = new Score(value, student, Subject.KOREAN);

        // when
        var result = policy.getGrade(score);

        // then
        assertEquals(expected, result);
    }
}