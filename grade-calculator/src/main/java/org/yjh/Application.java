package org.yjh;

import org.yjh.logger.BaseLogger;
import org.yjh.university.Major;
import org.yjh.university.Score;
import org.yjh.university.Student;
import org.yjh.university.Subject;
import org.yjh.university.University;

import java.util.List;

public class Application {

    private static final BaseLogger log = BaseLogger.getLogger();

    public static void main(String[] args) {

        Major koreanMajor = new Major("국어국문학과", Subject.KOREAN);
        Major computerMajor = new Major("컴퓨터공학과", Subject.MATH);

        Student student1 = new Student(211213, "강감찬", koreanMajor);
        Student student2 = new Student(212330, "김유신", computerMajor);
        Student student3 = new Student(201518, "신사임당", koreanMajor);
        Student student4 = new Student(202360, "이순신", koreanMajor);
        Student student5 = new Student(201290, "홍길동", computerMajor);

        List<Score> scores = List.of(
                new Score(95, student1, Subject.KOREAN),
                new Score(95, student2, Subject.KOREAN),
                new Score(100, student3, Subject.KOREAN),
                new Score(89, student4, Subject.KOREAN),
                new Score(83, student5, Subject.KOREAN),
                new Score(56, student1, Subject.MATH),
                new Score(98, student2, Subject.MATH),
                new Score(88, student3, Subject.MATH),
                new Score(95, student4, Subject.MATH),
                new Score(56, student5, Subject.MATH),
                new Score(95, student1, Subject.DANCE),
                new Score(85, student2, Subject.DANCE),
                new Score(55, student3, Subject.DANCE)
        );


        try {
            University university = University.getInstance();

            university.register(List.of(student1, student2, student3, student4, student5));

            String report = university.getGradeReportAll(scores);
            showReports(report);

        } catch (Exception e) {
            log.info(e.getMessage());
        }

    }

    private static void showReports(String report) {
        System.out.println(report);
    }

}
