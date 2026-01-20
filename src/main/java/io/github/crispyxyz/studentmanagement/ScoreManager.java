package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreManager {
    public static Map<Student, Integer> getFailedStudents(List<Student> students) {
        Map<Student, Integer> failedStudents = new HashMap<>();
        for(Student student : students) {
            int count = 0;
            for(Map.Entry<String, Double> entry : student.getScores().entrySet()) {
                if(entry.getValue() < 60) {
                    count++;
                }
            }
            if(count > 0) {
                failedStudents.put(student, count);
            }
        }
        return failedStudents;
    }

    public static CourseStat getCourseStat(List<Student> students, String courseName) {
        int studentNum = students.size();
        double scoreSum = 0;
        int passNum = 0;
        for(Student student : students) {
            for(Map.Entry<String, Double> entry : student.getScores().entrySet()) {
                if(entry.getKey().equals(courseName)) {
                    scoreSum += entry.getValue();
                    if(entry.getValue() >= 60.0) {
                        passNum++;
                    }
                }
            }
        }
        return new CourseStat(scoreSum/studentNum, (double)passNum/studentNum);
    }

    public static class CourseStat {
        public final double average;
        public final double passRate;
        public CourseStat(double average, double passRate) {
            this.average = average;
            this.passRate = passRate;
        }
    }
}
