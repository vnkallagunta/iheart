package com.iheart.challenge.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthService {
	final List<String> validTokens = new ArrayList() {{add("cb9c6f13-d680-4335-a5df-de2ea8ea70da");add("1f837e00-3cfd-4f0d-8cc6-65873ec6ec90");add("48337cac-a60b-4608-9b88-79265c67f91d");}};
	
	public final boolean authorize(final String token) {
		if(StringUtils.isEmpty(token)) {
			return false;
		}
		if(!validTokens.contains(token)) {
			return false;
		}
		return true;
	}
}
