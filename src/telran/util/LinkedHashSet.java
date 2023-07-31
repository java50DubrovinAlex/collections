package telran.util;

import java.util.Iterator;

import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements Set<T> {
	HashMap<T, LinkedList.Node<T>> map = new HashMap<>();
	LinkedList<T> list = new LinkedList<>();
	@Override
	public boolean add(T obj) {
		if(!map.containsKey(obj)) {
			LinkedList.Node<T>node = new Node<>(obj);
			list.addTail(node);
			map.put(obj, list.tail);
			return true;
		}
		
		
		return false;
	}

	@Override
	public boolean remove(Object pattern) {
		if(map.containsKey(pattern)) {
			map.remov(pattern);
			list.removeNode(map.get(pattern));
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object pattern) {
		if(map.containsKey(pattern)) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Iterator<T> iterator() {
		
		return list.iterator();
	}

	@Override
	public T get(Object pattern) {
		if(map.containsKey(pattern)) {
			return list.get(list.indexOf(pattern));
		}
		return null;
	}

}