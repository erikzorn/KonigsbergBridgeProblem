/*
 * Erik Zorn - CSCE 146 - HW08 - 8Mar2016
 */

import java.util.*;

public class KonigsbergBridge {

	private class Vertex // creates vertex properties
	{
		String name;
		ArrayList<Edge> neighbors;

		public Vertex(String aName) {
			this.name = aName; // assigns name
			this.neighbors = new ArrayList<Edge>(); // arrayList of neighbors
		}
	}

	private class Edge {
		Vertex Vert1; // linked vertex
		Vertex Vert2;
		double weight; // distance to nextVert // distance of edge

		public Edge(Vertex aV1, Vertex aV2, double aWeight) {
			Vert1 = aV1;
			Vert2 = aV2;
			weight = aWeight;
		}
	}

	private int paths;
	private Vertex origin;
	private ArrayList<Vertex> verticies; // all verticies
	private ArrayList<Vertex> markedVerts; // used for iterating through
	private ArrayList<Vertex> visitedVerts; // ^^
	private ArrayList<Edge> edges;
	private ArrayList<Edge> markedEdges;


	public KonigsbergBridge() { // initialize the graph
		origin = null;
		verticies = new ArrayList<Vertex>();
		markedVerts = new ArrayList<Vertex>();
		visitedVerts = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		markedEdges = new ArrayList<Edge>();
		// maxLength = aLength;
	}

	public void addVertex(String aName) {
		if (vertexIsContained(aName)) // dont add duplicate
			return;
		Vertex v = new Vertex(aName);
		verticies.add(v);
		if (origin == null) { // if empty, make vertex origin
			origin = v;
		}
	}

	public boolean vertexIsContained(String aName) { // method checks to see if
														// the vertex is already
														// contained in the
														// graph
		for (Vertex vert : verticies) {
			if (vert.name.equals(aName))
				return true;
		}
		return false;
	}

	public void addEdge(String fromVert, String toVert, double weight) {
		Vertex v1 = getVertex(fromVert); // vert is linked by fromVert
		Vertex v2 = getVertex(toVert); // next Vert
		if (v1 == null || v2 == null)
			return;
		Edge edge = new Edge(v1, v2, weight);
		edges.add(edge);
		v1.neighbors.add(edge);
		v2.neighbors.add(edge);
	}

	public Vertex getVertex(String aName) { // return vertex by name identifier
		for (Vertex vert : verticies) {
			if (vert.name.equals(aName))
				return vert;
		}
		return null;
	}

	public void sortEdges() {
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int j = 0; j < edges.size() - 1; j++) {
				if (edges.get(j).weight > edges.get(j + 1).weight) {
					Edge temp = edges.get(j);
					edges.set(j, edges.get(j + 1));
					edges.set(j + 1, temp);
					flag = true;
				}
			}
		}
	}
	public void fromEachIsland() {
		for(Vertex vert : verticies) {
			System.out.println("\n*************** From Island " + vert.name + " ***************");
			markedEdges.clear();
			overEachBridge(vert);
			System.out.println("\n\n");
		}
			System.out.println("Path Tried: " + this.paths + "\nDONE: NOT POSSIBLE TO TRAVERSE ALL BRIDGES ONLY ONCE" );
	}
	
	public void overEachBridge(Vertex vert) {
		for (Edge edge : vert.neighbors) {
			if (!markedEdges.contains(edge)) {	// if edge has not yet been traversed
				markedEdges.add(edge);
				if(edge.Vert1.equals(vert))  {
					
					overEachBridge(edge.Vert2);	// 
				}
				else {
					overEachBridge(edge.Vert1);
				}
				System.out.println();

			}	
		}
		
		int size = markedEdges.size();	// stores bridges traveled on this path

		System.out.println(size + " bridge(s) traveled:");
		paths++;
		for(Edge edge : markedEdges) {	// prints path tried
			System.out.print("Bridge: "+ edge.weight + " ----> ");	// weight in this case refers to bridge number
		}	
		if (size==7) {		// if 7 bridges have been traveled, then a path has been found
			System.out.println("Path Found!!!");
			System.exit(0);
		}
		
		System.out.println();
		
		if (size==0)	// if size is 0, then all paths from this island have been tried
			System.out.println("***All paths starting from this island have been traveled***");
		
		if(markedEdges.size()>0)	// go back an island and try again on next neighbor
			markedEdges.remove(markedEdges.size()-1);
	}
	public int getPaths() {
		return paths;
	}
	
}










