package com.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.dao.AddressRepository;
import com.springboot.dao.UserRepository;
import com.springboot.model.Address;
import com.springboot.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AddressRepository addressRepository;

	@Override
	public void userRegister(User user) {
		try {
			List<Integer> addressIdList = new ArrayList<Integer>();
			for (Address address : user.getAddress()) {
				address.setUser(user);
				System.out.println("address list" + address);
			}
			if (user.getUserId() != 0) {
				System.out.println("USE OBJ" + user);
				int rem = 0;
				System.out.println(user.getAddress().size());
				while (user.getAddress().size() != rem) {
					String cityCheck = user.getAddress().get(rem).getAddCity();
					System.out.println("city check" + cityCheck);
					if (cityCheck != null) {
						rem++;
					} else {
						user.getAddress().remove(rem);
					}
				}
				for (Address address : user.getAddress()) {
					addressIdList.add(address.getAddId());
				}
				System.out.println("AddressIdList" + addressIdList);
				addressRepository.deleteByAddIdNotInAndUser(addressIdList, user);

			}
			userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User userLogin(User user) {
		try {
			return userRepository.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> allUsers() {
		try {
			return userRepository.findByUserStatus(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteUser(int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public User userIdDetail(int id) {
		try {
			return userRepository.findByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePassword(User user) {
		try {
			userRepository.updatePassword(user.getUserEmail(), user.getUserPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkMail(String email) {
		try {
			List<User> list = userRepository.findByUserEmail(email);
			if (!list.isEmpty()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void changeRole(int id) {
		try {
			userRepository.changeRole(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> adminDetail() {
		try {
			return userRepository.findByUserStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
