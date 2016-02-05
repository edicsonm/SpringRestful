package au.com.edimoto.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import au.com.edimoto.spring.model.User;

@Service("UserService")
public class UserServiceImpl implements UserService {
	
	public List<User> listUsers = new ArrayList<User>();
	
	public UserServiceImpl(){
		listUsers.add(new User("Edicson","Morales"));
		listUsers.add(new User("Hamilton","Morales"));
		listUsers.add(new User("Michelle","Earle"));
	}
	
	public List<User> listUsers() {
		return listUsers;
	}

	public void addUser(User user) {
		listUsers.add(user);
	}

	public boolean isUserExist(User user) {
		return listUsers.contains(user);
	}

	public void updateUser(User user) {
		int posi = listUsers.indexOf(user);
		if (posi != -1)
			listUsers.set(posi, user);
	}

	public void deleteUserById(String userName) {
		User user = new User();
		user.setName(userName);
		listUsers.remove(user);
	}

	public User findById(String userName) {
		User user = new User();
		user.setName(userName);
		int posi = listUsers.indexOf(user);
		if (posi != -1)
			user = listUsers.get(posi);
		else
			user = null;		
		return user;
	}
	
}
