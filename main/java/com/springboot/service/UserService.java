package com.springboot.service;

import java.util.List;

import com.springboot.model.User;

public interface UserService {

	void userRegister(User user);

	User userLogin(User user);

	List<User> allUsers();

	void deleteUser(int id);

	User userIdDetail(int id);

	void updateUser(User user);

	void updatePassword(User user);

	boolean checkMail(String email);

	void changeRole(int id);

	List<User> adminDetail(User user);

}
