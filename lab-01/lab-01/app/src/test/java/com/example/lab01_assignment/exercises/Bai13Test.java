package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;

public class Bai13Test {

    @Test
    public void testFindTwoMax() {
        int[] arr = {3, 7, 2, 9, 5};
        int[] result = Bai13.findTwoMax(arr);
        assertEquals(9, result[0]); // max1
        assertEquals(3, result[1]); // idx1
        assertEquals(7, result[2]); // max2
        assertEquals(1, result[3]); // idx2
    }

    @Test
    public void testSortArray() {
        int[] arr = {5, 1, 4, 2};
        int[] sorted = Bai13.sortArray(arr);
        assertArrayEquals(new int[]{1, 2, 4, 5}, sorted);
    }

    @Test
    public void testInsertSorted_Middle() {
        int[] arr = {1, 3, 5, 7};
        int[] newArr = Bai13.insertSorted(arr, 4);
        assertArrayEquals(new int[]{1, 3, 4, 5, 7}, newArr);
    }

    @Test
    public void testInsertSorted_AtBeginning() {
        int[] arr = {2, 3, 4};
        int[] newArr = Bai13.insertSorted(arr, 1);
        assertArrayEquals(new int[]{1, 2, 3, 4}, newArr);
    }

    @Test
    public void testInsertSorted_AtEnd() {
        int[] arr = {1, 2, 3};
        int[] newArr = Bai13.insertSorted(arr, 5);
        assertArrayEquals(new int[]{1, 2, 3, 5}, newArr);
    }
}
