package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai2Test {

    @Test
    public void testFactorialOf0() {
        assertEquals(1, Bai2.factorial(0));
    }

    @Test
    public void testFactorialOf1() {
        assertEquals(1, Bai2.factorial(1));
    }

    @Test
    public void testFactorialOf5() {
        assertEquals(120, Bai2.factorial(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialOfNegative() {
        Bai2.factorial(-3);
    }
}
