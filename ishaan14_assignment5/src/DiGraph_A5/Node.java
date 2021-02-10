package DiGraph_A5;

import java.util.HashMap;

public class Node {
	long idNum;
	String lab;
	HashMap<Node, Edge> inner;
	HashMap<Node, Edge> outer;
	long length;
	boolean tested;
	
  
  
  public Node(long idNum, String label) {
    this.idNum = idNum;
    this.lab = label;
    this.inner = new HashMap<Node, Edge>();
    this.outer = new HashMap<Node, Edge>();
    this.tested = false;
  }
  
  public String getLabel() {
    return this.lab;
  }
  
  public long getID() {
    return this.idNum;
  }
  
  public String toString()  {
	  return "Node: id: "+this.idNum+"  label: "+this.lab;
  }

}
