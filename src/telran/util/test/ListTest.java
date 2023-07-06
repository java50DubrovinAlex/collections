package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

abstract class ListTest extends CollectionTest {

	List<Integer> list;
	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		list = (List<Integer>)collection;
	}

	

	@Override
	void addTest() {
		Integer [] expected = {10,  -20, 8, 14, 30, 12, 100, 10};
		assertTrue(list.add(10));
		assertArrayEquals(expected, list.toArray(new Integer[0]));
		
	}
	@Test
	void addIndexTest() {
		Integer [] expected = {10, 10, -20, 8, 14, 30, 12, 100};
		list.add(0, 10);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	@Test
	void removeIndexTest() {			
		Integer [] expected = {10,  -20, 8, 14, 30, 12};
		list.remove(6);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}
	@Test
	void indexOfTest() {
		assertEquals(6, list.indexOf(100));
		assertEquals(-1, list.indexOf(100000));
	}
	

}