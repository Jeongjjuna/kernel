package org.yjh.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.yjh.university.Major;
import org.yjh.university.Subject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Major 단위테스트")
class MajorTest {

    @DisplayName("필수수강 과목여부를 알 수 있다.")
    @CsvSource(value = {"KOREAN, true", "MATH, false"})
    @ParameterizedTest
    void isRequiredSubject(Subject subject, boolean expected) {
        // given
        Major major = new Major("국어국문학과", Subject.KOREAN);

        // when
        var result = major.isRequiredSubject(subject);

        // then
        assertEquals(expected, result);
    }
}