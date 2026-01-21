package io.github.crispyxyz.studentmanagement.model;

import io.github.crispyxyz.studentmanagement.util.ValidatingUtil;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person implements Detail {
    private String major;
    private Map<String, Double> scores = new HashMap<>();

    public Student(String id, int age, String name, String major) {
        super(id, age, name);
        setMajor(major);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        ValidatingUtil.validateString(major, "专业");
        this.major = major;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        if(scores == null) throw new NullPointerException("分数不能为 null");
        scores.keySet().forEach(key -> ValidatingUtil.validateString(key, "科目"));
        scores.values().forEach(value -> ValidatingUtil.validateDouble(value,0.0,100.0, "分数"));
        this.scores = scores;
    }

    @Override
    public void showDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId()).append("\n")
          .append("姓名: ").append(getName()).append("\n")
          .append("年龄: ").append(getAge()).append("\n")
          .append("专业: ").append(getMajor()).append("\n")
          .append("分数: ");
        for(Map.Entry<String, Double> entry : getScores().entrySet()) {
            sb.append("\n- ").append(entry.getKey()).append(": ").append(entry.getValue());
        }
        System.out.println(sb);
    }
}
