package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class Bai11Test {

    @Test
    public void testEmptyString() {
        Map<String, Integer> result = Bai11.countWordFrequency("");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testNullString() {
        Map<String, Integer> result = Bai11.countWordFrequency(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleWord() {
        Map<String, Integer> result = Bai11.countWordFrequency("hello");
        assertEquals(1, (int) result.get("hello"));
    }

    @Test
    public void testMultipleWords() {
        Map<String, Integer> result = Bai11.countWordFrequency("a b c d");
        assertEquals(4, result.size());
        assertEquals(1, (int) result.get("a"));
        assertEquals(1, (int) result.get("b"));
    }

    @Test
    public void testRepeatedWords() {
        Map<String, Integer> result = Bai11.countWordFrequency("hi hi hello hi");
        assertEquals(3, (int) result.get("hi"));
        assertEquals(1, (int) result.get("hello"));
    }

    @Test
    public void testWithSpacesAndTabs() {
        Map<String, Integer> result = Bai11.countWordFrequency("one   two\ttwo\nthree");
        assertEquals(1, (int) result.get("one"));
        assertEquals(2, (int) result.get("two"));
        assertEquals(1, (int) result.get("three"));
    }
}
