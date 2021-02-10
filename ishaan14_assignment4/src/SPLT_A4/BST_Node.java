package SPLT_A4;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  BST_Node par; //parent...not necessarily required, but can be useful in splay tree
  boolean justMade; //could be helpful if you change some of the return types on your BST_Node insert.
            //I personally use it to indicate to my SPLT insert whether or not we increment size.
  
  BST_Node(String data){ 
    this.data=data;
    this.justMade=true;
  }
  
  BST_Node(String data, BST_Node left,BST_Node right,BST_Node par){ //feel free to modify this constructor to suit your needs
    this.data=data;
    this.left=left;
    this.right=right;
    this.par=par;
    this.justMade=true;
  }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is (meaning also make sure they do in fact return data,left,right respectively)

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- Some example methods that could be helpful ------------------------------------------
  //
  // add the meat of correct implementation logic to them if you wish

  // you MAY change the signatures if you wish...names too (we will not grade on delegation for this assignment)
  // make them take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations

  
  public BST_Node containsNode(String s){ 
	  if (data.equals(s)) {
		  splay(this);
		  return this;
	  }
	  if (data.compareTo(s) > 0) {
		  if (left != null) {
			  return left.containsNode(s);
		  } else {
			  splay(this);
			  return this;
		  }
	  }
	  if (data.compareTo(s) < 0) {
		  if (right != null) {
		  return right.containsNode(s);
	  } else {
		  splay (this);
		  return this;
		  }
	  }
  return null;
  }//note: I personally find it easiest to make this return a Node,(that being the node splayed to root), you are however free to do what you wish.
  
  public BST_Node insertNode(String s){
	  if (s.compareTo(this.data) < 0) {
		  if (this.left != null) {
			  return this.left.insertNode(s);
		  }
		  this.left = new BST_Node(s, null, null, this);
		  BST_Node temp = this.left;
		  splay(this.left);
		  return temp;
	  }
	  if (s.compareTo(this.data) > 0) {
		  if (this.right != null) {
			  return this.right.insertNode(s);
		  }
		  this.right = new BST_Node(s, null, null, this);
		  BST_Node temp = this.right;
		  splay(this.right);
		  return temp;
	  }
	  splay (this);
	  return this;
	  }
	  
 //Really same logic as above note

	/*
  public boolean removeNode(String s){
	  
  } //I personal do not use the removeNode internal method in my impl since it is rather easily done in SPLT, feel free to try to delegate this out, however we do not "remove" like we do in BST
  */
  public BST_Node findMin(){ 
	  if (left != null) {
		  return left.findMin();
	  }
	  splay (this);
	  return this;
  }
  public BST_Node findMax(){
	  if (right != null) {
		  return right.findMax();
	  }
	  splay (this);
	  return this;
  }
  public int getHeight(){
	  int lt = 0;
	  int rt = 0;
	  if (left != null)
		  lt += left.getHeight() + 1;
	  if (right != null) 
		  rt += right.getHeight() + 1;
	  if (lt > rt) {
		  return lt;
	  } else {
		  return rt;
	  }
	  
  }

  private void splay(BST_Node toSplay) {
	  while (toSplay.par != null) {
			BST_Node curr = toSplay.par;
			BST_Node prev = curr.par;
			if (prev == null) { 			
				if (toSplay == curr.left) { 		
					rotateRight(toSplay);
				} else { 						
					rotateLeft(toSplay);
				}
			} else if (toSplay == curr.left) { 	
				if (curr == prev.left) {		
					rotateRight(toSplay);
					rotateRight(toSplay);
				} else { 						
					rotateRight(toSplay);
					rotateLeft(toSplay);
				}
			} else if (toSplay == curr.right) {
				if (curr == prev.right) { 
					rotateLeft(toSplay);
					rotateLeft(toSplay);
				} else { 						
					rotateLeft(toSplay);
					rotateRight(toSplay);
				}
			}
		}
	}
  //you could have this return or take in whatever you want..so long as it will do the job internally. As a caller of SPLT functions, I should really have no idea if you are "splaying or not"
                        //I of course, will be checking with tests and by eye to make sure you are indeed splaying
                        //Pro tip: Making individual methods for rotateLeft and rotateRight might be a good idea!
  

  // --- end example methods --------------------------------------

  
  

  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  private void rotateRight(BST_Node n) {
		BST_Node temp = n.par;
		if (temp.par != null) {
			if (temp != temp.par.left) {
				temp.par.right = n;
			} else {
				temp.par.left = n;
			}
		}
		if (n.right != null) {
			n.right.par = temp;
		}
		n.par = temp.par;
		temp.par = n;
		temp.left = n.right;
		n.right = temp;
		}

	private void rotateLeft(BST_Node n) {
		BST_Node temp = n.par;
		if (temp.par != null) {
			if (temp != temp.par.left) {
				temp.par.right = n;
			} else {
				temp.par.left = n;
			}
		}
		if (n.left != null) {
			n.left.par = temp;
		}
		n.par = temp.par;
		temp.par = n;
		temp.right = n.left;
		n.left = temp;
		} 
  
}