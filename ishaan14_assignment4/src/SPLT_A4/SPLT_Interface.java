/**
 * COMP 410
 *
 * Make your class and its methods public!
 *
 * Your SPLT implementation will implement this BST interface.
 *
*/

package SPLT_A4;

/*
  Interface: The SPLT will provide this collection of operations:

  insert:
    in: a string (the element to be stored into the tree)
    return: void, if you need to update size when delegating, consider using a boolean as I mention in BST_Node
    effect: if the string is already in the tree, then there is no change to
              the tree state (save for splaying), and return
            if the string is not already in the tree, then a new tree cell/node
              is created, the string put into it as data, the new node is linked
              into the tree at the proper place; size is incremented by 1,
              and return
  remove:
    in: a string (the element to be taken out of the tree)
    return: void
    effect: see description on "when to splay"
    
  contains:
    in: a string (the element to be searched for)
    return: boolean, return true if the string being looked for is in the tree;
            return false otherwise
            this means return false if tree size is 0
    effect: no change to the state of the tree (save for splaying found node or what would be its parent)

  findMin:
    in: none
    return: string, the element value from the tree that is smallest
    effect: no tree state change (save for splaying)
    error: is tree size is 0, return null


  findMax:
    in: none
    return: string, the element value from the tree that is largest
    effect: no tree state change (save for splaying)
    error: is tree size is 0, return null

  size:
    in: nothing
    return: number of elements stored in the tree
    effect: no change to tree state

  empty:
    in: nothing
    return: boolean, true if the tree has no elements in it, true if size is 0
            return false otherwise
    effect: no change to tree state

  height:
    in: none
    return: integer, the length of the longest path in the tree from root to a leaf
    effect: no change in tree state
    error: return -1 is tree is empty (size is 0, root is null)

  getRoot:
    in: none
    return: a tree cell/node, the one that is the root of the entire tree
            means return a null if the tree is empty
    effect: no change to tree state

*/

// ADT operations

public interface SPLT_Interface {
  public void insert(String s);
  public void remove(String s);
  public String findMin();
  public String findMax();
  public boolean empty();
  public boolean contains(String s);
  public int size();
  public int height();
  public BST_Node getRoot();
}


package DiGraph_A5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
  // and the topo sort operation
	HashMap<String, Node> NodeMap = new HashMap<String, Node>();  //key:label, value: Node
	HashSet<Long> NodeId = new HashSet<Long>();//store all nodes' id
	HashSet<Long> EdgeId = new HashSet<Long>();//store all edges' id

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
  }
  
  // rest of your code to implement the various operations
  
  public boolean addNode(long idNum, String label)
  {
	  if(idNum<0 || label==null)
		  return false;
	  else if(NodeMap.containsKey(label)==false && NodeId.contains(idNum)==false)
	  {
		  Node newNode = new Node(idNum,label);
		  NodeMap.put(label, newNode);
		  NodeId.add(idNum);
		  return true;
	  }
	  else
		  return false;
		 		  
  }
  
  public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel)
  {
	  if(idNum<0 || EdgeId.contains(idNum)==true)
		  return false;
	  else if(NodeMap.containsKey(sLabel)==false || NodeMap.containsKey(dLabel)==false)
		  return false;
	  else 
	  {
		  Node sourceNode = NodeMap.get(sLabel);
		  Node destiNode  = NodeMap.get(dLabel);
		  if(sourceNode.Out_Edge.containsKey(dLabel) && destiNode.In_Edge.containsKey(sLabel))
			  return false;
		  else //add edge
		  {
			  Edge newEdge = new Edge(idNum,sLabel,dLabel,weight,eLabel);
			  EdgeId.add(idNum);
			  sourceNode.Out_Edge.put(dLabel, newEdge);
			  destiNode.In_Edge.put(sLabel, newEdge);
			  destiNode.in_degree+=1;		  
			  return true;
		  }
		 
	  }
  }
  
