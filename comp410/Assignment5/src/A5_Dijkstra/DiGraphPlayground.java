package A5_Dijkstra;

public class DiGraphPlayground {

	public static void main (String[] args) {

		// thorough testing is your responsibility
		//
		// you may wish to create methods like 
		//    -- print
		//    -- sort
		//    -- random fill
		//    -- etc.
		// in order to convince yourself your code is producing
		// the correct behavior
		exTest();
	}

	public static void exTest(){
		DiGraph d = new DiGraph();
		d.addNode(1, "A");
		d.addNode(2, "B");
		d.addNode(3, "C");
		d.addNode(4, "D");
		d.addNode(5, "E");
		d.addNode(6, "F");
		d.addNode(7, "G");
		d.addEdge(0, "A", "B", 4, null);
		d.addEdge(1, "A", "C", 2, null);
		d.addEdge(2, "A", "G", 3, null);
		d.addEdge(3, "B", "D", 3, null);
		d.addEdge(4, "B", "F", 4, null);
		d.addEdge(5, "B", "G", 2, null);
		d.addEdge(6, "C", "B", 1, null);
		d.addEdge(7, "C", "G", 1, null);
		d.addEdge(8, "D", "E", 2, null);
		d.addEdge(9, "D", "F", 1, null);
		d.addEdge(10, "F", "E", 2, null);
		d.addEdge(11, "G", "D", 1, null);
		d.addEdge(12, "G", "F", 2, null);
		System.out.println("numEdges: "+d.numEdges());
		System.out.println("numNodes: "+d.numNodes());
		ShortestPathInfo[] s = d.shortestPath("A");
		for(int i = 0; i < 7; i++) {
			System.out.println(s[i].toString());
		}
	}
}