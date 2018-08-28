package com.booking.dao;


import java.util.List;

import com.booking.model.*;

public interface UserDao {
	List<User> getAllUser();
	User saveOrUpdate(User user);
	User login(String username, String password);
	User getIdUser(Integer id);
	List <User> getAllUserByRole(String Role);
	
	public User sendPassword(String email);
	
}
