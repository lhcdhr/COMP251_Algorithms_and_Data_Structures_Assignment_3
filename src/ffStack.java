public class ffStack {
    /*
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();

		path.add(source);
		LinkedList<Integer> vertexStack = new LinkedList<Integer>();
		vertexStack.add(source);
		LinkedList<Integer> used = new LinkedList<>();
		used.add(source);
		while(vertexStack.size()!=0){
			Integer v = vertexStack.removeFirst();
			Integer w = null;
			for(Edge edge: graph.listOfEdgesSorted()){
				if(validEdge(edge,v,used)){
					if(validPath(used,edge.nodes[1],destination,graph)){
						w=edge.nodes[1];
						break;
					}
				}
			}
			if(w==null){
				break;
			}
			used.add(w);
			path.add(w);
			vertexStack.addFirst(w);
		}
		return path;
	}
	// check whether this edge is qualified to contain the flow
	private static boolean validEdge(Edge toCheck, Integer v, LinkedList<Integer> used) {
		return v.equals(toCheck.nodes[0]) && toCheck.weight > 0 && !used.contains(toCheck.nodes[1]);
	}

	// make sure there is a valid path from this vertex to destination
	private static boolean validPath(LinkedList<Integer> used, Integer source, Integer destination, WGraph graph){
		if(source.equals(destination)){
			return true;
		}
		for(Edge edge: graph.getEdges()) {
			if (validEdge(edge,source,used)){
				LinkedList<Integer> subUsed = new LinkedList<>(used);
				subUsed.add(edge.nodes[0]);
				if(validPath(subUsed, edge.nodes[1], destination,graph)){
					return true;
				}
			}
		}
		return false;
	}
*/

}
