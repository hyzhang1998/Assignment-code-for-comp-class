package A5_Dijkstra;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DiGraph implements DiGraphInterface {
	private Map<Long, Long> verticesId = new HashMap<Long, Long>();
	private Map<Long, Long> edgeId = new HashMap<Long, Long>();
	private Map<String, Vertex> vertices = new HashMap<String, Vertex>();

	public DiGraph ( ) { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
	}

	@Override
	public boolean addNode(long idNum, String label) {
		if(vertices.containsKey(label) || idNum < 0) {
			return false;
		}
		else if(verticesId.containsKey(idNum)) {
			return false;
		}
		else {
			vertices.put(label, new Vertex(idNum, label));
			verticesId.put(idNum, idNum);
			return true;
		}
	}

	public Vertex getNode(String label) {
		return vertices.get(label);
	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if(!vertices.containsKey(sLabel) || !vertices.containsKey(dLabel) || edgeId.containsKey(idNum)) {
			return false;
		}
		else {
			Vertex v = vertices.get(sLabel);
			if(v.getOut_edges().containsKey(dLabel)) {
				return false;
			}
			v.addEdge(new Edge(idNum, sLabel, dLabel, weight, eLabel));
			vertices.get(dLabel).addEdge(new Edge(idNum, sLabel, dLabel, weight, eLabel));
			edgeId.put(idNum, idNum);
			return true;
		}
	}

	@Override
	public boolean delNode(String label) {
		if(!vertices.containsKey(label)) {
			return false;
		}
		else {
			for(String s : vertices.get(label).getIn_edges().keySet()) {
				vertices.get(s).delOutEdge(label);
			}
			for(String s : vertices.get(label).getOut_edges().keySet()) {
				vertices.get(s).delInEdge(label);
			}
			verticesId.remove(vertices.get(label).getId());
			vertices.remove(label);
			return true;
		}
	}

	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		if(!vertices.containsKey(sLabel) || !vertices.containsKey(dLabel)) {
			return false;
		}
		else {
			if(vertices.get(sLabel).getOut_edges().containsKey(dLabel)) {
				long id = vertices.get(sLabel).getOutEdgeId(dLabel);
				edgeId.remove(id);
			}
			return vertices.get(sLabel).delOutEdge(dLabel) && vertices.get(dLabel).delInEdge(sLabel);
		}
	}

	@Override
	public long numNodes() {
		return vertices.size();
	}

	@Override
	public long numEdges() {
		return edgeId.size();
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		ShortestPathInfo[] shortestPaths = new ShortestPathInfo[vertices.size()];
		int i = 0;
		Map<String, ShortestPathInfo> sp = new HashMap<String, ShortestPathInfo>();
		for(String s : vertices.keySet()) {
			sp.put(s, new ShortestPathInfo(s, -1));
			i++;
		}
		sp.put(label, new ShortestPathInfo(label, 0));
		PriorityQueue<ShortestPathInfo> pq = new PriorityQueue<ShortestPathInfo>(vertices.size(), (a, b) -> (int)(a.getTotalWeight() - b.getTotalWeight()));
		pq.add(sp.get(label));
		while(!pq.isEmpty()) {
			Vertex v = vertices.get(pq.peek().getDest());
			int d = (int) pq.peek().getTotalWeight();
			pq.remove();
			if(!v.known) {
				v.known = true;
				for(String s : v.getOut_edges().keySet()) {
					if(sp.get(s).getTotalWeight() > d + v.getOut_edges().get(s).getWeight() || sp.get(s).getTotalWeight() == -1) {
						sp.put(s, new ShortestPathInfo(s, d + v.getOut_edges().get(s).getWeight()));
						pq.add(new ShortestPathInfo(s, d + v.getOut_edges().get(s).getWeight()));
					}
				}
			}
		}
		i = 0;
		for(String s : sp.keySet()) {
			shortestPaths[i] = sp.get(s);
			i++;
		}
		return shortestPaths;
	}

}