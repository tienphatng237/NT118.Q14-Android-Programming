package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class Bai7Test {

    @Test
    public void testIsPrimeBasic() {
        assertTrue(Bai7.isPrime(2));
        assertTrue(Bai7.isPrime(3));
        assertFalse(Bai7.isPrime(4));
        assertTrue(Bai7.isPrime(17));
        assertFalse(Bai7.isPrime(1));
        assertFalse(Bai7.isPrime(0));
    }

    @Test
    public void testListPrimesLessThan10() {
        List<Integer> primes = Bai7.listPrimesLessThanN(10);
        assertArrayEquals(new Integer[]{2, 3, 5, 7}, primes.toArray(new Integer[0]));
    }

    @Test
    public void testListPrimesLessThan2() {
        List<Integer> primes = Bai7.listPrimesLessThanN(2);
        assertTrue(primes.isEmpty());
    }

    @Test
    public void testListPrimesLessThan20() {
        List<Integer> primes = Bai7.listPrimesLessThanN(20);
        assertArrayEquals(new Integer[]{2, 3, 5, 7, 11, 13, 17, 19}, primes.toArray(new Integer[0]));
    }
}
