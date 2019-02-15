import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Miroslav Mirkovic
 */

public class BST<Key extends Comparable<Key>, Value> {

	private Comparator<Key> comp;
	private Node root;

	private class Node {
		Key key;
		Value value;
		Node left, right;
		int size;

		public String toString() {
			return key + " " + value;
		}
	}

	public BST() {
		this(Comparator.naturalOrder());
	}

	public BST(Comparator<Key> c) {
		comp = c;
		root = null;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.size;
	}

	public void put(Key key, Value value) {
		if (root == null) {
			comp.compare(key, key);
			root = new Node();
			root.key = key;
			root.value = value;
			return;
		}
		put(root, key, value);
	}

	private void put(Node n, Key key, Value value) {
		int compare = comp.compare(key, n.key);
		if (compare == 0) {
			n.value = value;
			return;
		}
		if (compare < 0) {
			if (n.left == null) {
				n.left = new Node();
				n.left.key = key;
				n.left.value = value;
			} else {
				put(n.left, key, value);
			}
		} else {
			if (n.right == null) {
				n.right = new Node();
				n.right.key = key;
				n.right.value = value;
			} else {
				put(n.right, key, value);
			}
		}
		n.size = 1 + size(n.left) + size(n.right);
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node n, Key key) {
		if (n == null)
			return null;
		int compare = comp.compare(key, n.key);
		if (compare < 0) {
			return get(n.left, key);
		} else if (compare > 0) {
			return get(n.right, key);
		} else {
			return n.value;
		}
	}

	public boolean containsKey(Key key) {
		return get(key) != null;
	}

	public Key minKey() {
		return minKey(root).key;
	}

	private Node minKey(Node n) {
		if (n.left == null)
			return n;
		else
			return minKey(n.left);
	}

	public Key maxKey() {
		return maxKey(root).key;
	}

	private Node maxKey(Node n) {
		if (n.right == null)
			return n;
		else
			return maxKey(n.right);
	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node n) {
		if (n != null) {
			System.out.println(n);
			preOrder(n.left);
			preOrder(n.right);
		}
	}

	public List<Key> keysInRange(Key a, Key b) {
		List<Key> keys = new ArrayList<>();
		keysInRange(root, keys, a, b);
		return keys;
	}

	private void keysInRange(Node n, List<Key> keys, Key a, Key b) {
		if (n == null)
			return;
		int minComp = comp.compare(a, n.key);
		int maxComp = comp.compare(b, n.key);
		if (minComp < 0)
			keysInRange(n.left, keys, a, b);
		if (minComp <= 0 && maxComp >= 0)
			keys.add(n.key);
		if (maxComp > 0)
			keysInRange(n.right, keys, a, b);
	}

	public int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to rank() is null");
		return rank(key, root);
	}

	private int rank(Key key, Node x) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(key, x.left);
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		else
			return size(x.left);
	}

	public int height() {
		return height(root);
	}

	private int height(Node n) {
		if (n == null)
			return -1;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node n) {
		if (n.left == null) {
			return n.right;
		}
		n.left = deleteMin(n.left);
		return n;
	}

	public void delete(Key key) {
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;

			Node t = x;
			x = minKey(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		return x;
	}

	public void printSideways() {
		printSideways(root, 0);
	}

	private String sep = "+--";

	private void printSideways(Node c, int level) {
		if (c == null)
			return;
		printSideways(c.right, level + 1);
		for (int i = 0; i < level; i++)
			System.out.print(sep);
		System.out.println(c);
		printSideways(c.left, level + 1);
	}

	public static void main(String[] args) {

	}
}
