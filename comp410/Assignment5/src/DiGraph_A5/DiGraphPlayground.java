package DiGraph_A5;

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
//		d.delEdge("f","s");
//				d.addNode(1,"f");
//				d.addNode(3,"s");
//				d.addEdge(0,"f","s", 0, null);
//				d.delEdge("f","s");
//				d.delEdge("f","s");
//				d.addEdge(0,"f","s", 0, null);
//				d.delEdge("f","s");
		d.addNode(1, "f");
		for(int i = 2; i < 10000000; i++) {
			d.addNode(i, i + "o");
			d.addEdge(i, (i - 1) + "o", i + "o", 1, null);
		}
//		d.addNode(3, "s");
//		d.addNode(7, "t");
//		d.addNode(0, "fo");
//		d.addNode(4, "fi");
//		d.addNode(6, "si");
//		d.addEdge(0, "f", "s", 0, null);
//		d.addEdge(1, "f", "si", 0, null);
//		d.addEdge(2, "s", "t", 0, null);
//		d.addEdge(3, "fo", "fi", 0, null);
//		d.addEdge(4, "fi", "si", 0, null);
		System.out.println("numEdges: "+d.numEdges());
		System.out.println("numNodes: "+d.numNodes());
	}
}