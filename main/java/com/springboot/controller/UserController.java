package com.springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.model.Address;
import com.springboot.model.User;
import com.springboot.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = { "/", "userlogin" })
	public String login() {
		System.out.println("inside login");
		return "Userlogin";
	}

	@RequestMapping(value = { "/register", "/addUser" })
	public String register(Model model, HttpSession session) {
		System.out.println("inside open register page");

		session.removeAttribute("userDetails");
		session.removeAttribute("addressList");
		session.removeAttribute("adminDetails");
		return "UserRegister";
	}

	@PostMapping("/userlogin")
	public String userlogin(Model model, @ModelAttribute("loginForm") User user, HttpSession session) {
		System.out.println("inside login");
		User userobj = service.userLogin(user);
		userobj.getUserStatus();
		List<Address> addressList = userobj.getAddress();
		System.out.println("object" + userobj);
		if (userobj != null) {
			if (userobj.getUserStatus()) {
				List<User> adminDetailList = service.adminDetail(user);
				System.out.println("adminDetail list" + adminDetailList);
				session.setAttribute("adminDetails", adminDetailList);
				session.setAttribute("addressList", addressList);
				return "AdminHomePage";
			} else {
				model.addAttribute("userDetails", userobj);
				model.addAttribute("addressList", addressList);
				return "UserHomePage";
			}
		}
		return "Userlogin";
	}

	@RequestMapping("/view")
	public String viewUser(Model model, HttpSession session) {
		List<User> allusers = service.allUsers();
		if (allusers.get(0).getUserStatus()) {
			allusers.remove(allusers.remove(0));
		}
		model.addAttribute("allusers", allusers);
		return "ViewUser";
	}

	@PostMapping(value = { "/UserRegister" })
	public String registration(Model model, @ModelAttribute("registerForm") User user,

			@RequestParam(value = "name", required = false) String urlValue) {

		System.out.println("url value is" + urlValue);
		System.out.println("inside user register");
		System.out.println("address" + user.getAddress());

		service.userRegister(user);

		if (urlValue != null) {
			return "AdminHomePage";
		} else {
			return "Userlogin";
		}
	}

	@PostMapping(value = "/DeleteUser")
	public void deleteUser(@RequestParam("userId") int id, HttpServletResponse response) throws IOException {
		service.deleteUser(id);
		response.getWriter().write("in success");
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String editUser(User user, HttpSession session, Model model,
			@RequestParam(value = "id", required = false) String userId,
			@RequestParam(value = "name", required = false) String valueURL) {

		System.out.println("name is" + valueURL);
		System.out.println("inside edit profile");
		System.out.println("id is" + userId);

		if (valueURL.equals("admin")) {
			session.removeAttribute("userDetails");
			session.removeAttribute("addressList");
			user.setUserStatus(true);
			List<User> adminDetailList = service.adminDetail(user);
			List<Address> addressList = adminDetailList.get(0).getAddress();
			session.setAttribute("adminDetails", adminDetailList);
			session.setAttribute("addressList", addressList);
		} else if (valueURL.equals("userEdit") || valueURL.equals("adminEdit")) {
			System.out.println("inside else part");
			session.removeAttribute("userDetails");
			User userProfile = service.userIdDetail(Integer.parseInt(userId));
			byte[] byteimage = userProfile.getImage();
			session.setAttribute("userImage", byteimage);
			List<Address> addressList = userProfile.getAddress();
			System.out.println("user profile" + userProfile);
			session.setAttribute("userDetails", userProfile);
			session.setAttribute("addressList", addressList);
		}

		return "UserRegister";
	}

	@PostMapping(value = "/UserEdit")
	public String updateUser(Model model, @ModelAttribute("registerForm") User user, HttpSession session,
			@RequestParam("file") MultipartFile image,
			@RequestParam(value = "name", required = false) String urlValue) {

		if (image.isEmpty() && (urlValue.equals("userEdit"))) {
			byte[] imagebytes = (byte[]) session.getAttribute("userImage");
			user.setImage(imagebytes);
		}
		service.updateUser(user);
		if (urlValue.equals("userEdit")) {
			User userProfile = service.userIdDetail(user.getUserId());
			model.addAttribute("userDetails", userProfile);
			return "UserHomePage";
		}
		return null;

	}

	@GetMapping(value = "/checkEmail")
	public void checkMail(@RequestParam("email") String email, HttpServletResponse response) throws IOException {
		boolean flag = service.checkMail(email);
		System.out.println("flag value" + flag);
		if (flag) {
			response.getWriter().write("true");
		} else {
			response.getWriter().write("false");
		}
	}

	@RequestMapping("/userHome")
	public String userHome() {
		return "UserHomePage";
	}

	@RequestMapping("/adminHome")
	public String adminHome() {
		return "AdminHomePage";
	}

	@RequestMapping("forgetPassword")
	public String forgetPwd() {
		return "ForgetPassword";
	}

	@PostMapping("ForgetPassword")
	public String forgetPassword(Model model, @ModelAttribute("forgetPwd") User user) {
		System.out.println("inside forget pwd");

		service.updatePassword(user);
		return "Userlogin";
	}

	@PostMapping(value = "/ChangeRole")
	public String changeRole(@RequestParam("userId") int id, HttpServletResponse response) throws IOException {
		service.changeRole(id);
		response.getWriter().write("in success");
		return "AdminHomePage";

	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("userDetails");
		session.removeAttribute("addressList");
		session.removeAttribute("adminDetails");
		return "Userlogin";
	}

}
