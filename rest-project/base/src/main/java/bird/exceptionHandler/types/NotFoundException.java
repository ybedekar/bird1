package bird.exceptionHandler.types;

import bird.exceptionHandler.handler.ErrorCode;
import bird.exceptionHandler.handler.BirdServiceException;

public class NotFoundException extends BirdServiceException{
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
