package bao.webapp;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bao.webapp.model.User;
import bao.webapp.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public List<User> list() {
		return userService.list();
	}

	@RequestMapping("/users/{id}")
	public User get(@PathVariable("id") Long id) {
		return userService.findById(id);
	}
}