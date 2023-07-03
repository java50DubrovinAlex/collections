package telran.util.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ArrayListTest {
	ArrayList <Integer> array = new ArrayList<Integer>();
	@BeforeEach
	void setUp() throws Exception {
		array.add(1);
		array.add(2);
		array.add(3);
		array.add(4);
		array.add(5);
		array.add(5);
	}

	@Test
	void addTest() {
		Integer[] expected = {0, 1, 2, 3, 4, 5, 5, 5};
		Integer[] ar = new Integer [8];
		array.add(0, 0);
		array.add(5);
		array.toArray(ar);
		assertArrayEquals(expected, ar);
	}
	@Test
	void addAllTest() {
		ArrayList<Integer> ar = new ArrayList <Integer>();
		Integer[] objToArray = new Integer [9];
		ar.add(4);
		ar.add(5);
		ar.add(6);
		array.addAll(ar);
		array.toArray(objToArray);
		Integer [] expected = {1, 2, 3, 4, 5, 5, 4, 5, 6};
		assertArrayEquals(expected, objToArray);
	}
	@Test
	void indexOfAndLastIndexOfTest() {
		int expected1 = 4;
		int expected2 = 5;
		int expected3 = -1;
		assertEquals(expected1, array.indexOf(5));
		assertEquals(expected2, array.lastIndexOf(5));
		assertEquals(expected3, array.indexOf(8));
		assertEquals(expected3, array.lastIndexOf(8));
	}
	@Test
	void indexOfAndLastIndexOfPredicateTest() {
		int expected1 = 1;
		int expected2 = 3;
		int expected3 = -1;
		assertEquals(expected1, array.indexOf(elm -> elm % 2 == 0));
		assertEquals(expected2, array.lastIndexOf(elm -> elm % 2 == 0));
		assertEquals(expected3, array.indexOf(elm -> elm % 8 == 0));
		assertEquals(expected3, array.lastIndexOf(elm -> elm % 8 == 0));
		
	}
	@Test
	
	void removIndexTest() {
		Integer [] expected = {1, 2, 3, 4, 5};
		Integer[] ar = new Integer [5];
		array.remove(4);
		array.toArray(ar);
		assertArrayEquals(expected, ar);
	}
	@Test
	
	void removeTest() {
		Integer [] expected = {2, 3, 4, 5, 5};
		Integer[] ar = new Integer [5];
		Integer obj = 1;
		array.remove(obj);
		array.toArray(ar);
		assertArrayEquals(expected, ar);
	}
	
	
	@Test
	
	void removPredicateTest() {
		Integer [] expected = {1, 2, 3, 4};
		Integer[] ar = new Integer [4];
		array.removeIf(elm -> elm == 5);
		array.toArray(ar);
		assertArrayEquals(expected, ar);
	}
	@Test
	
	void removAllTest() {
		ArrayList <Integer> array1 = new ArrayList<Integer>();
		array1.add(1);
		array1.add(2);
		array1.add(3);
		array.removeAll(array1);
		Integer [] expected = {4, 5, 5};
		Integer[] ar = new Integer [3];
		array.toArray(ar);
		assertArrayEquals(expected, ar);
		
	}

}
