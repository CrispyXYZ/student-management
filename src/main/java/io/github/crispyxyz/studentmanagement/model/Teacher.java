package io.github.crispyxyz.studentmanagement.model;

import io.github.crispyxyz.studentmanagement.exception.InvalidCourseException;
import io.github.crispyxyz.studentmanagement.util.ValidatingUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 教师实体类，继承自 Person
 * 管理教师的基本信息及其授课课程，提供成绩录入功能
 */
public class Teacher extends Person implements Detail {
    /**
     * 授课列表
     * 元素：课程名称字符串
     */
    private List<String> courses = new ArrayList<>();

    /**
     * 教师构造函数
     * @param id 教师ID
     * @param age 年龄
     * @param name 姓名
     */
    public Teacher(String id, int age, String name) {
        super(id, age, name);
    }

    public List<String> getCourses() {
        return courses;
    }

    /**
     * 设置授课列表
     * @param courses 授课列表
     * @throws NullPointerException 若含有 null 元素或 null 参数
     * @throws IllegalArgumentException 若含有空白字符串元素
     */
    public void setCourses(List<String> courses) {
        if(courses == null) {
            throw new NullPointerException("课程不能为 null");
        }
        courses.forEach(str -> ValidatingUtil.validateString(str, "课程"));
        this.courses = courses;
    }

    /**
     * 为指定学生录入指定课程的成绩
     * @param course 课程名称
     * @param student 学生
     * @param score 成绩
     * @throws InvalidCourseException 若教师不教授该课程
     * @throws NullPointerException 若含有 null 参数
     * @throws IllegalArgumentException 若含有空白字符串参数或成绩超出 0.0~100.0 范围
     */
    public void recordScore(String course, Student student, double score) throws InvalidCourseException {
        if(!courses.contains(course)) {
            throw new InvalidCourseException("教师" + getName() + "不教授课程" + course + "，无法录入成绩");
        }
        if(student == null) {
            throw new NullPointerException("学生不能为 null");
        }
        ValidatingUtil.validateString(course, "课程");
        ValidatingUtil.validateDouble(score, 0.0, 100.0, "分数");
        student.getScores().put(course, score);
    }

    /**
     * 输出教师的详细信息
     */
    @Override
    public void showDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId()).append("\n")
          .append("姓名: ").append(getName()).append("\n")
          .append("年龄: ").append(getAge()).append("\n")
          .append("授课课程: ");
        for(String courseName : getCourses()) {
            sb.append("\n- ").append(courseName);
        }
        System.out.println(sb);
    }
}
