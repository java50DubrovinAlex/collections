package telran.util;

import java.util.ListIterator;
import java.util.function.Predicate;

public interface List<T> extends Collection<T> {
	void add(int index, T obj);

	T get(int index);

	T set(int index, T obj);

	T remove(int index);

	int indexOf(Predicate<T> predicate);

	int lastIndexOf(Predicate<T> predicate);

	@Override
	default boolean remove(Object pattern) {
		int index = indexOf(pattern);
		boolean res = false;
		if (index >= 0) {
			res = true;
			remove(index);
		}
		return res;
	}

	@Override
	default boolean contains(Object pattern) {
		return indexOf(pattern) >= 0;
	}

	default void indexValidation(int index, boolean sizeInclusive) {
		int size = size();
		int bounder = sizeInclusive ? size : size - 1;
		if (index < 0 || index > bounder) {
			throw new IndexOutOfBoundsException(index);
		}

	}

	default int indexOf(Object pattern) {
		return indexOf(Predicate.isEqual(pattern));
	}

	default int lastIndexOf(Object pattern) {
		return lastIndexOf(Predicate.isEqual(pattern));
	}

	default boolean listEqualsTo(Object other) {
		// TODO Checks other is List having the equal elements in the same order (using iteration)
		if(!(other instanceof List)) {
			return false;
		}
		List<Collection<T>> otherList =  (List<Collection<T>>) other;
		if(this.size() != otherList.size()) {
			return false;
		}
		ListIterator<T> thisIterator = (ListIterator<T>) this.iterator();
		ListIterator<T> otherListIterator = (ListIterator<T>) otherList.iterator();
		while(thisIterator.hasNext() && otherListIterator.hasNext()) {
			T thisElem = thisIterator.next();
			T otherElem = otherListIterator.next();
			if(!thisElem.equals(otherElem)) {
				return false;
			}
			
		}
		
			
		
		return true;
	}
}