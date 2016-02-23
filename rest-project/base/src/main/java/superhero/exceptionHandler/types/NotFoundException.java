package superhero.exceptionHandler.types;

import superhero.exceptionHandler.handler.SuperHeroServiceException;
import superhero.exceptionHandler.handler.ErrorCode;

public class NotFoundException extends SuperHeroServiceException{
	private static final long serialVersionUID = 2L;
	private ErrorCode errorCode;
	private Object[] args;

	public NotFoundException(Object... args) 
	{
		super(args);
	}

	public NotFoundException(String message, Object... args) 
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
