package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

@Override
public void insert(String s) {
	// TODO Auto-generated method stub
	if (empty()) {
		root = new BST_Node(s);
	} else {
		root = root.insertNode(s);
	} 
	if (root.justMade) {
		size += 1;
		root.justMade = false;
	}
}

@Override
public void remove(String s) {
	// TODO Auto-generated method stub
	if (root == null || (!contains(s))) {
		return;
	}
	if (root.left == null) {
		root = root.right;
	} else {
		BST_Node rNode = this.root.right;
		if (rNode == null) {
			root = root.left;
		} else {
			root = root.left.findMax();
		}
		if (rNode != null) {
			root.right = rNode;
		}
		if (root.right != null) {
			root.right.par = root;
		}
	}
	if (root != null) {
		root.par = null;
	}
	size -= 1;
}

@Override
public String findMin() {
	// TODO Auto-generated method stub
	if (empty()) {
		return null;
	} else {
		root = root.findMin();
		return root.data;
	}
}

@Override
public String findMax() {
	// TODO Auto-generated method stub
	if (empty()) {
		return null;
	} else {
		root = root.findMax();
		return root.data;
	}
}

@Override
public boolean empty() {
	// TODO Auto-generated method stub
	if (size == 0) {
		return true;
	} else {
		return false;
	}
}

@Override
public boolean contains(String s) {
	// TODO Auto-generated method stub
	if (empty()) {
		return false;
	}
	root = root.containsNode(s);
	return root.data.equals(s);
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return size;
}

@Override
public int height() {
	// TODO Auto-generated method stub
	if (empty()) {
		return 0;
	} else {
		return root.getHeight();
	}
}  

}