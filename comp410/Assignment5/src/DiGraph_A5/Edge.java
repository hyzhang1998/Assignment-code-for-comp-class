package DiGraph_A5;

public class Edge {

	private long idNum;
	private String sLabel, dLabel;
	private long weight = 1;
	private String eLabel;
	
	public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.weight = weight;
		this.eLabel = eLabel;
	}
	
	public String getSource() {
		return sLabel;
	}
	
	public String getDestination() {
		return dLabel;
	}
	
	public long getWeight() {
		return weight;
	}
	
	public String getLabel() {
		return eLabel;
	}
	
	public long getId() {
		return idNum;
	}
}
