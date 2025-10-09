package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai9Test {

    @Test
    public void testPrimeNumber() {
        assertEquals("13", Bai9.factorize(13)); // số nguyên tố → chính nó
    }

    @Test
    public void testCompositeNumber() {
        assertEquals("2 x 2 x 3", Bai9.factorize(12)); // 12 = 2*2*3
    }

    @Test
    public void testLargeNumber() {
        assertEquals("2 x 2 x 5 x 5 x 7", Bai9.factorize(700)); // 700 = 2*2*5*5*7
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorizeWithZero() {
        Bai9.factorize(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorizeWithOne() {
        Bai9.factorize(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorizeWithNegative() {
        Bai9.factorize(-15);
    }
}
