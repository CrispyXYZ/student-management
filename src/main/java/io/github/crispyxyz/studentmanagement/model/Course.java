package io.github.crispyxyz.studentmanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private int capacity;
    private int remain;
    private List<Student> students = new ArrayList<>();

    public Course(String id, String name, int capacity) {
        setId(id);
        setName(name);
        setCapacity(capacity);
        remain=capacity;
    }

    public synchronized boolean enroll(Student student) {
        if(remain<=0) return false;
        students.add(student);
        remain--;
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRemain() {
        return remain;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
