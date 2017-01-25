package com.onlineTestExam.business;

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

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	private UserService app;

	@Mock
	private IUserRepository repo;

	@Before
	public void init() {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void loginWithNullFieldsShouldGiveAnErrorMessage() {

		String errorMessage = app.login(null, null);

		Assert.assertNotNull(errorMessage);

		Assert.assertNotEquals("", errorMessage);

		Assert.assertEquals("Please, provide both username and password.", errorMessage);

		errorMessage = app.login("", "");

		Assert.assertNotNull(errorMessage);

		Assert.assertNotEquals("", errorMessage);

		Assert.assertEquals("Please, provide both username and password.", errorMessage);
	}
	
	@Test
	public void loginWithWrongFieldsShouldNotGiveAnErrorMessage() {
		
		String username = "username";
		
		String password = "password";
		
		Mockito.when(repo.findByUsernameAndPassword(username, password)).thenReturn(null);
		
		String errorMessage = app.login(username, password);
		
		Mockito.verify(repo).findByUsernameAndPassword(username, password);
		
		Assert.assertNotNull(errorMessage);
		
		Assert.assertEquals("Invalid username or password.", errorMessage);
	}

	@Test
	public void loginWithProvidedFieldsShouldNotGiveAnErrorMessage() {

		String username = "username";

		String password = "password";
		
		Mockito.when(repo.findByUsernameAndPassword(username, password)).thenReturn(new User());

		String errorMessage = app.login(username, password);
		
		Assert.assertNull(errorMessage);
	}
}
