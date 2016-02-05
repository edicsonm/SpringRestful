package au.com.edimoto.spring.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	
	@NotEmpty
	@Length(max = 10)
	public String name;
	
	@NotEmpty
	@Length(max = 10)
//	@Size(max = 100, message = "error.description.size")
	public String lastName;
	
	public User(){
	}
	
	public User(String name, String lastName){
		this.name = name;
		this.lastName = lastName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public boolean  equals (Object object) {
	boolean result = false;
	if (object == null || object.getClass() != getClass()) {
	    result = false;
	} else {
		User user = (User) object;
	    if (this.name.equalsIgnoreCase(user.getName())) {
	        result = true;
	    }
	}
	return result;
	}
}
