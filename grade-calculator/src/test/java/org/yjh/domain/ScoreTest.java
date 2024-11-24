package org.yjh.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Score 단위테스트")
class ScoreTest {

    @DisplayName("필수 과목에 대한 점수인지 알 수 있다.")
    @CsvSource(value = {"KOREAN, true", "MATH, false"})
    @ParameterizedTest
    void isScoreRequiredSubject(Subject subject, boolean expected) {
        // given
        Major major = new Major("국어국문학과", Subject.KOREAN);
        Student student = new Student(111111, "홍길동", major);
        Score score = new Score(100, student, subject);

        // when
        var result = score.isScoreRequiredSubject();

        // then
        assertEquals(expected, result);
    }

    @DisplayName("어떤 과목에 대한 점수인지 알 수 있다.")
    @CsvSource(value = {"KOREAN, true", "MATH, false"})
    @ParameterizedTest
    void isSame(Subject subject, boolean expected) {
        // given
        Major major = new Major("국어국문학과", Subject.KOREAN);
        Student student = new Student(111111, "홍길동", major);
        Score score = new Score(100, student, subject);

        // when
        var result = score.isSame(Subject.KOREAN);
        // then
        assertEquals(expected, result);
    }

}