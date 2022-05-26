package com.springboot.service;

import java.util.List;

import com.springboot.model.User;

public interface UserService {

	/**
	 * 
	 * @param user
	 */
	void userRegister(User user);

	/**
	 * 
	 * @param user
	 * @return
	 */
	User userLogin(User user);

	/**
	 * 
	 * @return
	 */
	List<User> allUsers();

	/**
	 * 
	 * @param id
	 */
	void deleteUser(int id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	User userIdDetail(int id);

	/**
	 * 
	 * @param user
	 */
	void updatePassword(User user);

	/**
	 * 
	 * @param email
	 * @return
	 */
	boolean checkMail(String email);

	/**
	 * 
	 * @param id
	 */
	void changeRole(int id);

	/**
	 * 
	 * @param user
	 * @return
	 */
	List<User> adminDetail();

}
