import java.util.LinkedList;
import java.util.List;

/**
 * @author Miroslav Mirkovic
 */

public class Graph {
	private final int V;
	private int E;
	private List<Integer>[] adj;

	public Graph(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = new LinkedList[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new LinkedList<>();
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public int degree(int v) {
		return adj[v].size();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges \n");
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {

	}

}
