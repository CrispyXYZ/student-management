package io.github.crispyxyz.studentmanagement;

import io.github.crispyxyz.studentmanagement.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreManagerTest {

    List<Student> studentList;
    Student student1;
    Student student2;
    Student student3;

    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();

        Map<String, Double> scores1 = new HashMap<>();
        scores1.put("军事理论", 97.0);
        scores1.put("微积分(2)", 59.9);

        student1 = new Student("1001", 19, "张三", "生物工程");
        student1.setScores(scores1);
        studentList.add(student1);

        Map<String, Double> scores2 = new HashMap<>();
        scores2.put("军事理论", 59.7);
        scores2.put("微积分(2)", 47.9);

        student2 = new Student("1002", 20, "李四", "应用化学");
        student2.setScores(scores2);
        studentList.add(student2);

        Map<String, Double> scores3 = new HashMap<>();
        scores3.put("军事理论", 100.0);
        scores3.put("微积分(2)", 100.0);

        student3 = new Student("1003", 24, "王五", "材料物理");
        student3.setScores(scores3);
        studentList.add(student3);
    }

    @AfterEach
    public void tearDown() {
        studentList = null;
        student1 = null;
        student2 = null;
        student3 = null;
    }

    @Test
    public void testGetCourseStat() {
        ScoreManager.CourseStat stat = ScoreManager.getCourseStat(studentList, "军事理论");
        assertEquals(2567.0/30.0, stat.average, 0.001);
        assertEquals(2.0/3.0, stat.passRate, 0.001);
    }

    @Test
    public void testGetCourseStatEmpty() {
        ScoreManager.CourseStat stat = ScoreManager.getCourseStat(studentList, "军事技能");
        assertEquals(0, stat.average, 0.001);
        assertEquals(0, stat.passRate, 0.001);
    }

    @Test
    public void testGetCourseStatFailure() {
        assertThrows(NullPointerException.class, () -> ScoreManager.getCourseStat(studentList, null));
        assertThrows(NullPointerException.class, () -> ScoreManager.getCourseStat(null, "军事理论"));
    }

    @Test
    public void testGetFailedStudents() {
        Map<Student, Integer> failedStudents = ScoreManager.getFailedStudents(studentList);

        assertTrue(failedStudents.containsKey(student1));
        assertTrue(failedStudents.containsKey(student2));
        assertFalse(failedStudents.containsKey(student3));

        assertEquals(1, failedStudents.get(student1));
        assertEquals(2, failedStudents.get(student2));
    }

    @Test
    public void testGetFailedStudentsEmpty() {
        assertEquals(0, ScoreManager.getFailedStudents(new ArrayList<>()).size());
    }

    @Test
    public void testGetFailedStudentsFailure() {
        assertThrows(NullPointerException.class, () -> ScoreManager.getFailedStudents(null));
    }
}
