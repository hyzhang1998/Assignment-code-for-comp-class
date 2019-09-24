package A5_Dijkstra;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
	public boolean known = false;
	private String label;
	private Map<String, Edge> in_edges = new HashMap<String, Edge>();
	private Map<String, Edge> out_edges = new HashMap<String, Edge>();
	private long idNum;
	
	public Vertex(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Map<String, Edge> getIn_edges(){
		return in_edges;
	}
	
	public Map<String, Edge> getOut_edges(){
		return out_edges;
	}
	
	public int In_degree() {
		return in_edges.size();
	}
	
	public long getId() {
		return idNum;
	}
	
	public void addEdge(Edge edge) {
		if(edge.getSource() == label) {
			out_edges.put(edge.getDestination(), edge);
		}
		else {
			in_edges.put(edge.getSource(), edge);
		}
	}
	
	public boolean delOutEdge(String dlabel) {
		if(out_edges.containsKey(dlabel)) {
			out_edges.remove(dlabel);
			return true;
		}
		else {
			return false;
		}
	}
	
	public long getOutEdgeId(String dlabel) {
		return out_edges.get(dlabel).getId();
	}
	
	public boolean delInEdge(String slabel) {
		if(in_edges.containsKey(slabel)) {
			in_edges.remove(slabel);
			return true;
		}
		else {
			return false;
		}
	}
	
}
