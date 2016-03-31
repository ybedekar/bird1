package bird.response;

import java.io.Serializable;
import java.util.List;

public class BirdsResponse implements Serializable{
	static final long serialVersionUID = 12333L;
	
	private List<String> birdIds;
	
	public BirdsResponse(){
		
	}
	
	public BirdsResponse(List<String> birdIds){
		this.birdIds = birdIds;
	}

	public List<String> getBirdIds() {
		return birdIds;
	}

	public void setBirdIds(List<String> birdIds) {
		this.birdIds = birdIds;
	}
	
	
}
