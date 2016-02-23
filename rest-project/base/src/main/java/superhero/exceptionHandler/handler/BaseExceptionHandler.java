package superhero.exceptionHandler.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import superhero.exceptionHandler.types.InvalidRequestException;
import superhero.exceptionHandler.types.NotFoundException;
import superhero.exceptionHandler.types.ServerException;

@ControllerAdvice
public class BaseExceptionHandler {
	@ExceptionHandler({ ServerException.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Message handleServerExceptions(ServerException ex) {
		return createErrorResponse(ex);
	}
	
	@ExceptionHandler({ InvalidRequestException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody Message handleBadRequestExceptions(InvalidRequestException ex) {
		return createErrorResponse(ex);
	}

	
	@ExceptionHandler({ NotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody Message handleNotFoundExceptions(NotFoundException ex) {
		return createErrorResponse(ex);
	}

	private Message createErrorResponse(SuperHeroServiceException ex) {
		
		return new Message(ex.getMessage());
	}
	
	
}
