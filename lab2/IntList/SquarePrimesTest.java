package IntList;

import static org.junit.Assert.*;

import jh61b.junit.In;
import org.junit.Test;

import javax.swing.plaf.basic.BasicInternalFrameUI;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple2() {
        IntList lst = IntList.of(3, 5, 7, 11, 13);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("9 -> 25 -> 49 -> 121 -> 169", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple3() {
        IntList lst = IntList.of(3, 5, 7, 11, 4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("9 -> 25 -> 49 -> 121 -> 4", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple4() {
        IntList lst = IntList.of(4, 5, 7, 11, 4);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 25 -> 49 -> 121 -> 4", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimple5() {
        IntList lst = IntList.of(4, 6, 8, 9, 10);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 6 -> 8 -> 9 -> 10", lst.toString());
        assertTrue(changed);
    }
}
