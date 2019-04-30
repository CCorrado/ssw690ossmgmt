package bao.webapp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bao.webapp.model.User;
import bao.webapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> list() {
		List<User> all = new ArrayList<>();

		for (User user : this.userRepository.findAll())
			all.add(user);

		return all;
	}

	@Override
	public User findById(long id) {
		return this.userRepository.findById(id).get();
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUserName(username);
	}

	@Override
	public void create(User user) {
		this.userRepository.save(user);
	}
	
}