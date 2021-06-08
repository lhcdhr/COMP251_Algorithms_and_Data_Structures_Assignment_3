import java.lang.reflect.Array;
import java.util.*;
import java.io.File;
//Haochen Liu
//COMP251 A3 Q3
public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE		*/
		LinkedList<Integer> used = new LinkedList<Integer>();
		DFS(path,source,destination,used,graph);
		return path;
	}

	private static boolean DFS(ArrayList<Integer> path,Integer source, Integer destination, LinkedList<Integer> used, WGraph graph){
		path.add(source);
		used.add(source);
		if(source.equals(destination)){
			return true;
		}
		for(Edge edge : graph.listOfEdgesSorted()) {
			if (validEdge(edge,source,used)){
				used.add(edge.nodes[0]);
				if(DFS(path,edge.nodes[1],destination,used,graph)){
					return true;
				}
			}
		}
		path.remove(path.size()-1);
		return false;
	}

	// check whether this edge is qualified to contain the flow
	private static boolean validEdge(Edge toCheck, Integer v, LinkedList<Integer> used) {
		return v.equals(toCheck.nodes[0]) && toCheck.weight > 0 && !used.contains(toCheck.nodes[1]);
	}

	private static int pathFlow(ArrayList<Integer> path, int min, WGraph rGraph){
		for (int i = 2; i < path.size(); i++) {
			Integer v = path.get(i-1);
			Integer w = path.get(i);
			Edge edge = rGraph.getEdge(v, w);
			int weight = edge.weight;
			if (weight < min) {
				min = weight;
			}
		}
		return min;
	}

	private static void augmentingPath(ArrayList<Integer> path, int min, WGraph graph){
		for (int i = 1; i < path.size(); i++) {
			Integer v = path.get(i - 1);
			Integer w = path.get(i);
			if (graph.getEdge(v, w) != null) {
				Edge fwdEdge = graph.getEdge(v, w);
				fwdEdge.weight += min;
			} else {
				graph.getEdge(w, v).weight -= min;
			}
		}
	}

	private static void residual(WGraph graph, WGraph rGraph, WGraph oGraph){
		int size = graph.getEdges().size();
		for (int i=0 ; i<size ; i++) {
			Edge edge = graph.getEdges().get(i);
			Edge flowEdge = graph.getEdge(edge.nodes[0], edge.nodes[1]);
			Edge capacityEdge = oGraph.getEdge(edge.nodes[0], edge.nodes[1]);
			int flow = flowEdge.weight;
			int capacity = capacityEdge.weight;
			if (flow <= capacity) {
				int fwdFlow = capacity - flow;
				rGraph.setEdge(edge.nodes[0], edge.nodes[1], fwdFlow);
			}
			if (flow > 0) {
				if (rGraph.getEdge(edge.nodes[1], edge.nodes[0]) == null) {
					rGraph.addEdge(new Edge(edge.nodes[1], edge.nodes[0], flow));
				} else {
					rGraph.setEdge(edge.nodes[1], edge.nodes[0], flow);
				}
			}
		}
	}

	public static String fordfulkerson(WGraph graph){
		String answer="";
		int maxFlow = 0;
		/* YOUR CODE GOES HERE		*/
		WGraph residualGraph = new WGraph(graph);
		WGraph originalGraph = new WGraph(graph);
		ArrayList<Edge> edges = graph.getEdges();
		for(Edge edge: edges){
			edge.weight=0;
		}
		Integer source = graph.getSource();
		Integer destination = graph.getDestination();
		ArrayList<Integer> path = pathDFS(source,destination,residualGraph);
		while(path.size()>=2) {
			int min = residualGraph.getEdge(path.get(0),path.get(1)).weight;
			//find the flow in this path
			min = pathFlow(path,min,residualGraph);
			maxFlow += min;
			//augmenting path
			augmentingPath(path,min,graph);
			//update residual graph
			residual(graph,residualGraph,originalGraph);
			//find a new path
			path = pathDFS(source, destination, residualGraph);
		}
		answer += maxFlow + "\n" + graph.toString();
		return answer;
	}

	public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
		//System.out.println(fordfulkerson(g));
	}
}