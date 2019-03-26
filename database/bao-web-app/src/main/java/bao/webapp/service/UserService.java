package bao.webapp.service;

import bao.webapp.model.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User findById(long id);
}