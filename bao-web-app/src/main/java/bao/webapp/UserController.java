package bao.webapp;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import bao.webapp.model.AuthInfo;
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
		try {
			return userService.findById(id);
		} catch (Exception exp) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		}
	}

	@RequestMapping("/users/by-username/{username}")
	public AuthInfo getByUsername(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);

		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
		} else {
			AuthInfo info = new AuthInfo();
			info.username = user.getUserName();
			info.password = user.getPassword();

			return info;
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public Boolean createUser(@RequestBody User user) {
		userService.create(user);

		return true;
	}
}