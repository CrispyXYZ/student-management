package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.model.Course;
import io.github.crispyxyz.studentmanagement.model.Student;

public class EnrollThread extends Thread {
    private final Student student;
    private final Course course;

    public EnrollThread(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch(InterruptedException e) {
            System.out.println("线程休眠被中断");
        }
        boolean result = course.enroll(student);
        System.out.printf("%s 抢课%s%n", student.getName(), result? "成功" : "失败");
    }
}
