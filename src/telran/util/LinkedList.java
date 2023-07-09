package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T>{
		T obj;
		Node<T> next;
		Node<T> prev;
		Node(T object){
			this.obj = object;
		}
	}
	Node<T> head;
	Node<T> tail;
	int size;
	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true;
	}

	private void addNode(int index, Node<T> node) {
		
		if(index == size) {
			addTail(node);
		}else if(index == 0) {
			addHead(node);
		}else {
			addMiddle(index, node);
		}
		size++;
	}

	private void addMiddle(int index, Node<T> node) {
		Node<T> nextNode = getNode(index);
		Node<T> prevNode = nextNode.prev;
		node.next = nextNode;
		nextNode.prev = node;
		prevNode.next = node;
		node.prev = prevNode;
		
	}

	private void addHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;
	}

	private void addTail(Node<T> node) {
		if(tail == null) {
			head = tail = node;
		}else {
			tail.next = node;
			node.prev = tail;
		}
		tail = node;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<T>{
		int currentIndex = 0;
		Node<T> current =  head;
		boolean canRemove = false;
		@Override
		public boolean hasNext() {
			
			return currentIndex < size; 
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			canRemove = true;
			currentIndex++;
			T res = current.obj;
			current = current.next;
			return  res;
		}
		@Override
		public void remove() {
			if(!canRemove) {
				throw new IllegalStateException();
			}
			
			LinkedList.this.remove(--currentIndex);
			canRemove = false;
		}
		
	}

	@Override
	public void add(int index, T obj) {
		indexValidation(index, true);
		Node<T> node = new Node<>(obj);
		addNode(index, node);

	}

	@Override
	public T get(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		return node.obj;
	}

	private Node<T> getNode(int index) {
		
		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for(int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromHead(int index) {
		Node<T> current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	@Override
	public T set(int index, T obj) {
		indexValidation(index, false);
		T current =  get(index);
		T prevElement = current;
		current = obj;
		
		return prevElement;
	}

	@Override
	public T remove(int index) {
		indexValidation(index, true);
		T res = null;
		if(index == 0) {
			res = head.obj;
			head = head.next;
			
		}else if(index == size - 1) {
			res = tail.obj;
			tail = tail.prev;
		}else {
			Node<T>removeNext = getNodeFromHead(index + 1);
			Node<T>removdePrev = getNodeFromHead(index - 1);
			res = removeNext.prev.obj;
			removeNext.prev = removdePrev;
			removdePrev.next = removeNext;
		}
		size--;
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		int index = 0;
		for(T obj: this) {
			if(obj == pattern) {
				return index;
			}
			index++;
		}
		
		return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		int index = 0;
		int lastIndex = -1;
		for(T obj: this) {
			if(obj == pattern) {
				lastIndex = index;
			}
			index++;
		}
		return lastIndex;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int index = 0;
		for(T obj: this) {
			if(predicate.test(obj)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int index = 0;
		int lastIndex = -1;
		for(T obj: this) {
			if(predicate.test(obj)) {
				lastIndex = index;
			}
			index++;
		}
		return lastIndex;
	}

}
