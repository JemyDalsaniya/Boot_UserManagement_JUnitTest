package com.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.springboot.dao.AddressRepository;
import com.springboot.dao.UserRepository;
import com.springboot.model.Address;
import com.springboot.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private AddressRepository addressRepository;

	@Test
	void userRegister() {

		User user = new User();
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserStatus(true);
		user.setUserDOB("2022-12-31");
		user.setBase64Image("");

		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();
		Address address1 = new Address();
		Address address2 = new Address();

		address.setAddId(1);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		address1.setAddId(2);
		address1.setAddCity("");
		address1.setAddStreet("");
		address1.setAddPincode("");
		address1.setAddState("");
		address1.setUser(user);

		address1.setAddId(0);
		address1.setAddCity("Ahmedabad");
		address1.setAddStreet("address 3");
		address1.setAddPincode("2728");
		address1.setAddState("Gujarat");
		address2.setUser(user);

		addList.add(address1);
		addList.add(address);
		addList.add(address2);

		user.setAddress(addList);

		final User entity = spy(User.class);
		when(userRepository.save(user)).thenReturn(entity);
		userService.userRegister(user);

		user.setUserId(1);
		userService.userRegister(user);

		doThrow(new RuntimeException()).when(userRepository).save(user);
		userService.userRegister(user);

		verify(userRepository, atLeast(1)).save(any());

	}

	@Test
	void userLogin() {
		User user = new User();
		user.setUserEmail("jemy@gmail.com");
		user.setUserPassword("123");
		when(userRepository.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword()))
				.thenReturn(user);
		User object = userService.userLogin(user);
		assertEquals(user, object);

		when(userRepository.findByUserEmailAndUserPassword(user.getUserEmail(), user.getUserPassword()))
				.thenThrow(new RuntimeException());
		userService.userLogin(user);
		verify(userRepository, atLeast(1)).findByUserEmailAndUserPassword(any(), any());

	}

	@Test
	void allUsers() {
		List<User> list = new ArrayList<>();
		when(userRepository.findByUserStatus(false)).thenReturn(list);
		list = userService.allUsers();

		assertNotNull(list);

		when(userRepository.findByUserStatus(false)).thenThrow(new RuntimeException());
		userService.allUsers();
		verify(userRepository, atLeast(1)).findByUserStatus(false);

	}

	@Test
	void updatePassword() {
		User user = new User();
		user.setUserEmail("jemy@gmail.com");
		user.setUserPassword("123");
		doNothing().when(userRepository).updatePassword(user.getUserEmail(), user.getUserPassword());
		userService.updatePassword(user);

		doThrow(new RuntimeException()).when(userRepository).updatePassword(user.getUserEmail(),
				user.getUserPassword());
		userService.updatePassword(user);
		verify(userRepository, atLeast(1)).updatePassword(any(), any());
	}

	@Test
	void deleteUser() {
		User user = new User();
		user.setUserId(1);
		doNothing().when(userRepository).deleteById(user.getUserId());
		userService.deleteUser(1);

		doThrow(new RuntimeException()).when(userRepository).deleteById(user.getUserId());
		userService.deleteUser(1);
		verify(userRepository, atLeast(1)).deleteById(any());

	}

	@Test
	void userIdDetail() {
		User user = new User();
		user.setUserId(1);
		when(userRepository.findByUserId(user.getUserId())).thenReturn(user);
		User object = userService.userIdDetail(1);

		assertEquals(user, object);

		when(userRepository.findByUserId(1)).thenThrow(new RuntimeException());
		userService.userIdDetail(1);
		verify(userRepository, atLeast(1)).findByUserId(1);
	}

	@Test
	void checkMail() {
		List<User> list = new ArrayList<User>();
		User user = spy(User.class);
		list.add(user);
		when(userRepository.findByUserEmail("jemy@gmail.com")).thenReturn(list);
		userService.checkMail("jemy@gmail.com");

		List<User> nullList = Collections.EMPTY_LIST;
		when(userRepository.findByUserEmail("jemy@gmail.com")).thenReturn(nullList);
		userService.checkMail("jemy@gmail.com");

		when(userRepository.findByUserEmail("jemy@gmail.com")).thenThrow(new RuntimeException());
		userService.checkMail("jemy@gmail.com");
		verify(userRepository, atLeast(1)).findByUserEmail("jemy@gmail.com");
	}

	@Test
	void changeRole() {
		User user = new User();
		user.setUserId(1);
		doNothing().when(userRepository).changeRole(user.getUserId());
		userService.changeRole(1);
		doThrow(new RuntimeException()).when(userRepository).changeRole(user.getUserId());
		userService.changeRole(user.getUserId());
		verify(userRepository, atLeast(1)).changeRole(1);

	}

	@Test
	void adminDetail() {
		User user = new User();
		user.setUserStatus(true);
		List<User> list = new ArrayList<>();

		when(userRepository.findByUserStatus(true)).thenReturn(list);
		list = userService.adminDetail();

		List<User> list1 = new ArrayList<>();
		User object = new User();
		object.setUserStatus(true);
		list1 = userService.adminDetail();

		assertEquals(list, list1);

		when(userRepository.findByUserStatus(true)).thenThrow(new RuntimeException());
		userService.adminDetail();
		verify(userRepository, atLeast(1)).findByUserStatus(true);

	}

}
