package au.com.edimoto.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/*@ControllerAdvice*/
public class GlobalControllerExceptionHandlerRestFul {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public void handleConflict() {
        System.out.println("Se detecto un error ... ");
    }
	
	/*@ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error handleException(MethodArgumentNotValidException exception) {
        return new ApiErrors(exception.getBindingResult());
    }*/
	
}
