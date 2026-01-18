package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.model.Course;
import io.github.crispyxyz.studentmanagement.model.Student;
import io.github.crispyxyz.studentmanagement.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();

        Course javaCourse = new Course("CS001", "Java 从入门到入土", 5);

        GenericDataManager.add(students, new Student("001", 24, "甲", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("002", 24, "乙", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("003", 24, "丙", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("004", 24, "丁", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("005", 24, "戊", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("006", 24, "己", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("007", 24, "庚", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("008", 24, "辛", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("009", 24, "壬", "计算机科学与技术"));
        GenericDataManager.add(students, new Student("010", 24, "癸", "计算机科学与技术"));

        List<Thread> threads = new ArrayList<>();
        for(Student student : students) {
            threads.add(new EnrollThread(student, javaCourse));
        }

        for(Thread thread : threads) {
            thread.start();
        }

        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {
            System.out.println("线程休眠被中断");
            System.exit(1);
        }

        System.out.println("抢课成功列表：");
        for(Student student : javaCourse.getStudents()) {
            System.out.println("- " + student.getName());
        }
        System.out.printf("选课人数：%d/%d%n", javaCourse.getCapacity() - javaCourse.getRemain(), javaCourse.getCapacity());
    }
}