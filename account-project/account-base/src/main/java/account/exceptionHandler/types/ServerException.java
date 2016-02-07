package account.exceptionHandler.types;

import account.exceptionHandler.handler.BankAccountServiceException;
import account.exceptionHandler.handler.ErrorCode;

public class ServerException extends BankAccountServiceException{
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
