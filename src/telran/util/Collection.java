package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	boolean add(T obj);

	boolean remove(Object pattern);

	default void clear() {
		removeIf(n -> true);
	}

	boolean contains(Object pattern);

	default boolean containsAll(Collection<T> collection) {
		boolean res = true;
		Iterator<T> it = collection.iterator();
		while (it.hasNext() && res) {
			T obj = it.next();
			res = contains(obj);
		}
		return res;
	}

	default T[] toArray(T[] ar) {
		int size = size();
		T[] res = ar.length < size ? Arrays.copyOf(ar, size) : ar;
		int index = 0;
		for (T obj : this) {
			res[index++] = obj;
		}
		if (res.length > size) {
			res[size] = null;
		}
		return res;
	}

	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while (it.hasNext()) {
			if (predicate.test(it.next())) {
				it.remove();
			}
		}
		return oldSize > size();
	}

	int size();

	default boolean addAll(Collection<T> collection) {
		int oldSize = size();
		for (T obj : collection) {
			add(obj);
		}
		return oldSize < size();
	}

	default boolean removeAll(Collection<T> collection) {
		int oldSize = size();
		for (T obj : collection) {
			remove(obj);
		}
		return oldSize > size();
	}

	default boolean retainAll(Collection<T> collection) {
		// TODO Removes from this collection all elements that are not contained by
		// other specified collection
		
		int oldSize = size();
		for(T obj: collection) {
			if(this.contains(obj)) {
				remove(obj);
			}
		}
		return oldSize > size();
	}

	Object clone() throws CloneNotSupportedException;
}