package DiGraph_A5;

public class EntryPair {
	
	public Node value;
	public long priority;
	
	public EntryPair(Node aValue, long length) {
	    value = aValue;
	    priority = length;
	}
	public Node getValue() { 
		  return value; 
		  }
	public long getPriority() { 
		  return priority; 
		  }
	  }