package DiGraph_A5;

public class MinBinHeap implements Heap_Interface {
	  private EntryPair[] array; //load this array
	  private int size;
	  private static final int arraySize = 10000; //Everything in the array will initially 
	                                              //be null. This is ok! Just build out 
	                                              //from array[1]

	  public MinBinHeap() {
	    this.array = new EntryPair[arraySize];
	    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
	                                             //of child/parent computations...
	                                             //the book/animation page both do this.
	  }
	    
	  //Please do not remove or modify this method! Used to test your entire Heap.
	  @Override
	  public EntryPair[] getHeap() { 
	    return this.array;
	  }
	  
	  //Helper method
	  public void bubbleHeapHelper (int i) {
		  
		  try {
			  int gap = i;
			  EntryPair tempo = array[gap];
			  int sub = gap * 2;
			  
			  for (; gap * 2 <= this.size; gap = sub) {
				  
				  sub = gap *2;
				  
				  if (sub != this.size && array[sub].getPriority() > array[sub + 1].getPriority()) {
					  sub++;
				  }
				  
				  if (tempo.getPriority() > array[sub].getPriority()) {
					  array[gap] = array[sub];
				  } else {
					  break;
				  }
			  }
			  array[gap] = tempo;
		  }
		  catch (Exception e) {
		  }
	  }
	  
	  @Override
	  public void insert (EntryPair enter) {
		  
		  try {
			  int gap = ++size;
			  while(enter.priority < array[gap/2].priority){
				  array[gap] = array[gap/2];
				  gap /= 2;
				  }
			  array[gap] = enter;
			  }
		  
		  catch (Exception e) {
			  size--;
			  }
		  }
	  
	  @Override
	  public void delMin() {
		  try {
			  if (this.size == 0) {
				  return;
			  } else {
				  array[1] = array[size--];
				  bubbleHeapHelper(1);
			  }
		  } 
		  catch (Exception e) {
			  
		  }
		
	  }
	  
	  @Override
	  public EntryPair getMin() {
		  if (this.size == 0) {
			  return null;
		  }
		  
		  return array [1];
	  }
	  
	  @Override
	  public int size() {
		  return size;
	  }
	  
	  @Override
	  public void build (EntryPair[] entrances) {
		  if (entrances[1] == null) {
			  return;
		  }
		  
		  try {
			  this.size = entrances.length;
			  
			  for (int i = 0; i < this.size; i++) {
				  array [i+1] = entrances [i];
			  }
			  
			  for (int i = this.size; i >= 1; i--) {
				  bubbleHeapHelper(i);
			  }
		  } catch (Exception e) {
			  }
	  }
	  
	  
	  }

