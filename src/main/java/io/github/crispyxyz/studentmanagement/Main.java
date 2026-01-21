package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.exception.DataNotFoundException;
import io.github.crispyxyz.studentmanagement.exception.InvalidCourseException;
import io.github.crispyxyz.studentmanagement.model.Course;
import io.github.crispyxyz.studentmanagement.model.Student;
import io.github.crispyxyz.studentmanagement.model.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();

        // 创建一个容量为 5 的课程
        Course javaCourse = new Course("CS001", "Java 从入门到入土", 5);

        System.out.println("=== 添加学生 ===");
        // 批量添加 10 名学生
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

        // 为每个学生创建一个选课线程，模拟并发抢课
        List<Thread> threads = new ArrayList<>();
        for(Student student : students) {
            threads.add(new EnrollThread(student, javaCourse));
        }

        System.out.println("=== 开始抢课 ===");
        for(Thread thread : threads) {
            thread.start();
        }

        // 等待 500ms，确保所有线程运行结束
        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {
            System.out.println("线程休眠被中断");
            System.exit(1);
        }

        // 输出抢课结果
        System.out.println("抢课成功列表：");
        for(Student student : javaCourse.getStudents()) {
            System.out.println("- " + student.getName());
        }
        System.out.printf("选课人数：%d/%d%n", javaCourse.getCapacity() - javaCourse.getRemain(), javaCourse.getCapacity());

        System.out.println("=== 添加老师 ===");
        Teacher teacher1 = new Teacher("2001", 55, "李田所");
        teacher1.getCourses().add("C++ 从入门到入土");
        GenericDataManager.add(teachers, teacher1);

        Teacher teacher2 = new Teacher("2002", 44, "张浩杨");
        teacher2.getCourses().add("C-- 从入门到精通");
        GenericDataManager.add(teachers, teacher2);

        // 模拟录入成绩
        System.out.println("=== 录入成绩 ===");
        for(Student student : students) {
            try {
                teacher1.recordScore("C++ 从入门到入土", student, Math.log10(Math.random())*50+100);
            } catch(InvalidCourseException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("=== 查询 ===");
        try {
            Teacher foundTeacher = GenericDataManager.findById(teachers, "2002");
            foundTeacher.showDetails();
        } catch(DataNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== 删除 ===");
        try {
            GenericDataManager.deleteById(teachers, "2002");
        } catch(DataNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== 删除后查询 ===");
        try {
            GenericDataManager.findById(teachers, "2002");
        } catch(DataNotFoundException e) {
            System.out.println(e.getMessage());
        }

        Teacher teacherNew = new Teacher("2001", 56, "李田所");

        System.out.println("=== 更新 ===");
        try {
            GenericDataManager.update(teachers, teacherNew);
        } catch(DataNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("=== 不及格统计 ===");
        Map<Student, Integer> failedStudents = ScoreManager.getFailedStudents(students);
        for(Map.Entry<Student, Integer> entry : failedStudents.entrySet()) {
            System.out.println("学生 " + entry.getKey().getName() + " 不及格科目数：" + entry.getValue());
            entry.getKey().showDetails();
        }

        System.out.println("=== 课程统计数据 ===");
        ScoreManager.CourseStat stat = ScoreManager.getCourseStat(students, "C++ 从入门到入土");
        System.out.println("平均分：" + stat.average);
        System.out.println("及格率：" + stat.passRate);

    }
}