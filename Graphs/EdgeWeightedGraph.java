import java.util.LinkedList;
import java.util.List;

/**
 * @author Miroslav Mirkovic
 */

public class EdgeWeightedGraph {
	private final int V;
	private final List<Edge>[] adj;

	public EdgeWeightedGraph(int V) {
		this.V = V;
		adj = new LinkedList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new LinkedList<>();
	}

	public int V() {
		return V;
	}

	public void addEdge(Edge e) {
		int v = e.either(), w = e.other(v);
		adj[v].add(e);
		adj[w].add(e);
	}

	public Iterable<Edge> adj(int v) {
		return adj[v];
	}

}