package br.com.superdia.controller;

import java.util.ArrayList;
import java.util.List;

public class UserController {
	
private List<User> users;
	
	
	public UserController() {
		users = new ArrayList<User>();
		
		users.add(new User("admin", "admin", "admin"));
		users.add(new User("ramon", "ramon", "cliente"));
		users.add(new User("jooj", "jooj", "caixa"));
		
	}
	
	
	public boolean login(User user) {
		for (User u : users) {
			if(user.getUsername().equals(u.getUsername())
					&& user.getPassword().equals(u.getPassword())
					&& u.getProfile().equals("admin")) 
			{
				return true;
			}
		}
		
		return false;
	}
}
