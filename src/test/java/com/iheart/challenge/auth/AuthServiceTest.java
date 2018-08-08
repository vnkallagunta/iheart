package com.iheart.challenge.auth;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {
	private AuthService service = new AuthService();
	
	@Test
	public void testAuthorize() {
		final String token1 = "cb9c6f13-d680-4335-a5df-de2ea8ea70da";
		final String token2 = "1f837e00-3cfd-4f0d-8cc6-65873ec6ec90";
		final String token3 = "48337cac-a60b-4608-9b88-79265c67f91d";
		
		Assert.assertTrue(service.authorize(token1));
		Assert.assertTrue(service.authorize(token2));
		Assert.assertTrue(service.authorize(token3));
	}
	
	@Test
	public void testAuthorizeWithEmptyToken() {
		final String token1 = "    ";
		final String token2 = "";
		final String token3 = null;
		
		Assert.assertFalse(service.authorize(token1));
		Assert.assertFalse(service.authorize(token2));
		Assert.assertFalse(service.authorize(token3));
	}
	
	@Test
	public void testAuthorizeWithInvalidToken() {
		final String invalidToken = "cb9c6f13-d680-4335-a5df-de2ea8ea70dc";
		
		Assert.assertFalse(service.authorize(invalidToken));
	}
}
