package io.github.crispyxyz.studentmanagement.model;

import io.github.crispyxyz.studentmanagement.exception.InvalidCourseException;
import io.github.crispyxyz.studentmanagement.util.ValidatingUtil;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person implements Detail {
    private List<String> courses = new ArrayList<>();

    public Teacher(String id, int age, String name) {
        super(id, age, name);
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        if(courses == null) {
            throw new NullPointerException("课程不能为 null");
        }
        courses.forEach(str -> ValidatingUtil.validateString(str, "课程"));
        this.courses = courses;
    }

    public void recordScore(String course, Student student, double score) throws InvalidCourseException {
        if(!courses.contains(course)) {
            throw new InvalidCourseException("教师" + getName() + "不教授课程" + course + "，无法录入成绩");
        }
        ValidatingUtil.validateString(course, "课程");
        ValidatingUtil.validateDouble(score, 0.0, 100.0, "分数");
        student.getScores().put(course, score);
    }

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
