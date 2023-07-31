package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
@SuppressWarnings("unchecked")
public class TreeSet<T> implements SortedSet<T> {
private static class Node<T> {
	T obj;
	Node<T> parent;
	Node<T> left;
	Node<T> right;
	Node(T obj) {
		this.obj = obj;
	}
	public void setNulls() {
		obj = null;
		parent = left = right = null;
		
	}
}
private class TreeSetIterator implements Iterator<T> {
	Node<T> current;
	Node<T> prev;
	boolean flNext = false;

	TreeSetIterator() {
		current = root == null ? null : getLeastFrom(root);
	}

	@Override
	public boolean hasNext() {

		return current != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T res = current.obj;
		prev = current;
		current = getCurrent(current);
		flNext = true;
		return res;
	}

	@Override
	public void remove() {
		if (!flNext) {
			throw new IllegalStateException();
		}
		removeNode(prev);
		flNext = false;
	}
}
	Node<T> root;
	int size;
	Comparator<T> comp;
	private int spacesPerLevel = 2;
	public int getSpacesPerLevel() {
		return spacesPerLevel;
	}
	public void setSpacesPerLevel(int spacesPerLevel) {
		this.spacesPerLevel = spacesPerLevel;
	}
	private Node<T> getCurrent(Node<T> current) {

		return current.right != null ? getLeastFrom(current.right) : getGreaterParent(current);
	}
	private Node<T> getGreaterParent(Node<T> current) {
		while (current.parent != null && current == current.parent.right) {
			current = current.parent;
		}
		return current.parent;
	}
	private Node<T> getParentOrNode(T key) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes;
		while(current != null && (compRes = comp.compare(key, current.obj)) != 0) {
			parent = current;
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? parent : current;
	}
	private Node<T> getParent(T key) {
		//returns null in the case the object matching key exists
		Node<T> node = getParentOrNode(key);
		Node<T> parent = null;
		if (comp.compare(key, node.obj) != 0) {
			parent = node;
		}
		return parent;
	}
	private Node<T> getNode(T key) {
		//returns null in the case the object matching key doesn't exist
				Node<T> node = getParentOrNode(key);
				Node<T> res = null;
				if (node != null && comp.compare(key, node.obj) == 0) {
					res = node;
				}
				return res;
			}
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
	}
	@Override
	public T get(Object pattern) {
		Node<T> node = getNode((T)pattern);
		T res = null;
		if (node != null) {
			res = node.obj;
		
		}
		return res;
	}

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<T>(obj);
		boolean res = false;
		if(root == null) {
			res = true;
			root = node;
		} else {
			Node<T> parent = getParent(obj);
			if(parent != null) {
				res = true;
				node.parent = parent;
				int compRes = comp.compare(obj, parent.obj);
				if(compRes > 0) {
					parent.right = node;
				} else {
					parent.left = node;
				}
			}
		}
		if(res) {
			size++;
		}
		return res;
	}

	@Override
	public boolean remove(Object pattern) {
		boolean res = false;
		Node<T> node = getNode((T)pattern);
		if (node != null) {
			removeNode(node);
			res = true;
		}

		return res;
	}

	private void removeNode(Node<T> node) {
		if (node.left != null && node.right != null) {
			removeJunction(node);
		} else {
			removeNonJunction(node);
		}
		size--;

	}

	private void removeJunction(Node<T> node) {
		Node<T> substitute = getGreatestFrom(node.left);
		node.obj = substitute.obj;
		removeNonJunction(substitute);

	}

	private void removeNonJunction(Node<T> node) {
		Node<T> parent = node.parent;
		Node<T> child = node.left == null ? node.right : node.left;
		if (parent == null) {
			root = child;
		} else {
			if (node == parent.left) {
				parent.left = child;
			} else {
				parent.right = child;
			}

		}
		if (child != null) {
			child.parent = parent;
		}
		node.setNulls();
		
	}
	@Override
	public boolean contains(Object pattern) {
		
		return getNode((T) pattern) != null;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

	@Override
	public T first() {
		T res = null;
		if (root != null) {
			res = getLeastFrom(root).obj;
		}
		
		return res;
	}

	private Node<T> getLeastFrom(Node<T> node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	@Override
	public T last() {
		T res = null;
		if (root != null) {
			res = getGreatestFrom(root).obj;
		}
		
		return res;
	}

	private Node<T> getGreatestFrom(Node<T> node) {
		while(node.right != null) {
			node = node.right;
		}
		return node;
	}
	private T floorCeiling(T pattern, boolean isFloor) {
		T res = null;
		int compRes = 0;
		Node<T> current = root;
		while (current != null && (compRes = comp.compare(pattern, current.obj)) != 0) {
			if ((compRes < 0 && !isFloor) || (compRes > 0 && isFloor)) {
				res = current.obj;
			}
			current = compRes < 0 ? current.left : current.right;
		}
		return current == null ? res : current.obj;

	}
	@Override
	public T ceiling(T key) {
		return floorCeiling(key, false);
	}

	@Override
	public T floor(T key) {
		return floorCeiling(key, true);
	}
	
	public void displayRotated() {
		displayRotated(root, 1);
	}
	private void displayRotated(Node<T> root, int level) {
		if(root != null) {
			displayRotated(root.right, level + 1);
			displayRoot(root, level);
			displayRotated(root.left, level + 1);
			
		}
		
	}
	private void displayRoot(Node<T> root, int level) {
		
		System.out.print(" ".repeat(level * spacesPerLevel ));
		System.out.println(root.obj);
		
	}
	public int width() {
		
		return width(root);
	}
	private int width(Node<T> root) {
		int res = 0;
		if(root != null) {
			if(root.left == null && root.right == null) {
				res = 1;
			} else {
				res = width(root.left) + width(root.right);
			}
		}
		return res;
	}
	public int height() {
		
		return height(root);
	}
	private int height(Node<T> root) {
		int res = 0;
		if (root != null) {
			int leftHeight = height(root.left);
			int rightHeight = height(root.right);
			res = Math.max(leftHeight, rightHeight) + 1;
		}
		return res;
	}
	public void balance() {
		Node<T> [] arrayNodes = getSortedArrayNodes();
		root = balanceArray(arrayNodes, 0, size - 1, null);
		
	}
	private Node<T> balanceArray(Node<T>[] arrayNodes, int left, int right, Node<T> parent) {
		Node<T> root = null;
		if (left <= right) {
			int rootIndex = (left + right) / 2;
			root = arrayNodes[rootIndex];
			root.parent = parent;
			root.left = balanceArray(arrayNodes, left, rootIndex - 1, root);
			root.right = balanceArray(arrayNodes, rootIndex + 1, right, root);
		}
		return root;
	}
	private Node<T>[] getSortedArrayNodes() {
		Node<T> [] res = new Node[size];
		int index = 0;
		Node<T> current = getLeastFrom(root);
		while(current != null) {
			res[index++] = current;
			current = getCurrent(current);
		}
		return res;
	}
	private void inverse(Node<T> root) {
		if(root != null) {
			Node<T> tmp = root.left;
			root.left = root.right;
			root.right = tmp;
			inverse(root.left);
			inverse(root.right);
		}
		
	}
	public void inverse() {
		inverse(root);
		comp = comp.reversed();
		
	}

}