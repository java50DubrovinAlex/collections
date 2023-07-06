package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

abstract class CollectionTest {
Integer [] numbers = {10, -20, 8, 14, 30, 12, 100};
Collection<Integer> collection;
	@BeforeEach
	void setUp()  {
		for(Integer num: numbers) {
			collection.add(num);
		}
	}

	@Test
	abstract void addTest();
	 @Test
	 void removeTest() {
		 Integer[] expected1 = {-20, 8, 14, 30, 12, 100};
		 Integer[] expected2 = {-20, 8, 14, 30, 12};
		 Integer[] expected3 = {-20, 8,  30, 12};
		 assertTrue(collection.remove(10));
		 assertArrayEquals(expected1, collection.toArray(new Integer[0]));
		 assertTrue(collection.remove(100));
		 assertArrayEquals(expected2, collection.toArray(new Integer[0]));
		 assertTrue(collection.remove(14));
		 assertArrayEquals(expected3, collection.toArray(new Integer[0]));
		 assertFalse(collection.remove(100000));
		 assertArrayEquals(expected3, collection.toArray(new Integer[0]));
	 }
	 @Test
	 void clearTest() {
		 collection.clear();
		 assertEquals(0, collection.size());
	 }
	 @Test
	 void containsTest() {
		 assertTrue(collection.contains(14));
		 assertFalse(collection.contains(1000000));
	 }
	 @Test
	 void containsAllTest() {
		 Integer ar1[] = {10, -20};
		 Integer ar2[] = {10, 100000};
		 Collection<Integer> col1 = getCollection(ar1);
		 Collection<Integer> col2 = getCollection(ar2);
		 assertTrue(collection.containsAll(col1));
		 assertFalse(collection.containsAll(col2));
	 }
	 @Test
	 void toArrayTest() {
		 Integer[] ar = new Integer[1000];
		 Arrays.fill(ar, 10);
		 Integer[] actual = collection.toArray(ar);
		 assertTrue(ar == actual);
		 assertNull(actual[collection.size()]);
		 assertArrayEquals(numbers, Arrays.copyOf(actual, collection.size()));
		 
	 }
	 @Test
	 void removeIfTest() {
		 Integer [] expected = {10, -20, 8, 14,  12};
		 assertTrue(collection.removeIf(num -> num >= 30));
		 assertArrayEquals(expected, collection.toArray(new Integer[0]));
	 }
	 @Test
	 void addAllTest() {
		 Integer [] ar = {1, 11};
		 Integer [] expected = {10, -20, 8, 14, 30, 12, 100, 1, 11};
		 assertTrue(collection.addAll(getCollection(ar)));
		 assertArrayEquals(expected, collection.toArray(new Integer[0]));
	 }
	 @Test
	 void removeAllTest() {
		 Integer[] ar1 = {10, -20, 1000};
		 Collection<Integer> col1 = getCollection(ar1);
		 Integer [] expected = { 8, 14, 30, 12, 100};
		 assertTrue(collection.removeAll(col1));
		 assertFalse(collection.removeAll(col1));
		 assertArrayEquals(expected, collection.toArray(new Integer[0]));
	 }

	protected abstract Collection<Integer> getCollection(Integer[] ar1);
}