//  public boolean delNode(String label)
//  {
//	  if(NodeMap.containsKey(label)==false)
//		  return false;
//	  else
//	  {
//		  Node remNode = NodeMap.get(label);
//		  NodeMap.remove(label,remNode);
//		  NodeId.remove(remNode.idNum);
//		  for(Node node:NodeMap.values())
//		  {
//			  if(node.In_Edge.containsKey(label))
//			  {
//				  EdgeId.remove(node.In_Edge.get(label).idNum);
//				  node.In_Edge.remove(label);
//				  node.in_degree-=1;
//			  }
//			  if(node.Out_Edge.containsKey(label))
//			  {
//				  EdgeId.remove(node.Out_Edge.get(label).idNum);
//				  node.Out_Edge.remove(label);	  
//			  }
//	
//		  }
//		  return true;  
//	  }	 
//  }

public boolean delNode(String label)
{
	  if(NodeMap.containsKey(label)==false)
		  return false;
	  else
	  {
		  Node remNode = NodeMap.get(label);
		
		  Iterator<String> InEdge_sL = remNode.In_Edge.keySet().iterator();
		  Iterator<String> OutEdge_dL = remNode.Out_Edge.keySet().iterator();
		  
		  while(InEdge_sL.hasNext())
			  delEdge(InEdge_sL.next(),remNode.Label);
		  while(OutEdge_dL.hasNext()) 			
			  delEdge(remNode.Label,OutEdge_dL.next());
		  
		  NodeMap.remove(label,remNode);
		  NodeId.remove(remNode.idNum);
		  return true;
	  }
}
  
  
  public boolean delEdge(String sLabel, String dLabel)
  {
	  if(NodeMap.containsKey(sLabel)==false || NodeMap.containsKey(dLabel)==false)
		  return false;
	  else 
	  {
		  Node sourceNode = NodeMap.get(sLabel);
		  Node destiNode = NodeMap.get(dLabel);
		  if(sourceNode.Out_Edge.containsKey(dLabel))
		  {
			  Edge remEdge = NodeMap.get(sLabel).Out_Edge.get(dLabel);
			  sourceNode.Out_Edge.remove(dLabel,remEdge);
		      destiNode.In_Edge.remove(sLabel,remEdge);
		      destiNode.in_degree-=1;  
			  EdgeId.remove(remEdge.idNum);
			  return true;
		  }
		  return false;
	  }
  }
  
  public long numNodes()
  {
    return NodeId.size();
  }
  
  public long numEdges()
  {
    return EdgeId.size();
  }

//  public String[] topoSort()
//  {
//	  int size = NodeId.size();
//	  String[] topoSort = new String[size];
//	  int i = 0;
//	  int k = 0;
//	  while(NodeMap.isEmpty()!=true)
//	  {	 
//		  for(Node node:NodeMap.values())
//		  {
//			  if(node.in_degree==0)
//			  {
//				  topoSort[i]=node.Label;
//				  delNode(node.Label);
//				  i++;
//				  break;
//			  }			 
//		  }  
//		  if(topoSort[k]==null)
//			  return null;
//		  k++;
//	  }
//		  return topoSort;	
//  }
  
   public String[] topoSort()
   {
	   Queue<Node> Zero_In_Node=new LinkedList<Node>();
	   if(NodeMap.isEmpty())
		   return null;

	   ArrayList<String> topoString = new ArrayList<String>();
	   for(Node node:NodeMap.values())
	   {
		   if(node.in_degree==0 )
			   Zero_In_Node.add(node);
	   }
	  
	   while(Zero_In_Node.isEmpty()==false)
	   {
		   Node deqNode = Zero_In_Node.poll();		   
		   topoString.add(deqNode.Label);
		   Iterator<Edge> OutEdge = deqNode.Out_Edge.values().iterator();
			while(OutEdge.hasNext()) 
			{			
				Node destiNode = NodeMap.get(OutEdge.next().destiL);
				destiNode.in_degree-=1;
				if(destiNode.in_degree ==0 ) {
					Zero_In_Node.add(destiNode);
				}
			}	   
	   }
	
	   if(NodeMap.size()==topoString.size())
		   return topoString.toArray( new String[topoString.size()]);
	   else
		   return null; 
   }
  
  
}