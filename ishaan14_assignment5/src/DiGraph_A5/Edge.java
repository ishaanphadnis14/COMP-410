package DiGraph_A5;

public class Edge {
	
	
	long idNum;
	String sLab;
	String dLab;
	long weight;
	
	public Edge(long idNum, String sLabel, String dLabel, long weight) {
		
	    this.idNum = idNum;
	    this.sLab = sLabel;
	    this.dLab = dLabel;
	    this.weight = weight;
	}
	
	public String getsourceLab() {
	    return this.sLab;
	  }
	  
	  public void setsourceLab(String sLab) {
	    this.sLab = sLab;
	  }
	  
	  public String getdestinateLab() {
	    return this.dLab;
	  }
	  
	  public void setdestinateLab(String dLab) {
	    this.dLab = dLab;
	  }
	  
	  public long getEID() {
	    return this.idNum;
	  }
	  
	  public void setEId(long idNum) {
	    this.idNum = idNum;
	  }
	  
	  public long getWeight() {
	    return this.weight;
	  }
	  
	  public void setWeight(long weight) {
	    this.weight = weight;
	  }
	
}
