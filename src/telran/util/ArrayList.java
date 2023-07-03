package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size = 0;
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}
	public ArrayList() {
		this(DEFAULT_CAPACITY);
		
	}
	@Override
	public boolean add(T obj) {
		if(size == array.length) {
			reallocate();
		}
		array[size++] = obj;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);
	}
	@Override
	public boolean remove(Object pattern) {
		boolean res = false;
		for(int i = 0; i < size;i++) {
			if(array[i] == pattern) {
				res = true;
				size--;
				System.arraycopy(array, 0, array, 0, i);
				System.arraycopy(array, i + 1, array, i, size - i );
				return res;
			}
		}
		return res;
	}

	@Override
	public T[] toArray(T[] ar) {
		T[] res = ar.length < size ? Arrays.copyOf(ar, size) : ar;
		int index = 0;
		for(T obj: this) {
			res[index++] = obj;
		}
		if(res.length > size) {
			res[size] = null;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean res = false;
		for(int i = 0; i < size; i++) {
			if(predicate.test(array[i])) {
				res = true;
				System.arraycopy(array, 0, array, 0, i);
				System.arraycopy(array, i + 1, array, i, size - i );
				size--;
				i--;
			}
		}
		return res;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean addAll(Collection<T> collection) {
		if(collection == null) {
			throw new NullPointerException();
		}
		boolean res = false;
		for(T elm:collection) {
			res  = add(elm);
		}
		return res;
	}

	@Override
	public boolean removeAll(Collection<T> collection) {
		boolean res = false;
		for(int i = 0; i < collection.size();i++) {
			if(remove(((List<T>) collection).get(i))) {
//				size--;
				res = true;
			}
		}
		return res;
	}

	public Iterator<T> iterator() {
	    return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<T> {
	    private int currentIndex = 0;

	    @Override
	    public boolean hasNext() {
	        return currentIndex < size;
	    }

	    @Override
	    public T next() {
	        if (!hasNext()) {
	            throw new NoSuchElementException();
	        }
	        return array[currentIndex++];
	    }
	}
	@Override
	public void add(int index, T obj) {
		indexValidation(index, false);
		size++;
		System.arraycopy(array, 0, array, index + 1, size);
		array[index] = obj;

	}

	@Override	
	public T get(int index) {
		indexValidation(index, false);
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		
		return array[index];
	}

	@Override
	public T set(int index, T obj) {
		indexValidation(index, false);
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		array[index] = obj;
		return obj;
	}

	@Override
	public T remove(int index) {
		indexValidation(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, 0, array, 0, index);	
		System.arraycopy(array, index + 1, array, index, size - index );
		array[size] = null;
		return res;
	}

	private void indexValidation(int index, boolean sizeInclusive) {
		int bounder = sizeInclusive ? size : size - 1;
		if(index < 0 || index > bounder) {
			throw new IndexOutOfBoundsException(index);
		}
		
	}
	@Override
	public int indexOf(T pattern) {
		for(int i = 0; i < size; i++) {
			if(array[i] == pattern) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		for(int i = size - 1; i >= 0; i--) {
			if(array[i] == pattern) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		// TODO Auto-generated method stub
		for(int i = 0; i < size; i++) {
			if(predicate.test(array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		for(int i = size - 1; i >= 0; i--) {
			if(predicate.test(array[i])) {
				return i;
			}
		}
		return -1;
	}

}
