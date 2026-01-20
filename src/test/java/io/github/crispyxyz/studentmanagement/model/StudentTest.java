package io.github.crispyxyz.studentmanagement.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    Student student;

    @BeforeEach
    public void setUp() {
        student = new Student("1001", 24, "李田所", "天体物理");
    }

    @AfterEach
    public void tearDown() {
        student = null;
    }

    @Test
    public void testSetMajorSuccess() {
        assertDoesNotThrow(() -> student.setMajor("专业"));
        assertEquals("专业", student.getMajor());
    }

    @Test
    public void testSetMajorFailure() {
        assertThrows(NullPointerException.class, () -> student.setMajor(null));
        assertThrows(IllegalArgumentException.class, () -> student.setMajor(""));
        assertThrows(IllegalArgumentException.class, () -> student.setMajor(" \n \t"));
    }

    @Test
    public void testSetScoresSuccess() {
        Map<String, Double> scores = new HashMap<>();
        scores.put("经典力学", 88.8);
        scores.put("电动力学", 77.7);
        assertDoesNotThrow(() -> student.setScores(scores));
        assertEquals(scores, student.getScores());
    }

    @Test
    public void testSetScoresFailure() {
        assertThrows(NullPointerException.class, () -> student.setScores(null));

        Map<String, Double> scores1 = new HashMap<>();
        scores1.put("", 100.0);
        Exception e1 = assertThrows(IllegalArgumentException.class, () -> student.setScores(scores1));
        assertTrue(e1.getMessage().contains("空"));

        Map<String, Double> scores2 = new HashMap<>();
        scores2.put(null, 0.0);
        assertThrows(NullPointerException.class, () -> student.setScores(scores2));

        Map<String, Double> scores3 = new HashMap<>();
        scores3.put("CET-4", 710.0);
        Exception e2 = assertThrows(IllegalArgumentException.class, () -> student.setScores(scores3));
        assertTrue(e2.getMessage().contains("范围"));
    }
}
