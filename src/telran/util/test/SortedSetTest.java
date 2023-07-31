package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.SortedSet;
//Integer [] numbers = {10, -20, 8, 14, 30, 12, 100};
public abstract class SortedSetTest extends SetTest {
	SortedSet<Integer> sortedSet;
	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		sortedSet = (SortedSet<Integer>) collection;
	}

	@Override
	protected void runArrayTest(Integer[] expected, Integer[] actual) {
		expected = Arrays.copyOf(expected, expected.length);
		Arrays.sort(expected);
		assertArrayEquals(expected, actual);

	}
	@Test
	void firstTest() {
		
		assertEquals(-20, sortedSet.first());
		
	}
	@Test
	void lastTest() {
		assertEquals(100, sortedSet.last());
		
	}
	@Test
	void ceilingTest() {
		//{ 10, -20, 7, 50, 100, 30 };
		runTestForExisted(sortedSet, true);
		assertEquals(30, sortedSet.ceiling(15));
		assertEquals(-20, sortedSet.ceiling(-40));
		assertNull(sortedSet.ceiling(101) );
	}
	private void runTestForExisted(SortedSet<Integer> sortedSet, boolean isCeiling) {
		assertEquals(-20, isCeiling ? sortedSet.ceiling(-20) :sortedSet.floor(-20));
		assertEquals(30, isCeiling ? sortedSet.ceiling(30) :sortedSet.floor(30));
		assertEquals(100, isCeiling ? sortedSet.ceiling(100) :sortedSet.floor(100));
	}
	@Test
	void floorTest() {
		runTestForExisted(sortedSet, false);
		assertEquals(30, sortedSet.floor(35));
		assertEquals(100, sortedSet.floor(101));
		assertNull(sortedSet.floor(-40) );
	}

}
