package account.exceptionHandler.handler;

public class BankAccountServiceException extends RuntimeException {

		private static final long serialVersionUID = 32L;
		
		private Object[] args;

		public BankAccountServiceException(Object... args) 
		{
			this.args = args;
		}

		public BankAccountServiceException(String message, Object... args) 
		{
			super(message);
			this.args = args;
		}

		

		public Object[] getArgs() 
		{
			return args;
		}

}
