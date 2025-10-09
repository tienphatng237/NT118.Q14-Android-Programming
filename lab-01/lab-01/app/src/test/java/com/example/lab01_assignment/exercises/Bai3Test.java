package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

public class Bai3Test {

    @Test
    public void testGenerateSquareMapWith3() {
        Map<Integer, Integer> map = Bai3.generateSquareMap(3);
        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.get(1));
        assertEquals(Integer.valueOf(4), map.get(2));
        assertEquals(Integer.valueOf(9), map.get(3));
    }

    @Test
    public void testGenerateSquareMapWith5() {
        Map<Integer, Integer> map = Bai3.generateSquareMap(5);
        assertEquals(5, map.size());
        assertEquals(Integer.valueOf(25), map.get(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateSquareMapWith0() {
        Bai3.generateSquareMap(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateSquareMapWithNegative() {
        Bai3.generateSquareMap(-2);
    }
}
