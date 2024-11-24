package org.yjh.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Student 단위테스트")
class StudentTest {

    @DisplayName("자신의 필수 수강 과목 여부를 알 수 있다.")
    @CsvSource(value = {"KOREAN, true", "MATH, false"})
    @ParameterizedTest
    void isSame(Subject subject, boolean expected) {
        // given
        Major major = new Major("국어국문학과", Subject.KOREAN);
        Student student = new Student(111111, "홍길동", major);

        // when
        var result = student.isRequiredSubject(subject);

        // then
        assertEquals(expected, result);
    }

}