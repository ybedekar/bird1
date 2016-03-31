package bird.exceptionHandler.types;

import bird.exceptionHandler.handler.ErrorCode;
import bird.exceptionHandler.handler.BirdServiceException;

public class InvalidRequestException extends BirdServiceException{
	private static final long serialVersionUID = 5L;
	private ErrorCode errorCode;
	private Object[] args;

	public InvalidRequestException(Object... args) 
	{
		super(args);
	}

	public InvalidRequestException(String message, Object... args) 
	{
		super(message,args);
	}

	public ErrorCode getErrorCode() 
	{
		return errorCode;
	}

	public Object[] getArgs() 
	{
		return args;
	}



}
