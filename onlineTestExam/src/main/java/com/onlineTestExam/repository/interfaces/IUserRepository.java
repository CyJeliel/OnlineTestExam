package com.onlineTestExam.repository.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.onlineTestExam.domain.User;

public interface IUserRepository extends CrudRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);
}
