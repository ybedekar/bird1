package superhero.exceptionHandler.types;

import superhero.exceptionHandler.handler.SuperHeroServiceException;
import superhero.exceptionHandler.handler.ErrorCode;

public class InvalidRequestException extends SuperHeroServiceException{
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
