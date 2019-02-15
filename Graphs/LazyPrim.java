
/**
 * @author Miroslav Mirkovic
 * 
 * O(ElogE)
 * 
 */

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LazyPrim {
	private double weight;
	private Queue<Edge> mst;
	private boolean[] marked;
	private PriorityQueue<Edge> pq;

	public LazyPrim(EdgeWeightedGraph g) {
		mst = new LinkedList<>();
		pq = new PriorityQueue<>();
		marked = new boolean[g.V()];
		visit(g, 0);

		while (!pq.isEmpty() && mst.size() < g.V() - 1) {
			Edge e = pq.remove();
			int v = e.either();
			int w = e.other(v);
			if (marked[v] && marked[w])
				continue;
			mst.add(e);
			weight += e.weight();
			if (!marked[v])
				visit(g, v);
			if (!marked[w])
				visit(g, w);
		}
	}

	private void visit(EdgeWeightedGraph g, int s) {
		marked[s] = true;
		for (Edge e : g.adj(s)) {
			if (!marked[e.other(s)])
				pq.add(e);
		}
	}

	public double weight() {
		return weight;
	}

	public Iterable<Edge> edges() {
		return mst;
	}

}