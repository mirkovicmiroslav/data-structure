
/**
 * @author Miroslav Mirkovic
 */

public class DFS {
	private boolean[] marked;
	private int count;

	public DFS(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		count++;
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
	}

	public boolean marked(int v) {
		return marked[v];
	}

	public int count() {
		return count;
	}

	public static void main(String[] args) {
		Graph graph = new Graph(5);
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(4, 0);
		System.out.println(graph);

	}

}
