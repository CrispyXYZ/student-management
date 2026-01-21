package io.github.crispyxyz.studentmanagement.model;

import io.github.crispyxyz.studentmanagement.util.ValidatingUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 学生实体类，继承自 Person
 * 包含专业信息和各科成绩
 */
public class Student extends Person implements Detail {
    /** 专业，不可为 null 或空 */
    private String major;

    /**
     * 成绩表
     * key: 课程名，不可为 null 或空
     * value: 成绩 (0.0 ~ 100.0)
     */
    private Map<String, Double> scores = new HashMap<>();

    /**
     * 学生构造函数
     * @param id 学生ID
     * @param age 年龄
     * @param name 姓名
     * @param major 专业
     * @throws IllegalArgumentException 若含有空白字符串参数
     * @throws NullPointerException 若含有 null 参数
     */
    public Student(String id, int age, String name, String major) {
        super(id, age, name);
        setMajor(major);
    }

    public String getMajor() {
        return major;
    }

    /**
     * 设置专业
     * @param major 专业
     * @throws IllegalArgumentException 若含有空白字符串参数
     * @throws NullPointerException 若含有 null 参数
     */
    public void setMajor(String major) {
        ValidatingUtil.validateString(major, "专业");
        this.major = major;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    /**
     * 设置成绩表
     * @param scores 成绩表
     * @throws NullPointerException 若含有 null 参数
     * @throws IllegalArgumentException 若含有空白字符串参数，或分数超出 0.0~100.0 范围
     */
    public void setScores(Map<String, Double> scores) {
        if(scores == null) throw new NullPointerException("分数不能为 null");
        scores.keySet().forEach(key -> ValidatingUtil.validateString(key, "科目"));
        scores.values().forEach(value -> ValidatingUtil.validateDouble(value,0.0,100.0, "分数"));
        this.scores = scores;
    }

    /**
     * 输出学生的详细信息
     */
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
