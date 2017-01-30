package com.onlineTestExam.service.implementations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.onlineTestExam.domain.User;
import com.onlineTestExam.repository.interfaces.IUserRepository;
import com.onlineTestExam.service.implementations.UserService;
import com.onlineTestExam.service.interfaces.IUserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private IUserService app = new UserService();

	@Mock
	private IUserRepository repo;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionWhenUserNull() {
	
		app.login(null);
	}
	
	@Test
	public void loginWithNullFieldsShouldGiveAnErrorMessage() {

		String errorMessage = app.login(new User());

		Assert.assertNotNull(errorMessage);

		Assert.assertNotEquals("", errorMessage);

		Assert.assertEquals("Please, provide both username and password.", errorMessage);
	}
	
	@Test
	public void loginWithWrongFieldsShouldNotGiveAnErrorMessage() {
		
		User user = new User();
		
		String username = "username";
		
		user.setUsername(username);
		
		String password = "password";
		
		user.setPassword(password);
		
		Mockito.when(repo.findByUsernameAndPassword(username, password)).thenReturn(null);
		
		String errorMessage = app.login(user);
		
		Mockito.verify(repo).findByUsernameAndPassword(username, password);
		
		Assert.assertNotNull(errorMessage);
		
		Assert.assertEquals("Invalid username or password.", errorMessage);
	}

	@Test
	public void loginWithProvidedFieldsShouldNotGiveAnErrorMessage() {
		
		User user = new User();
		
		String username = "username";
		
		user.setUsername(username);
		
		String password = "password";
		
		user.setPassword(password);
		
		Mockito.when(repo.findByUsernameAndPassword(username, password)).thenReturn(new User());

		String errorMessage = app.login(user);
		
		Assert.assertNull(errorMessage);
	}
}
