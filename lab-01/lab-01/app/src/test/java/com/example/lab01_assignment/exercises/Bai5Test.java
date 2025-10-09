package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class Bai5Test {

    @Test
    public void testFibonacciWith1() {
        List<Integer> result = Bai5.generateFibonacci(1);
        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(0), result.get(0));
    }

    @Test
    public void testFibonacciWith2() {
        List<Integer> result = Bai5.generateFibonacci(2);
        assertEquals(2, result.size());
        assertEquals(Integer.valueOf(0), result.get(0));
        assertEquals(Integer.valueOf(1), result.get(1));
    }

    @Test
    public void testFibonacciWith5() {
        List<Integer> result = Bai5.generateFibonacci(5);
        assertEquals(5, result.size());
        assertArrayEquals(new Integer[]{0, 1, 1, 2, 3}, result.toArray(new Integer[0]));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFibonacciWithZero() {
        Bai5.generateFibonacci(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFibonacciWithNegative() {
        Bai5.generateFibonacci(-3);
    }
}
