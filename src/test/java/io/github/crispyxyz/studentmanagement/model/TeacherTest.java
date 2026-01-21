package io.github.crispyxyz.studentmanagement.model;

import io.github.crispyxyz.studentmanagement.exception.InvalidCourseException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherTest {

    @Test
    public void testSetCourseSuccess() {
        Teacher teacher = new Teacher("2001", 55, "常凯申");
        List<String> courses = new ArrayList<>();
        courses.add("军事物流学");
        assertDoesNotThrow(() -> teacher.setCourses(courses));
        assertEquals(courses, teacher.getCourses());
    }

    @Test
    public void testSetCourseFailure() {
        Teacher teacher = new Teacher("2001", 55, "常凯申");
        assertThrows(NullPointerException.class, () -> teacher.setCourses(null));

        List<String> courses1 = new ArrayList<>();
        courses1.add(null);
        assertThrows(NullPointerException.class, () -> teacher.setCourses(courses1));

        List<String> courses2 = new ArrayList<>();
        courses2.add("");
        assertThrows(IllegalArgumentException.class, () -> teacher.setCourses(courses2));
    }

    @Test
    public void testRecordScoreSuccess() {
        Teacher teacher = new Teacher("2001", 55, "常凯申");
        teacher.setCourses(Collections.singletonList("军事理论"));

        Student student = new Student("1001", 24, "张三", "材料化学");
        assertDoesNotThrow(() -> teacher.recordScore("军事理论", student, 99.9));
        assertTrue(student.getScores().containsKey("军事理论"));
        assertEquals(99.9, student.getScores().get("军事理论"));
    }

    @Test
    public void testRecordScoreFailure() {
        Teacher teacher = new Teacher("2001", 55, "常凯申");
        teacher.setCourses(Collections.singletonList("军事理论"));

        Student student = new Student("1001", 24, "张三", "材料化学");
        assertThrows(InvalidCourseException.class, () -> teacher.recordScore("理论力学", student, 60.1));

    }
}
