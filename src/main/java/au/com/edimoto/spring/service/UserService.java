package au.com.edimoto.spring.service;

import java.util.List;

import au.com.edimoto.spring.model.User;

public interface UserService {
	List<User> listUsers();
	void addUser(User user);
	void updateUser(User user);
	void deleteUserById(String userName);
	boolean isUserExist(User user);
	User findById(String userName);
}
