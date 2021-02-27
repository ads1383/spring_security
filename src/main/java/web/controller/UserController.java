package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.security.Principal;
import java.util.*;


@Controller
public class UserController {


	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public String showAll() {
		return "redirect:/login";
	}

	@GetMapping(value = "/user")
	public String showAllUsers(Principal principal, ModelMap model) {
		User user = userService.findByUsername(principal.getName());
		List<String> messages = new ArrayList<>();
		messages.add(user.getFirstName());
		messages.add(user.getLastName());
		messages.add(user.getEmail());
		model.addAttribute("messages", messages);
		return "hello";
	}

	@GetMapping(value = "/admin")
	public String showAadmins(ModelMap model) {
		model.addAttribute("users", userService.listUsers());
		return "index";
	}

	@GetMapping(value = "/login")
	public String getLoginPage() {
		return "login";
	}

	@GetMapping(value = "/add")
	public String addUser(@ModelAttribute("user") User user, ModelMap model) {
		model.addAttribute("message", "Add new User");
		model.addAttribute("user", user);
		model.addAttribute("rolelist", userService.listRoles());
		return "adduser";
	}

	@PostMapping(value = "/saveuser")
	public String saveUser(@ModelAttribute("user") User user,
							@RequestParam("val") List<Long> roles) {
		user.setRoles(userService.hashSetRolesByListId(roles));
		userService.save(user);
		return "redirect:/admin";
	}

	@PostMapping(value = "/updateuser")
	public String updateUser(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("message", "Update User");
		model.addAttribute("user", userService.getById(id));
		model.addAttribute("rolelist", userService.listRoles());
		return "adduser";
	}

	@PostMapping(value = "/deleteuser")
	public String removeUser(@RequestParam("id") Long id) {
		userService.delete(id);
		return "redirect:/admin";
	}
}