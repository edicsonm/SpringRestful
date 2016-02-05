package au.com.edimoto.spring.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO {
	
	private List<FieldErrorDTO> fieldErrors = new ArrayList<FieldErrorDTO>();

	public List<FieldErrorDTO> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	public void addFieldError(FieldErrorDTO fieldErrorDTO){
		fieldErrors.add(fieldErrorDTO);
	}
	
}
