package au.com.edimoto.test;

import org.junit.Test;

import static org.junit.Assert.*;

import au.com.edimoto.spring.model.User;
import au.com.edimoto.spring.service.UserServiceImpl;

public class UserTest {
	
	UserServiceImpl userServiceImpl = new UserServiceImpl();
	
	@Test
	public void testUserExistence(){
		User user = new User("Edicson","Morales");
		System.out.println(userServiceImpl.listUsers.size());
		assertTrue(userServiceImpl.listUsers.contains(user));
	}
	
	@Test
	public void testUserList(){
		System.out.println(userServiceImpl.listUsers.size());
		assertFalse(userServiceImpl.listUsers.isEmpty());
	}

}
