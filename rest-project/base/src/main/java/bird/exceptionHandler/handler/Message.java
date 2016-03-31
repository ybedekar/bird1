package bird.exceptionHandler.handler;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 7952023622474512765L;

	private String message;

	public Message(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}