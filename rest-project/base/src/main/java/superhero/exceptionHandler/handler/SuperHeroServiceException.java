package superhero.exceptionHandler.handler;

public class SuperHeroServiceException extends RuntimeException {

		private static final long serialVersionUID = 32L;
		
		private Object[] args;

		public SuperHeroServiceException(Object... args) 
		{
			this.args = args;
		}

		public SuperHeroServiceException(String message, Object... args) 
		{
			super(message);
			this.args = args;
		}

		

		public Object[] getArgs() 
		{
			return args;
		}

}
