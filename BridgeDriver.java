/*
 * Erik Zorn - CSCE146 - HW08 - 8Apr2016
 * 
 * 
 * 	This graph models the Konigsberd bridge problem
 * 	Each island is created as a vertex in the graph with a letter (A,B,C, or D) as its name
 * 				A: Top Island
 * 				B: Middle-Left Island
 * 				C: Middle-Right Island
 * 				D: Bottom Island
 * 	Each bridge is created as an Edge in the graph with a weight corresponding to its number.
 * 
 */
public class BridgeDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KonigsbergBridge g = new KonigsbergBridge();
		System.out.println("Creating islands and bridges");
		g.addVertex("A");		// Creating islands
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		
		g.addEdge("A", "B", 1);	// Creating bridges
		g.addEdge("A", "B", 2);
		g.addEdge("B", "C", 3);
		g.addEdge("A", "C", 4);
		g.addEdge("B", "D", 5);
		g.addEdge("B", "D", 6);
		g.addEdge("C", "D", 7);
		
		System.out.println("Searching for a path to travel all seven bridges only once");
		g.fromEachIsland();		// Finding paths 
		
	}

}
