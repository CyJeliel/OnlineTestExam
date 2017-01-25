package com.onlineTestExam.service.implementations;

import static org.springframework.util.StringUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineTestExam.domain.User;
import com.onlineTestExam.repository.interfaces.IUserRepository;
import com.onlineTestExam.service.interfaces.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository repo;

	@Override
	public String login(String username, String password) {

		String message = null;

		if (isEmpty(username) || isEmpty(password)) {

			message = "Please, provide both username and password.";

		} else {

			User user = repo.findByUsernameAndPassword(username, password);

			if (user == null) {

				message = "Invalid username or password.";
			}
		}

		return message;
	}

}
