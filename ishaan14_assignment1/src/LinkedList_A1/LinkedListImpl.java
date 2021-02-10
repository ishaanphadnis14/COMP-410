/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node sentinel; //this will be the entry point to your linked list (the head)
  
  private int length;
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel=new Node(0); //Note that the root's data is not a true part of your data set!
	
	length = 0;
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
  
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
public boolean insert(double elt, int index) {
	// TODO Auto-generated method stub
	if (index > length || index < 0) {
		return false;
	}

	if (index == 0 && length == 0) {
		Node insert = new Node(elt);
		sentinel.prev = insert;
		sentinel.next = insert;
		insert.prev = sentinel;
		insert.next = sentinel;
		length++;
		
		return true;
	}
	
	int i = -1;
	Node current = sentinel;
	
	while (i < index - 1) {
		current = current.next;
		i++;
	}
	
	Node insert = new Node(elt);
	Node nextNode = current.next;

	current.next = insert;
	insert.prev = current;
	insert.next = nextNode;
	nextNode.prev = insert;

	length++;
	return true;
}

@Override
public boolean remove(int index) {
	// TODO Auto-generated method stub
	if (index > length - 1 || index < 0) {
		return false;
	}

	Node current = sentinel.next;
	
	if (index == 0 && length == 1) {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		
	} else if (index == 0) {
		sentinel.next = current.next;
		current.next.prev = sentinel;
		
	} else {
	
	int i = 0;
	while (current != sentinel) {
		if (i < index) {
				current = current.next;
				i++;
				
		} else {	
			break;	
		}
	}
	current.prev.next = current.next;
	current.next.prev = current.prev;

	}
	
	if (index == length) {
		current.next = sentinel;
		sentinel.prev = current;
		
	}
	
	length--;

	return true;
}

@Override
public double get(int index) {
	// TODO Auto-generated method stub
	if (index > length - 1 || index < 0) {
		return Double.NaN;
	}

	Node current = sentinel.next;	
	while (index != 0) {
		current = current.next;
		index--;
	}

	return current.data;
}

@Override
public int size() {
	// TODO Auto-generated method stub
	return length;
}

@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	return length == 0;
}

@Override
public void clear() {
	// TODO Auto-generated method stub
	while (length != 0) {
		
		remove(0);
		
		}
	}	
}
