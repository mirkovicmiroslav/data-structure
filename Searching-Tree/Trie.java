import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author Miroslav Mirkovic
 */

public class Trie {

	Node root;

	private class Node {
		Map<Character, Node> kids = new TreeMap<>();
		int size;
	}

	public Trie() {
		root = new Node();
	}

	public void put(String s) {
		put(root, new StringBuilder(s));
	}

	private void put(Node n, StringBuilder s) {
		if (s.length() == 0) {
			return;
		}
		Character c = s.charAt(0);
		Node end = n.kids.get(c);
		if (end == null) {
			end = new Node();
			n.kids.put(c, end);
		}
		s.deleteCharAt(0);
		if (s.length() == 0) {
			end.size++;
		} else {
			put(end, s);
		}
	}

	public int get(String key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		Node x = get(root, key, 0);
		if (x == null)
			return 0;
		return x.size;
	}

	private Node get(Node x, String key, int d) {
		if (x == null)
			return null;
		if (d == key.length())
			return x;
		char c = key.charAt(d);
		return get((Node) x.kids.get(c), key, d + 1);
	}

	public boolean contains(String key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != 0;
	}

	public void printInternal() {
		printInternal(root, 0);
	}

	private void printInternal(Node n, int depth) {
		if (n == null) {
			return;
		}
		for (int i = 0; i < depth; i++) {
			System.out.print(" + ");
		}
		System.out.printf("%1d -- %2s%n", n.size, n.kids.keySet());
		for (Node kid : n.kids.values()) {
			printInternal(kid, depth + 1);
		}
	}

	public void printAll() {
		printAll(root, new StringBuilder());
	}

	public void printAll(Node c, StringBuilder sb) {
		if (c.size > 0)
			System.out.printf("%1s : %2d%n", sb.toString(), c.size);
		for (Entry<Character, Node> e : c.kids.entrySet()) {
			sb.append(e.getKey());
			printAll(e.getValue(), sb);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.put("abakus");
		trie.put("albatros");
		trie.put("albatros");
		trie.put("albatros");
		trie.put("albatros");
		trie.put("bala");
		trie.put("balavac");
		trie.put("papagaj");
		trie.printInternal();
		trie.printAll();
		System.out.println(trie.get("albatros"));
		System.out.println(trie.contains("albatros"));
		
	}

}