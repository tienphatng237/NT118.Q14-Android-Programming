package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class Bai8Test {

    @Test
    public void testIsPrimeBasic() {
        assertTrue(Bai8.isPrime(101));   // số nguyên tố
        assertFalse(Bai8.isPrime(100));  // không phải số nguyên tố
        assertTrue(Bai8.isPrime(99991)); // số nguyên tố 5 chữ số
    }

    @Test
    public void testListFiveDigitPrimes_NotEmpty() {
        List<Integer> primes = Bai8.listFiveDigitPrimes();
        assertFalse(primes.isEmpty());  // chắc chắn có số nguyên tố
    }

    @Test
    public void testListFiveDigitPrimes_FirstAndLast() {
        List<Integer> primes = Bai8.listFiveDigitPrimes();
        assertEquals(Integer.valueOf(10007), primes.get(0));         // số nguyên tố 5 chữ số đầu tiên
        assertEquals(Integer.valueOf(99991), primes.get(primes.size() - 1)); // số nguyên tố 5 chữ số cuối cùng
    }

    @Test
    public void testListFiveDigitPrimes_Size() {
        List<Integer> primes = Bai8.listFiveDigitPrimes();
        // số lượng các số nguyên tố 5 chữ số là 8363
        assertEquals(8363, primes.size());
    }
}
