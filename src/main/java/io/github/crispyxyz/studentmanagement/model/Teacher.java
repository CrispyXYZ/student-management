package io.github.crispyxyz.studentmanagement.model;

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
        this.courses = courses;
    }

    @Override
    public void showDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId()).append("\n")
          .append("姓名： ").append(getName()).append("\n")
          .append("年龄： ").append(getAge()).append("\n")
          .append("授课课程： ");
        for(String courseName : getCourses()) {
            sb.append("\n- ").append(courseName);
        }
        System.out.println(sb);
    }
}
