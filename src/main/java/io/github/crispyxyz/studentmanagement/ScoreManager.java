package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.model.Student;
import io.github.crispyxyz.studentmanagement.util.ValidatingUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreManager {
    /**
     * 获取不及格学生及其不及格科目数
     * @param students 学生列表
     * @return 映射表，key: 学生对象，value: 不及格科目数
     * @throws NullPointerException 若学生列表为 null
     */
    public static Map<Student, Integer> getFailedStudents(List<Student> students) {
        if(students == null) {
            throw new NullPointerException("学生列表不可为 null");
        }

        Map<Student, Integer> failedStudents = new HashMap<>();

        // 遍历所有学生的所有课程成绩
        for(Student student : students) {
            int count = 0;
            for(Map.Entry<String, Double> entry : student.getScores().entrySet()) {
                if(entry.getValue() < 60) {
                    count++;
                }
            }

            // 只记录至少一门课程不及格的学生
            if(count > 0) {
                failedStudents.put(student, count);
            }
        }
        return failedStudents;
    }

    /**
     * 获取指定课程的统计信息
     * @param students 学生列表
     * @param courseName 课程名称
     * @return CourseStat 对象,包含平均分和及格率
     * @throws NullPointerException 若有参数为null
     * @throws IllegalArgumentException 若有参数为空白字符串
     */
    public static CourseStat getCourseStat(List<Student> students, String courseName) {
        ValidatingUtil.validateString(courseName, "课程名称");
        if(students == null) {
            throw new NullPointerException("学生列表不可为 null");
        }

        int studentNum = students.size();
        double scoreSum = 0;
        int passNum = 0;

        // 遍历所有学生的所有成绩
        for(Student student : students) {
            for(Map.Entry<String, Double> entry : student.getScores().entrySet()) {
                // 判断该项是否是给定课程
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

    /**
     * 课程统计信息包装类
     */
    public static class CourseStat {
        /** 平均分 */
        public final double average;
        /** 及格率 */
        public final double passRate;
        public CourseStat(double average, double passRate) {
            this.average = average;
            this.passRate = passRate;
        }
    }
}
