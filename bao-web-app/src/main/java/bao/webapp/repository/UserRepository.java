package bao.webapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bao.webapp.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}