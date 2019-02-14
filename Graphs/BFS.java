import java.util.LinkedList;
import java.util.Stack;

/**
 * @author Miroslav Mirkovic
 */

public class BFS {
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;

	public BFS(Graph G, int s) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		LinkedList<Integer> queue = new LinkedList<>();
		distTo[s] = 0;
		marked[s] = true;
		queue.add(s);

		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					queue.push(w);
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public int distTo(int v) {
		return distTo[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}

	public static void main(String[] args) {
		Digraph digraph = new Digraph(5);
		digraph.addEdge(0, 1);
		digraph.addEdge(1, 2);
		digraph.addEdge(1, 3);
		digraph.addEdge(4, 0);
		System.out.println(digraph);

	}
	
}
