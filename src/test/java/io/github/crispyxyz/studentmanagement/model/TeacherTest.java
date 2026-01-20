package io.github.crispyxyz.studentmanagement.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
}
