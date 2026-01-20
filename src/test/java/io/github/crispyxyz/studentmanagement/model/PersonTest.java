package io.github.crispyxyz.studentmanagement.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    TestPerson person;

    @BeforeEach
    public void setUp() {
        person = new TestPerson("001", 19, "张三");
    }

    @AfterEach
    public void tearDown() {
        person = null;
    }

    @Test
    public void testSetNameSuccess() {
        assertDoesNotThrow(() -> person.setName("张四"));
        assertEquals("张四", person.getName());
    }

    @Test
    public void testSetNameFailure() {
        assertThrows(NullPointerException.class, () -> person.setName(null));
        assertThrows(IllegalArgumentException.class, () -> person.setName(""));
        assertThrows(IllegalArgumentException.class, () -> person.setName(" \n \t"));
    }

    @Test
    public void testSetAgeSuccess() {
        assertDoesNotThrow(() -> person.setAge(20));
        assertEquals(20, person.getAge());
    }

    @Test
    public void testSetAgeFailure() {
        assertThrows(IllegalArgumentException.class, () -> person.setAge(-20));
    }

    @Test
    public void testSetIdSuccess() {
        assertDoesNotThrow(() -> person.setId("002"));
    }

    @Test
    public void testSetIdFailure() {
        assertThrows(NullPointerException.class, () -> person.setId(null));
        assertThrows(IllegalArgumentException.class, () -> person.setId(""));
        assertThrows(IllegalArgumentException.class, () -> person.setId(" \n \t"));
    }

    private static class TestPerson extends Person {
        public TestPerson(String id, int age, String name) {
            super(id, age, name);
        }
    }
}
