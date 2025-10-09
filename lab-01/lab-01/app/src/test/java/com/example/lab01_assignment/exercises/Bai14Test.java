package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai14Test {

    @Test
    public void testFindMax() {
        int[][] mat = {{1, 2}, {9, 4}};
        int[] res = Bai14.findMax(mat);
        assertArrayEquals(new int[]{9, 1, 0}, res);
    }

    @Test
    public void testReplaceNonPrimes() {
        int[][] mat = {{2, 4}, {5, 6}};
        int[][] expected = {{2, 0}, {5, 0}};
        assertArrayEquals(expected, Bai14.replaceNonPrimes(mat));
    }

    @Test
    public void testSortColumns() {
        int[][] mat = {{4, 1}, {2, 3}};
        int[][] sorted = Bai14.sortColumns(mat);
        int[][] expected = {{2, 1}, {4, 3}};
        assertArrayEquals(expected, sorted);
    }

    @Test
    public void testColumnMostPrimes() {
        int[][] mat = {{2, 4}, {3, 5}, {6, 7}};
        int[] res = Bai14.columnMostPrimes(mat);
        // Cột 1 có 2 số nguyên tố (3, 5), cột 0 có 2 số nguyên tố (2, 3)
        assertTrue(res[1] >= 2);
    }

    @Test
    public void testIsPrime() {
        assertTrue(Bai14.isPrime(7));
        assertFalse(Bai14.isPrime(9));
    }
}
