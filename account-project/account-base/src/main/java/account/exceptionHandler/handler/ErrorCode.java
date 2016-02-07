package account.exceptionHandler.handler;

public enum ErrorCode {

	INPUT_INVALID_PAYLOAD("Invalid request"), 
	RESOURCE_NOT_FOUND("Resource not found"), 
	MISC_SERVER_ERROR("Server error");

	private String description;
	
	ErrorCode(String description) {
        this.description = description;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    
}
