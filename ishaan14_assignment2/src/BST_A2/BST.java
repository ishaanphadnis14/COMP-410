package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }
  
  public boolean insert(String s) {
	  if (this.contains(s)) {
		  return false;
	  }

	  if (root == null) {
		  root = new BST_Node(s);
		  size++;
		  
		  return true;
		  } else {
			  boolean result = root.insertNode(s);
			  if (result) {
				  size++;
				  return true;
				  }
			  return false;
			  }
	  }

	  public boolean remove(String s) {
	  if (root == null || size == 0) {
	   return false;

	  }

	  if (root.data.equals(s)) {
	   BST_Node holder = new BST_Node("root");
	   holder.left = root;
	   boolean result = holder.removeNode(s, holder);
	   root = holder.left;
	   size--;
	   return result;

	  } else {
	   size--;

	   return root.removeNode(s, null);
	   
	  }
	  }

	  public String findMin() {
	  if (size == 0) {
	   return null;

	  } else {
	   return root.findMin().data;

	  }
	  }

	  public String findMax() {
	  if (size == 0) {
	   return null;

	  } else {
	   return root.findMax().data;

	  }
	  }

	  public boolean empty() {
	  if (size == 0) {
	   return true;
	   
	  } else {
	   return false;

	  }
	  }

	 

	  public boolean contains(String s) {
		  if (size == 0) {
			  return false;
			  
		  } else {
			  return root.containsNode(s);
			  }
		  }

	  public int size() {
		  return size;

	  }

	  public int height() {		
		  if (size == 0) {
			  return -1;
		  }
		  return root.getHeight();
	  }
}


