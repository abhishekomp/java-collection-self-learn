package org.example.puzzle.min_steps_end_of_array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReachTillEndOfArrayTest {

    ReachTillEndOfArray runner;

    @BeforeEach
    void setUp() {
        runner = new ReachTillEndOfArray();
    }

    @Test
    void canReachTillEndOfArray() {
        int[] intArr = new int[] {2, 2, 3, 3, 2, 0, 1};
        boolean result = runner.canReachTillEndOfArray(intArr);
        System.out.println("result = " + result);
        assertTrue(result);
    }

    @Test
    void cannotReachTillEndOfArray() {
        int[] intArr = new int[] {1, 0, 4};
        boolean result = runner.canReachTillEndOfArray(intArr);
        System.out.println("result = " + result);
        assertFalse(result);
    }

    @Test
    void minNumberOfJumpsToReachEnd() {
        int[] intArr = new int[] {2, 2, 3, 3, 2, 0, 1};
        int minJumps = runner.minNumberOfJumpsToReachEnd(intArr);
        System.out.println("result = " + minJumps);
        assertEquals(3, minJumps);
    }

    @Test
    void minNumberOfJumpsToReachEnd_should_be_minus_one() {
        int[] intArr = new int[] {1, 0, 4};
        int minJumps = runner.minNumberOfJumpsToReachEnd(intArr);
        System.out.println("result = " + minJumps);
        assertEquals(-1, minJumps);
    }
}