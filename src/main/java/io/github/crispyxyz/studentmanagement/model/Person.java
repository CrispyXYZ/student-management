package io.github.crispyxyz.studentmanagement.model;

import io.github.crispyxyz.studentmanagement.util.ValidatingUtil;

public abstract class Person {
    private String id;
    private String name;
    private int age;

    public Person(String id, int age, String name) {
        setId(id);
        setAge(age);
        setName(name);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        ValidatingUtil.validateString(id, "ID");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        ValidatingUtil.validateString(name, "姓名");
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0) {
            throw new IllegalArgumentException("年龄不能为负数");
        }
        this.age = age;
    }
}
