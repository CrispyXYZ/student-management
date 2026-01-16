package io.github.crispyxyz.studentmanagement.model;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person implements Detail {
    private String major;
    private Map<String, Double> scores = new HashMap<>();

    public Student(String id, int age, String name, String major) {
        super(id, age, name);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }

    @Override
    public void showDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId()).append("\n")
          .append("姓名: ").append(getName()).append("\n")
          .append("年龄：").append(getAge()).append("\n")
          .append("专业：").append(getMajor()).append("\n")
          .append("分数：");
        for(Map.Entry<String, Double> entry : getScores().entrySet()) {
            sb.append("\n- ").append(entry.getKey()).append(": ").append(entry.getValue());
        }
        System.out.println(sb);
    }
}
