package bao.webapp.service;

import java.util.List;

import bao.webapp.model.User;

public interface UserService {

	public List<User> list();

	public User findById(long id);
}