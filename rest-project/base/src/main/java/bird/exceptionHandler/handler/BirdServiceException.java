package bird.exceptionHandler.handler;

public class BirdServiceException extends RuntimeException {

	private static final long serialVersionUID = 32L;

	private Object[] args;

	public BirdServiceException(Object... args) 
	{
		this.args = args;
	}

	public BirdServiceException(String message, Object... args) 
	{
		super(message);
		this.args = args;
	}



	public Object[] getArgs() 
	{
		return args;
	}

}
