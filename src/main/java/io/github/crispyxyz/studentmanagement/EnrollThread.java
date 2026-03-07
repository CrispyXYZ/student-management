package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.model.Course;
import io.github.crispyxyz.studentmanagement.model.Student;

import java.util.concurrent.CountDownLatch;

public class EnrollThread extends Thread {
    private final Student student;
    private final Course course;
    private final CountDownLatch startLatch;
    private final CountDownLatch endLatch;

    public EnrollThread(Student student, Course course, CountDownLatch startLatch, CountDownLatch endLatch) {
        this.student = student;
        this.course = course;
        this.startLatch = startLatch;
        this.endLatch = endLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
            boolean result = course.enroll(student);
            System.out.printf("%s 抢课%s%n", student.getName(), result? "成功" : "失败");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        endLatch.countDown();
    }
}
