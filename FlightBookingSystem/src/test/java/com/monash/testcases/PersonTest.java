package com.monash.testcases;

import com.monash.mainclasses.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void testPersonFields() {
        // Test with missing fields
        assertThrows(IllegalArgumentException.class, () -> new Person(null, "Gomez", 25, "Man"));
        assertThrows(IllegalArgumentException.class, () -> new Person("Roy", null , 25, "Man"));
        assertThrows(IllegalArgumentException.class, () -> new Person("Roy", "Gomez", -1, "Man"));
        assertThrows(IllegalArgumentException.class, () -> new Person("Roy", "Gomez", 25, null));

        // Testing with all fields present
        Person person = new Person("John", "Smith", 25, "Man");
        assertNotNull(person);
    }

    @Test
    public void testPersonGender() {
        Person person = new Person();

        // Testing with incorrect input
        assertThrows(IllegalArgumentException.class, () -> person.setGender("M"));

        // Testing with correct input
        person.setGender("Man");
        assertEquals("Man", person.getGender());
    }

    @Test
    public void testNames() {
        Person person = new Person();

        //Testing with non-alphabetic input
        assertThrows(IllegalArgumentException.class, () -> person.setFirstName("123"));
        assertThrows(IllegalArgumentException.class, () -> person.setSecondName("123"));

        // Testing with alphabetic input
        person.setFirstName("John");
        person.setSecondName("Smith");

        assertEquals("John", person.getFirstName());
        assertEquals("Smith", person.getSecondName());
    }
}