package com.booking.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.dao.PasswordDaoImpl;
import com.booking.dao.UserDao;
import com.booking.model.Notifikasi;
import com.booking.model.User;


@Service
public class UserDaoImpl implements UserDao {
	
	private EntityManagerFactory entityManagerFactory;
	private PasswordDaoImpl passwords;
	
	@Autowired
	public void setPasswords(PasswordDaoImpl passwords) {
		this.passwords = passwords;
	}

	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<User> getAllUser() {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from User", User.class).getResultList();
	}

	@Override
	public User saveOrUpdate(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		User saved = em.merge(user);
		em.getTransaction().commit();
		return saved;
		
	}

	@Override
	public User login(String username, String password) {
		List <User> allUser = getAllUser();
		User userFind = null;
		for(User user : allUser){
			if(user.getUsername().equals(username)){
				System.out.println("->"+password+"->"+user.getPassword());
				if(passwords.matches(password, user.getPassword())) {
					userFind = user;
					break;
				}
			}
		}
		return userFind;
	}

	@Override
	public User getIdUser(Integer id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.find(User.class, id);
		
	}

	@Override
	public List<User> getAllUserByRole(String Role) {
		EntityManager em = entityManagerFactory.createEntityManager();
		return em.createQuery("from User where role= '"+Role+"'", User.class).getResultList();
	}

	@Override
	public User sendPassword(String email) {
		List <User> users = getAllUser();
		for(User user : users){
			if(user.getEmail().equals(email)){
				return user;
			}
		}
		return null;
	}

}
