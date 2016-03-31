package bird.exceptionHandler.types;

import bird.exceptionHandler.handler.BirdServiceException;
import bird.exceptionHandler.handler.ErrorCode;

public class ServerException extends BirdServiceException {
	private static final long serialVersionUID = 3L;
	private ErrorCode errorCode;
	private Object[] args;

	public ServerException(Object... args) 
	{
		super(args);
	}

	public ServerException(String message, Object... args) 
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
