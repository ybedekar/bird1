package superhero.errorcontroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController {
		
	@RequestMapping(value ="/http500Error")
	String internalServerError() {
	    return "Server error";
	}
	
	@RequestMapping(value ="/http400Error")
	String badRequestError() {
		return "Bad request";
	}
 	
	@RequestMapping(value ="/http405Error")
	String methodNotAllowedError() {
	    return "Method not allowed";
	}
	
	@RequestMapping(value ="/http404Error")
	String noDataError() {
	    return "Not found";
	}
	
	@RequestMapping(value ="/http403Error")
	String forbiddenError() {
	    return "Forbidden request";
	}
	

}
