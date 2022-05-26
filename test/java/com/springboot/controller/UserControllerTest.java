package com.springboot.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.model.Address;
import com.springboot.model.User;
import com.springboot.service.UserService;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	void login() throws Exception {
		this.mockMvc.perform(get("/userlogin")).andExpect(status().isOk());
	}

	@Test
	void register() throws Exception {
		this.mockMvc.perform(get("/register")).andExpect(status().isOk());
	}

	@Test
	void userlogin() throws Exception {
		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserStatus(false);
		user.setUserDOB("2022-12-31");
		user.setImage("image".getBytes());
		user.setBase64Image("image");
		List<Address> addList = new ArrayList<>();
		Address address = new Address();

		address.setAddId(1);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		addList.add(address);
		user.setAddress(addList);

		when(userService.userLogin(any())).thenReturn(user);
		this.mockMvc.perform(post("/Userlogin").contentType(MediaType.APPLICATION_JSON).content(user.toString()))
				.andExpect(status().isOk());

	}

	@Test
	void userLoginAdmin() throws Exception {
		List<User> list = new ArrayList<>();
		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserStatus(true);
		user.setUserDOB("2022-12-31");
		user.setImage("image".getBytes());
		user.setBase64Image("image");

		List<Address> addList = new ArrayList<>();
		Address address = new Address();

		address.setAddId(1);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		addList.add(address);
		user.setAddress(addList);

		list.add(user);

		when(userService.userLogin(any())).thenReturn(user);
		when(userService.adminDetail()).thenReturn(list);

		this.mockMvc.perform(post("/Userlogin").sessionAttr("adminDetails", list).content(user.toString()))
				.andExpect(status().isOk());
	}

	@Test
	void userloginTest() throws Exception {
		User user = null;
		when(userService.userLogin(any())).thenReturn(user);
		this.mockMvc.perform(post("/Userlogin").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void registrationTest() throws Exception {
		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserStatus(true);
		user.setUserDOB("2022-12-31");
		user.setBase64Image("");
		String fileLoc = "src/main/webapp/resources/image/default_profile.jpg";
		BufferedImage bImage = ImageIO.read(new File(fileLoc));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos);
		byte[] data = bos.toByteArray();
		user.setImage(data);

		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();

		address.setAddId(0);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		addList.add(address);
		user.setAddress(addList);

		doNothing().when(userService).userRegister(user);

		List<User> list = new ArrayList<User>();
		list.add(user);

		System.out.println("list in test" + list);

		when(userService.adminDetail()).thenReturn(list);
		list = userService.adminDetail();
		System.out.println("list from service" + list);

		this.mockMvc.perform(post("/UserRegister").contentType(MediaType.APPLICATION_JSON).content(user.toString()))
				.andExpect(status().isOk());

		this.mockMvc.perform(post("/UserRegister").param("urlValue", "ADD").contentType(MediaType.APPLICATION_JSON)
				.content(user.toString())).andExpect(status().isOk());

	}

	@Test
	void deleteUser() throws Exception {
		User user = new User();
		user.setUserId(1);
		doNothing().when(userService).deleteUser(1);
		userService.deleteUser(user.getUserId());
		this.mockMvc.perform(post("/DeleteUser").param("userId", "1").contentType(MediaType.APPLICATION_JSON)
				.content(user.toString())).andExpect(status().isOk());
		verify(userService, atLeast(1)).deleteUser(1);

	}

	@Test
	void editUser() throws Exception {

		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserDOB("2022-12-31");
		user.setImage("image".getBytes());
		user.setBase64Image("image");
		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();

		address.setAddId(0);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		addList.add(address);
		user.setAddress(addList);

		when(userService.userIdDetail(1)).thenReturn(user);
		this.mockMvc
				.perform(get("/editProfile").param("id", "1").param("urlValue", "userEdit")
						.sessionAttr("userDetails", user).sessionAttr("userImage", user.getImage()))
				.andExpect(status().isOk());

	}

	@Test
	void editUserByAdmin() throws Exception {

		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserDOB("2022-12-31");
		user.setImage("image".getBytes());
		user.setBase64Image("image");
		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();

		address.setAddId(0);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		addList.add(address);
		user.setAddress(addList);

		when(userService.userIdDetail(1)).thenReturn(user);
		this.mockMvc
				.perform(get("/editProfile").param("id", "1").param("urlValue", "adminEdit")
						.sessionAttr("userDetails", user).sessionAttr("userImage", user.getImage()))
				.andExpect(status().isOk());

	}

	@Test
	void editAdmin() throws Exception {

		List<User> list = new ArrayList<>();

		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserStatus(true);
		user.setUserDOB("2022-12-31");
		user.setImage("image".getBytes());
		user.setBase64Image("image");

		List<Address> addList = new ArrayList<>();
		Address address = new Address();
		Address address1 = new Address();

		address.setAddId(1);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		address1.setAddId(2);
		address1.setAddCity("Rajkot");
		address1.setAddStreet("address 1");
		address1.setAddPincode("1234");
		address1.setAddState("Gujarat");
		address1.setUser(user);

		addList.add(address);
		addList.add(address1);
		user.setAddress(addList);

		list.add(user);
		System.out.println("list" + list);

		when(userService.adminDetail()).thenReturn(list);
		this.mockMvc.perform(get("/editProfile").param("urlValue", "admin").content(user.toString()))
				.andExpect(status().isOk());

	}

	@Test
	void updateUser() throws Exception {
		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserDOB("2022-12-31");
		user.setImage("image".getBytes());
		user.setBase64Image("image");

		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();

		address.setAddId(0);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		addList.add(address);
		user.setAddress(addList);

		MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		doNothing().when(userService).userRegister(user);
		when(userService.userIdDetail(1)).thenReturn(user);
		this.mockMvc.perform(multipart("/UserEdit").file("file", file.getBytes()).param("urlValue", "userEdit")
				.contentType(MediaType.APPLICATION_JSON).content(user.toString())).andExpect(status().isOk());

	}

	@Test
	void updateUserImage() throws IOException, Exception {
		User user = new User();
		user.setImage("image".getBytes());
		MockMultipartFile file = new MockMultipartFile("data", "".getBytes());
		this.mockMvc.perform(multipart("/UserEdit").file("file", file.getBytes()).param("urlValue", "userEdit"))
				.andExpect(status().isOk());

	}

	@Test
	void updateUserImageByAdmin() throws IOException, Exception {
		User user = new User();
		user.setImage("image".getBytes());
		MockMultipartFile file = new MockMultipartFile("data", "".getBytes());
		this.mockMvc.perform(multipart("/UserEdit").file("file", file.getBytes()).param("urlValue", "adminEdit"))
				.andExpect(status().isOk());

	}

	@Test
	void updateAdminImage() throws IOException, Exception {
		List<User> list = new ArrayList<>();
		User user = new User();

		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();

		address.setAddId(0);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		addList.add(address);
		user.setAddress(addList);
		list.add(user);
		user.setImage("image".getBytes());
		MockMultipartFile file = new MockMultipartFile("data", "".getBytes());
		when(userService.adminDetail()).thenReturn(list);
		this.mockMvc.perform(multipart("/UserEdit").file("file", file.getBytes()).param("urlValue", "admin"))
				.andExpect(status().isOk());

	}

	@Test
	void updateUserByAdmin() throws Exception {

		List<User> list = new ArrayList<>();

		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserDOB("2022-12-31");
		user.setImage("image".getBytes());
		user.setBase64Image("image");

		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();

		address.setAddId(0);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		addList.add(address);
		user.setAddress(addList);
		list.add(user);

		MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		doNothing().when(userService).userRegister(user);
		when(userService.allUsers()).thenReturn(list);
		this.mockMvc
				.perform(multipart("/UserEdit").file("file", file.getBytes()).param("urlValue", "adminEdit")
						.sessionAttr("userDetails", user).sessionAttr("userImage", user.getImage()))
				.andExpect(status().isOk());

	}

	@Test
	void updateAdmin() throws Exception {

		List<User> list = new ArrayList<>();

		User user = new User();
		user.setUserId(1);
		user.setUserName("jemy");
		user.setUserPassword("123");
		user.setUserEmail("jemy@gmail.com");
		user.setUserContact("1234567890");
		user.setUserGender("female");
		user.setUserHobby("Sports");
		user.setUserStatus(true);
		user.setUserDOB("2022-12-31");
		user.setImage("image".getBytes());
		user.setBase64Image("image");

		List<Address> addList = new ArrayList<>();
		Address address = new Address();
		Address address1 = new Address();

		address.setAddId(1);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);

		address1.setAddId(2);
		address1.setAddCity("Rajkot");
		address1.setAddStreet("address 1");
		address1.setAddPincode("1234");
		address1.setAddState("Gujarat");
		address1.setUser(user);

		addList.add(address);
		addList.add(address1);
		user.setAddress(addList);

		list.add(user);
		System.out.println("list" + list);
		MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		doNothing().when(userService).userRegister(user);

		when(userService.adminDetail()).thenReturn(list);
		this.mockMvc.perform(multipart("/UserEdit").file("file", file.getBytes()).param("urlValue", "admin")
				.content(user.toString())).andExpect(status().isOk());

	}

	@Test
	void checkMail() throws Exception {
		User user = new User();
		user.setUserEmail("jemy@gmail.com");
		when(userService.checkMail(user.getUserEmail())).thenReturn(false);
		this.mockMvc.perform(get("/checkEmail").param("email", user.getUserEmail())).andExpect(status().isOk());
		when(userService.checkMail(user.getUserEmail())).thenReturn(true);
		this.mockMvc.perform(get("/checkEmail").param("email", user.getUserEmail())).andExpect(status().isOk());

	}

	@Test
	void viewUser() throws Exception {

		List<User> list = new ArrayList<>();
		when(userService.allUsers()).thenReturn(list);
		this.mockMvc.perform(get("/view").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void userHome() throws Exception {
		User user = new User();
		user.setImage("image".getBytes());
		user.setBase64Image("image");
		List<Address> addList = new ArrayList<Address>();
		Address address = new Address();

		address.setAddId(0);
		address.setAddCity("Rajkot");
		address.setAddStreet("address 1");
		address.setAddPincode("1234");
		address.setAddState("Gujarat");
		address.setUser(user);
		user.setAddress(addList);

		this.mockMvc.perform(get("/userHome")).andExpect(status().isOk());
	}

	@Test
	void adminHome() throws Exception {
		User user = new User();
		List<User> list = new ArrayList<>();
		when(userService.adminDetail()).thenReturn(list);
		this.mockMvc
				.perform(get("/adminHome").sessionAttr("adminDetails", list).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void forgetPwd() throws Exception {
		this.mockMvc.perform(get("/forgetPassword")).andExpect(status().isOk());
	}

	@Test
	void forgetPassword() throws Exception {

		User user = new User();
		user.setUserEmail("jemy@gmail.com");
		user.setUserPassword("123");
		doNothing().when(userService).updatePassword(user);
		this.mockMvc.perform(post("/ForgetPassword")).andExpect(status().isOk());
		verify(userService, atLeast(1)).updatePassword(any());

	}

	@Test
	void changeRole() throws Exception {
		User user = new User();
		user.setUserId(1);
		doNothing().when(userService).changeRole(user.getUserId());
		userService.changeRole(1);
		this.mockMvc.perform(post("/ChangeRole").param("userId", "1")).andExpect(status().isOk());

	}

	@Test
	void logout() throws Exception {
		this.mockMvc.perform(post("/logout")).andExpect(status().isOk());
	}

}
