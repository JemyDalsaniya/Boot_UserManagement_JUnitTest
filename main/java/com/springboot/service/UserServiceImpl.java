package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.UserRepository;
import com.springboot.model.Address;
import com.springboot.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void userRegister(User user) {
		System.out.println("inside service" + user.getAddress());
		for (Address address : user.getAddress()) {
			address.setUser(user);
		}
		userRepository.save(user);
		System.out.println("after save" + user.getAddress());
	}

	@Override
	public User userLogin(User user) {
		return userRepository.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
	}

	@Override
	public List<User> allUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User userIdDetail(int id) {
		return userRepository.findByUserId(id);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void updatePassword(User user) {
		userRepository.updatePassword(user.getUserEmail(), user.getUserPassword());
	}

	@Override
	public boolean checkMail(String email) {
		List<User> list = userRepository.findByUserEmail(email);
		System.out.println("list in servivcve" + list);
		if (list != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void changeRole(int id) {
		userRepository.changeRole(id);
	}

	@Override
	public List<User> adminDetail(User user) {
		return userRepository.findByUserStatus(user.getUserStatus());
	}

}
