package DiGraph_A5;

import java.util.HashMap;
import java.util.Iterator;


public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	
	HashMap<String, Node> nodes = new HashMap<String, Node>();  
	HashMap<Long, Node> nodeID = new HashMap<Long, Node>();
	HashMap<Long, Node> edgeID = new HashMap<Long, Node>();

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
  }
  
@Override
public boolean addNode(long idNum, String lab) {
	// TODO Auto-generated method stub
	if(nodeID.containsKey(idNum) || idNum < 0 || nodes.containsKey(lab) || lab == null) 
		return false;

	Node addNode = new Node(idNum, lab);
	nodes.put(lab, addNode);
	nodeID.put(idNum, addNode);
	return true;
}

@Override
public boolean addEdge(long idNum, String sLab, String dLab, long weight, String eLab) {
	// TODO Auto-generated method stub
	if(edgeID.containsKey(idNum) || idNum < 0 || !nodes.containsKey(sLab) || !nodes.containsKey(dLab) || nodes.get(sLab).outer.containsKey(nodes.get(dLab)) || nodes.get(dLab).inner.containsKey(nodes.get(sLab)))
		return false;

			Edge addEdge = new Edge(idNum, sLab, dLab, weight);
			nodes.get(sLab).outer.put(nodes.get(dLab), addEdge);
			nodes.get(dLab).inner.put(nodes.get(sLab), addEdge);
			edgeID.put(idNum, null);
			return true;
}


@Override
public boolean delNode(String lab) {
	// TODO Auto-generated method stub
	if(!nodes.containsKey(lab)) return false;

	Node delnode = nodes.get(lab);
	for(Edge delEdge: delnode.inner.values()) delEdge(delEdge.sLab, delEdge.dLab);
	for(Edge delEdge: delnode.outer.values()) delEdge(delEdge.sLab, delEdge.dLab);
	nodes.remove(lab, delnode);
	nodeID.remove(delnode.idNum);
	return true;
}


@Override
public boolean delEdge(String sLab, String dLab) {
	// TODO Auto-generated method stub
	if(!nodes.containsKey(sLab) || !nodes.containsKey(dLab) || !nodes.get(sLab).outer.containsKey(nodes.get(dLab)))
		return false;
	Edge remEdge = nodes.get(sLab).outer.get(nodes.get(dLab));
	edgeID.remove(remEdge.idNum);
	nodes.get(sLab).outer.remove(nodes.get(dLab), remEdge);
	nodes.get(dLab).inner.remove(nodes.get(sLab), remEdge);
	return true;
}


@Override
public long numNodes() {
	// TODO Auto-generated method stub
	 return nodeID.size();
}


@Override
public long numEdges() {
	// TODO Auto-generated method stub
	return edgeID.size();
}
  
  // rest of your code to implement the various operations

@Override
public ShortestPathInfo[] shortestpath(String lab) {
		// TODO Auto-generated method stub
		MinBinHeap pQ = new MinBinHeap();
		ShortestPathInfo[] shortPath = new ShortestPathInfo[(int) numNodes()];
		pQ.insert(new EntryPair(nodes.get(lab), 0)); 
		int i = 0;
		shortPath[i] = new ShortestPathInfo(lab, 0);
		while(pQ.size()!= 0) {
			
			Node v = nodes.get(pQ.getMin().getValue().lab);
			long distance = pQ.getMin().priority;  
			pQ.delMin(); 
			if(v.tested) continue; 
			shortPath[i] = new ShortestPathInfo(v.lab, distance);  
			v.tested = true;   
			Iterator<Edge> adjacent = v.outer.values().iterator();
			i++;
			while(adjacent.hasNext()){ 
				
				Node adj = nodes.get(adjacent.next().dLab); 
				if(!adj.tested){
					if(distance + v.outer.get(adj).weight < adj.length|| adj.length== 0){ 
						adj.length= v.outer.get(adj).weight + distance;
						pQ.insert(new EntryPair(nodes.get(adj.lab), v.outer.get(adj).weight + distance));
					} 
				}
			}	
		} for(Node v: nodes.values()){ //test 4
				if(!v.tested){

					shortPath[i] = new ShortestPathInfo(v.lab, -1); 
					i++;
				}
			}
		return shortPath;	  
	}
}

