package superhero.exceptionHandler.types;

import superhero.exceptionHandler.handler.SuperHeroServiceException;
import superhero.exceptionHandler.handler.ErrorCode;

public class ServerException extends SuperHeroServiceException{
		private static final long serialVersionUID = 1L;
		private Object[] args;

		public ServerException(Object... args) 
		{
			super(args);
		}

		public ServerException(String message, Object... args) 
		{
			super(message,args);

		}
		
		public Object[] getArgs() 
		{
			return args;
		}

	
}
