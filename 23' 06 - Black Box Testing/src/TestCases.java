import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TestCases {

	//Switch which line is commented out in order to test the correct vs broken code
	//TestingFunctions functions = new BlackBoxCorrect();
	//TestingFunctions functions = new BlackBoxIncorrect();
	TestingFunctions functions = new MyFunctions();
	
	/**
	 * This is a simple validity check for the method greatestCommonDivisor. Checks that the method
	 * returns the correct result for a known GCD problem gcd(2,4) = 2
	 */
	@Test
	public void testGCD() { // provided test
		assertEquals("Error: should return 2", 2, functions.greatestCommonDivisor(2, 4));
	}
	@Test
	public void gcdNeg() { // negative, and 0 value inputs
		assertEquals("Error: should return -1", -1, functions.greatestCommonDivisor(-2, 0));
	}
	@Test
	public void gcdOne() { // inputs with a GCD of 1
		assertEquals("Error: should return 1", 1, functions.greatestCommonDivisor(1, 5));
	}
	@Test
	public void gcdEqual() { // GCD equal values
		assertEquals("Error: should return 1", 1, functions.greatestCommonDivisor(1, 1));
	}
	/**
	 * This is a simple check of the reverseWindow method. Checks if the method will reverse the entire contents
	 * of the passed array correctly.
	 */
	@Test (expected = IndexOutOfBoundsException.class)
	public void revWinOB() { // out of bound array size
		int[] arrInput = {1,2,3,4};
		functions.reverseWindow(arrInput, 0, 5);
		int[] arrAns = {4,3,2,1};
		assertArrayEquals(arrAns, arrInput);  
	}
	@Test
	public void revWinAB() { // at bounds array size
		int[] arrInput = {1,2,3,4};
		functions.reverseWindow(arrInput, 0, 4);
		int[] arrAns = {4,3,2,1};
		assertArrayEquals(arrAns, arrInput);
	}
	@Test
	public void revWinRV() { // reversed input indicies
		int[] arrInput = {1,2,3,4};
		functions.reverseWindow(arrInput, 4, 0);
		int[] arrAns = {4,3,2,1};
		assertArrayEquals(arrAns, arrInput);
	}
	@Test
	public void revWinEMTY() { // input with empty array
		int[] arrInput = {};
		functions.reverseWindow(arrInput, 0, 0);
		int[] arrAns = {};
		assertArrayEquals(arrInput, arrAns);
	}
	@Test
	public void revWinEQL() { // input with equal array
		int[] arrInput = {1,2,3,4};
		functions.reverseWindow(arrInput, 1, 1);
		int[] arrAns = {1,2,3,4};
		assertArrayEquals(arrInput, arrAns);
	}

	//For completion, write additonal tests as described in the lab documentation.
}
