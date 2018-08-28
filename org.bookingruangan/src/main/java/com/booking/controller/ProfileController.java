package com.booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.booking.dao.UserDao;
import com.booking.model.User;

@Controller
public class ProfileController {
	private UserDao uDao;
	private User userLogin;
	
	@Autowired
	public void setuDao(UserDao uDao) {
		this.uDao = uDao;
	}

	
	
}
