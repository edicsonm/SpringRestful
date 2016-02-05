package au.com.edimoto.spring.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import au.com.edimoto.spring.dto.FieldErrorDTO;
import au.com.edimoto.spring.dto.ValidationErrorDTO;
import au.com.edimoto.spring.model.User;
import au.com.edimoto.spring.service.UserService;


@RestController
public class AppRestfulController {
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UserService userService;
	
	//-------------------Retrieve all Users---------------------------------------------------
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> listAllUsers() {
		System.out.println("Obteniendo data");
		return userService.listUsers();
    }
	
	//-------------------Create a User--------------------------------------------------------
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@Valid @RequestBody User user,/* BindingResult bindingResults,*/ UriComponentsBuilder uri) {
		System.out.println("Creating User " + user.getName());
		/*if (bindingResults.hasErrors()) {
			System.out.println("Contiene errores ...");
		}*/
		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
			validationErrorDTO.addFieldError(new FieldErrorDTO("user", "user exist ... "));
			return new ResponseEntity<ValidationErrorDTO>(validationErrorDTO, HttpStatus.CONFLICT);
		}
		
		userService.addUser(user);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri.path("/user/{id}").buildAndExpand(user.getName()).toUri());
		
		return new ResponseEntity<User>(headers, HttpStatus.CREATED);
	}
	
	
	//-------------------Update a User--------------------------------------------------------
		@RequestMapping(value = "/user", method = RequestMethod.PUT)
		public ResponseEntity<?> updateUser(@Valid @RequestBody User user, UriComponentsBuilder uri) {
			System.out.println("Updating User " + user.getName());
			
			userService.updateUser(user);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(uri.path("/user/{id}").buildAndExpand(user.getName()).toUri());
			
			return new ResponseEntity<User>(headers, HttpStatus.CREATED);
		}
	
	//------------------- Delete a User --------------------------------------------------------
	
	@RequestMapping(value = "/user/{user}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("user") String userName) {
		System.out.println("Fetching & Deleting User with user name " + userName);
		User user = userService.findById(userName);
		if (user == null) {
			System.out.println("Unable to delete. User with user name " + userName + " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(userName);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}	
		
	
	//------------------- Manage Errors --------------------------------------------------------
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationErrorDTO handleValidationException(MethodArgumentNotValidException e) {
	    
		/*List<ObjectError> errors = e.getBindingResult().getAllErrors();
	    for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
			ObjectError objectError = (ObjectError) iterator.next();
			System.out.println("objectError: "+objectError);
			
			System.out.println(objectError.getDefaultMessage());
			System.out.println(objectError.getObjectName());
			System.out.println(objectError.getCode());
			System.out.println(objectError.getCode());
			
		}*/
		
//	    System.out.println("*******************");
		Locale currentLocale =  LocaleContextHolder.getLocale();
		
	    ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
	    BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        
	    for (FieldError fieldError : fieldErrors) {
//			System.out.println(fieldError + " --> " + fieldError.getField());
	    	
			String[] fieldErrorCodes = fieldError.getCodes();
			validationErrorDTO.addFieldError(new FieldErrorDTO(fieldError.getField(), messageSource.getMessage(fieldError, currentLocale)));
		}
//        System.out.println(validationErrorDTO.toString());
	    //WebServiceError webServiceError = WebServiceError.build(WebServiceError.Type.VALIDATION_ERROR, errors.get(0).getObjectName() + " " + errors.get(0).getDefaultMessage());
	    //return new ResponseEntity<>(webServiceError, HttpStatus.BAD_REQUEST);
	    return validationErrorDTO;
	}
	
	/*@ExceptionHandler
    @ResponseBody
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Error handleException(MethodArgumentNotValidException exception) {
        //return new ApiErrors(exception.getBindingResult());
        
		System.out.println("Se presento un error de tipo MethodArgumentNotValidException" + exception.getMessage());
		return null;
    }
 */
	
}
