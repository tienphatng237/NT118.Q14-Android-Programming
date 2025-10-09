package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai6Test {

    @Test
    public void testGcdSimple() {
        assertEquals(6, Bai6.gcd(54, 24));
    }

    @Test
    public void testGcdWithPrimeNumbers() {
        assertEquals(1, Bai6.gcd(17, 31));
    }

    @Test
    public void testGcdWhenEqual() {
        assertEquals(10, Bai6.gcd(10, 10));
    }

    @Test
    public void testLcmSimple() {
        assertEquals(216, Bai6.lcm(54, 24));
    }

    @Test
    public void testLcmWhenEqual() {
        assertEquals(10, Bai6.lcm(10, 10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGcdWithZero() {
        Bai6.gcd(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testLcmWithNegative() {
        Bai6.lcm(-4, 6);
    }
}
