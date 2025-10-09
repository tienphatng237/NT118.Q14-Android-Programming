package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai10Test {

    @Test
    public void testEmptyString() {
        assertEquals(0, Bai10.countWords(""));
    }

    @Test
    public void testNullString() {
        assertEquals(0, Bai10.countWords(null));
    }

    @Test
    public void testSingleWord() {
        assertEquals(1, Bai10.countWords("Hello"));
    }

    @Test
    public void testMultipleWords() {
        assertEquals(4, Bai10.countWords("Xin chào các bạn"));
    }

    @Test
    public void testStringWithExtraSpaces() {
        assertEquals(3, Bai10.countWords("  one   two three   "));
    }

    @Test
    public void testStringWithTabsAndNewlines() {
        assertEquals(3, Bai10.countWords("one\ttwo\nthree"));
    }
}
